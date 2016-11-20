package br.com.flpsno.h02_praticalistview.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.flpsno.h02_praticalistview.R;
import br.com.flpsno.h02_praticalistview.banco.Constantes;
import br.com.flpsno.h02_praticalistview.dao.PedidoDao;
import br.com.flpsno.h02_praticalistview.model.Pedido;

/**
 * Created by Felipe on 18/10/2016.
 */

public class TelaEdicaoPedido extends AppCompatActivity{

    private Context context;
    //
    private Toolbar toolbar;
    //
    private EditText et_codigo;
    private EditText et_cliente;
    private EditText et_valor_total;
    private EditText et_valor_frete;
    private EditText et_tipo_frete;
    private EditText et_status;
    //
    private Button bt_cancelar;
    private Button bt_salvar;
    //
    private PedidoDao pedidoDao;
    private long idAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaedicaopedido);

        inicializarVariavel();
        inicializarAcao();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //
        pedidoDao = new PedidoDao(context);
        //
        et_codigo = (EditText) findViewById(R.id.telaedicaopedido_et_codigo);
        et_cliente = (EditText) findViewById(R.id.telaedicaopedido_et_cliente);
        et_valor_total = (EditText) findViewById(R.id.telaedicaopedido_et_valor_total);
        et_valor_frete = (EditText) findViewById(R.id.telaedicaopedido_et_valor_frete);
        et_tipo_frete = (EditText) findViewById(R.id.telaedicaopedido_et_tipo_frete);
        et_status = (EditText) findViewById(R.id.telaedicaopedido_et_status);
        //
        bt_cancelar = (Button) findViewById(R.id.telaedicaopedido_bt_cancelar);
        bt_salvar = (Button) findViewById(R.id.telaedicaopedido_bt_salvar);
        //
        recuperarParametros();
        //
        if (idAtual != -1) {
            Pedido pAux = pedidoDao.obterPedidoByID(idAtual);
            //
            et_codigo.setText(pAux.getPEDIDO_ELO7());
            et_cliente.setText(pAux.getCOMPRADOR());
            et_valor_total.setText(String.valueOf(pAux.getVALOR_TOTAL()));
            et_valor_frete.setText(String.valueOf(pAux.getVALOR_FRETE()));
            et_tipo_frete.setText(pAux.getTIPO_FRETE());
            et_status.setText(pAux.getSTATUS_ELO7());
        }
    }

    private void inicializarAcao() {
        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarLista();
            }
        });
        //
        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarLista();
            }
        });
    }

    private void recuperarParametros() {
        idAtual = getIntent().getLongExtra(Constantes.PARAMETRO_ID, 0);
    }

    @Override
    public void onBackPressed() {
        caixaAlertaSair();
    }

    private void caixaAlertaSair() {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(TelaEdicaoPedido.this);

        alerta.setTitle("Cadastro de Pedidos");
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

    private void chamarLista() {
        Intent mIntent = new Intent(context, TelaPedidos.class);
        //
        startActivity(mIntent);
        //
        finish();
    }
}
