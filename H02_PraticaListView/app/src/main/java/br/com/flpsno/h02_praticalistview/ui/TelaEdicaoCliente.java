package br.com.flpsno.h02_praticalistview.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.R;
import br.com.flpsno.h02_praticalistview.banco.Constantes;
import br.com.flpsno.h02_praticalistview.dao.ClienteDao;
import br.com.flpsno.h02_praticalistview.model.Cliente;
import br.com.flpsno.h02_praticalistview.model.Produto;

/**
 * Created by Felipe on 22/09/2016.
 */
public class TelaEdicaoCliente extends AppCompatActivity {

    private Context context;
    //
    private Toolbar toolbar;
    //
    private ClienteDao clienteDao;
    //
    private EditText et_codigo;
    private EditText et_nome;
    private EditText et_email;
    private EditText et_telefone;
    private EditText et_cidade;
    private Spinner sp_estado;
    //
    private Button bt_excluir;
    private Button bt_salvar;
    //
    private ArrayAdapter<String> adapter_estados;
    //
    private Long idAtual;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaedicaocliente);

        inicializarVariavel();
        inicializarAcao();
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //
        clienteDao = new ClienteDao(context);
        //
        et_codigo = (EditText) findViewById(R.id.telaedicaocliente_et_codigo);
        et_nome = (EditText) findViewById(R.id.telaedicaocliente_et_nome);
        et_email = (EditText) findViewById(R.id.telaedicaocliente_et_email);
        et_telefone = (EditText) findViewById(R.id.telaedicaocliente_et_telefone);
        et_cidade = (EditText) findViewById(R.id.telaedicaocliente_et_cidade);
        sp_estado = (Spinner) findViewById(R.id.telaedicaocliente_sp_estado);
        //
        bt_salvar = (Button) findViewById(R.id.telaedicaocliente_bt_salvar);
        bt_excluir = (Button) findViewById(R.id.telaedicaocliente_bt_excluir);
        //
        recuperarParametros();
        //
        adapter_estados = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_item,
                Constantes.estados
        );
        //
        adapter_estados.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        sp_estado.setAdapter(adapter_estados);
        //
        if (idAtual != -1) {
            Cliente cAux = clienteDao.obterClienteByID(idAtual);
            //
            et_codigo.setText(String.valueOf(cAux.getIdcliente()));
            et_nome.setText(cAux.getNome());
            et_email.setText(cAux.getEmail());
            et_telefone.setText(cAux.getTelefone());
            et_cidade.setText(cAux.getCidade());
            sp_estado.setSelection(descobrirPosicaoEstado(sp_estado, cAux.getEstado()));
            //
            bt_excluir.setVisibility(View.VISIBLE);
        } else {
            bt_excluir.setVisibility(View.GONE);
        }
    }

    private int descobrirPosicaoEstado(Spinner spAux, String estado) {
        int indice = 0;
        //
        for (int i = 0; i < spAux.getCount(); i++) {
            String sAux = (String) spAux.getItemAtPosition(i);
            //
            if (sAux.equalsIgnoreCase(estado)){
                indice = i;
                //
                break;
            }
        }
        //
        return indice;
    }

    private void recuperarParametros() {
        idAtual = getIntent().getLongExtra(Constantes.PARAMETRO_ID, 0);
    }

    private void inicializarAcao() {
        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCliente();
            }
        });
        //
        bt_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caixaAlertaExluir();
            }
        });
    }

    private void caixaAlertaExluir() {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(TelaEdicaoCliente.this);

        alerta.setTitle("Cadastro de Clientes");
        alerta.setMessage("Deseja Realmente Excluir?");
        //
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clienteDao.apagarCliente(idAtual);
                //
                chamarLista();
            }
        });
        alerta.setNegativeButton("Não", null);
        //
        alerta.show();
    }

    private void salvarCliente() {
        Cliente cAux = new Cliente();
        //
        cAux.setNome(et_nome.getText().toString().trim());
        cAux.setEmail(et_email.getText().toString().trim());
        cAux.setTelefone(et_telefone.getText().toString());
        cAux.setCidade(et_cidade.getText().toString());
        cAux.setEstado(sp_estado.getSelectedItem().toString());
        //
        if (idAtual != -1) {
            cAux.setIdcliente(idAtual);
            //
            clienteDao.atualizarCliente(cAux);
        } else {
            idAtual = clienteDao.proximoID();

            cAux.setIdcliente(idAtual);
            //
            clienteDao.incluirCliente(cAux);
            //
            et_codigo.setText(String.valueOf(cAux.getIdcliente()));
            //
            bt_excluir.setVisibility(View.VISIBLE);
        }

        exibirNotificacao("Edição de Clientes",
                "Cliente " + cAux.getNome() + " salvo com sucesso!!!",
                cAux.getIdcliente()
        );
        exibirMensagem("Cliente Salvo com Sucesso");
    }

    private void exibirNotificacao(String titulo, String mensagem,Long idcliente) {
        Intent mIntent = new Intent(
                context,
                TelaEdicaoCliente.class
        );
        //
        mIntent.putExtra(Constantes.PARAMETRO_ID, idcliente);
        //
        PendingIntent pi = PendingIntent.getActivity(
                context,
                0,
                mIntent,
                0
        );
        //
        NotificationManager nm = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);
        //
        Notification.Builder notificacao =
                new Notification.Builder(context);
        //
        notificacao.setContentIntent(pi)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle(titulo)
                .setContentText(mensagem);
        //
        notificacao.setDefaults(
                Notification.DEFAULT_SOUND |
                        Notification.DEFAULT_VIBRATE);
        //
        int versao = Build.VERSION.SDK_INT;

        if (versao >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            nm.notify(
                    Constantes.ID_NOTIFICACAO_TELAEDICAOCLIENTES,
                    notificacao.build()
            );
        } else {
            nm.notify(
                    Constantes.ID_NOTIFICACAO_TELAEDICAOCLIENTES,
                    notificacao.getNotification()
            );
        }
    }

    private void exibirMensagem(String texto) {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(TelaEdicaoCliente.this);

        alerta.setTitle("Cadastro de Clientes");
        alerta.setMessage(texto);
        //
        alerta.setPositiveButton("Ok", null);
        //
        alerta.show();
    }

    private void chamarLista() {
        Intent mIntent = new Intent(context, TelaClientes.class);
        //
        startActivity(mIntent);
        //
        finish();
    }

    @Override
    public void onBackPressed() {
        caixaAlertaSair();
    }

    private void caixaAlertaSair() {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(TelaEdicaoCliente.this);

        alerta.setTitle("Cadastro de Clientes");
        alerta.setMessage("Deseja Realmente Sair?");
        //
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chamarLista();
            }
        });
        alerta.setNegativeButton("Não", null);
        //
        alerta.show();
    }
}
