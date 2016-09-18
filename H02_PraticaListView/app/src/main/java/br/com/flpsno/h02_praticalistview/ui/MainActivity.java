package br.com.flpsno.h02_praticalistview.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.flpsno.h02_praticalistview.R;

public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Button bt_clientes;
    private Button bt_produtos;
    private Button bt_pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teladashboard);

        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        bt_clientes = (Button) findViewById(R.id.teladashboard_bt_clientes);
        bt_produtos = (Button) findViewById(R.id.teladashboard_bt_itens);
        bt_pedidos = (Button) findViewById(R.id.teladashboard_bt_pedidos);
    }

    private void inicializarAcao() {
        bt_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, TelaClientes.class);
                startActivity(mIntent);
                finish();
            }
        });
        //
        bt_produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, TelaProdutos.class);
                startActivity(mIntent);
                finish();
            }
        });
        //
        bt_pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, TelaPedidos.class);
                startActivity(mIntent);
                finish();
            }
        });
    }
}
