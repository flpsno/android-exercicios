package br.com.flpsno.h02_praticalistview.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.flpsno.h02_praticalistview.R;
import br.com.flpsno.h02_praticalistview.banco.Constantes;
import br.com.flpsno.h02_praticalistview.dao.ProdutoDao;
import br.com.flpsno.h02_praticalistview.model.Produto;

/**
 * Created by Felipe on 10/09/2016.
 */
public class TelaEdicaoProduto extends AppCompatActivity {

    private Context context;
    //
    private EditText et_codigo;
    private EditText et_descricao;
    private EditText et_qtd_minima;
    private EditText et_preco;
    private Button bt_salvar;
    private Button bt_excluir;
    //
    private ProdutoDao produtoDao;
    private long idAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaedicaoproduto);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        produtoDao = new ProdutoDao(context);
        //
        et_codigo = (EditText) findViewById(R.id.telaedicaoproduto_et_codigo);
        et_descricao = (EditText) findViewById(R.id.telaedicaoproduto_et_descricao);
        et_qtd_minima = (EditText) findViewById(R.id.telaedicaoproduto_et_qtd_minima);
        et_preco = (EditText) findViewById(R.id.telaedicaoproduto_et_preco);
        bt_salvar = (Button) findViewById(R.id.telaedicaoproduto_bt_salvar);
        bt_excluir = (Button) findViewById(R.id.telaedicaoproduto_bt_excluir);
        //
        recuperarParametros();
        //
        if (idAtual != -1) {
            Produto pAux = produtoDao.obterProdutoByID(idAtual);
            //
            et_codigo.setText(String.valueOf(pAux.getIdproduto()));
            et_descricao.setText(String.valueOf(pAux.getDescricao()));
            et_qtd_minima.setText(String.valueOf(pAux.getQtd_min()));
            et_preco.setText(String.valueOf(pAux.getValor_unitario()));
            //
            bt_excluir.setVisibility(View.VISIBLE);
        } else {
            bt_excluir.setVisibility(View.GONE);
        }
    }

    private void recuperarParametros() {
        idAtual = getIntent().getLongExtra(Constantes.PARAMETRO_ID, 0);
    }

    private void inicializarAcao() {
        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarContato();
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

    private void salvarContato() {
        Produto pAux = new Produto();
        //
        pAux.setDescricao(et_descricao.getText().toString().trim());
        pAux.setQtd_min(Integer.parseInt(et_qtd_minima.getText().toString().trim()));
        pAux.setValor_unitario(Double.parseDouble(et_preco.getText().toString()));
        //
        if (idAtual != -1) {
            pAux.setIdproduto(idAtual);
            //
            produtoDao.atualizarProduto(pAux);
        } else {
            idAtual = produtoDao.proximoID();

            pAux.setIdproduto(idAtual);
            //
            produtoDao.incluirProduto(pAux);
            //
            et_codigo.setText(String.valueOf(pAux.getIdproduto()));
          //
            bt_excluir.setVisibility(View.VISIBLE);
        }

        exibirNotificacao("Edição de Produtos", "Produto " + pAux.getDescricao() + " salvo com sucesso!!!", (int) pAux.getIdproduto());
//        exibirMensagem("Produto Salvo com Sucesso");
    }

    private void exibirMensagem(String texto) {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(TelaEdicaoProduto.this);

        alerta.setTitle("Cadastro de Produtos");
        alerta.setMessage(texto);
        //
        alerta.setPositiveButton("Ok", null);
        //
        alerta.show();
    }

    @Override
    public void onBackPressed() {
        caixaAlertaSair();
    }

    private void caixaAlertaSair() {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(TelaEdicaoProduto.this);

        alerta.setTitle("Cadastro de Produtos");
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

    private void caixaAlertaExluir() {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(TelaEdicaoProduto.this);

        alerta.setTitle("Cadastro de Produtos");
        alerta.setMessage("Deseja Realmente Excluir?");
        //
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                produtoDao.apagarProduto(idAtual);
                //
                chamarLista();
            }
        });
        alerta.setNegativeButton("Não", null);
        //
        alerta.show();
    }

    private void exibirNotificacao(String titulo, String mensagem, int idnotificacao) {
        Intent mIntent = new Intent(
                context,
                MainActivity.class
        );
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

        int versao = Build.VERSION.SDK_INT;

        if (versao >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            nm.notify(
                    idnotificacao,
                    notificacao.build()
            );
        } else {
            nm.notify(
                    idnotificacao,
                    notificacao.getNotification()
            );
        }
    }

    private void chamarLista() {
        Intent mIntent = new Intent(context, TelaProdutos.class);
        //
        startActivity(mIntent);
        //
        finish();
    }
}
