package br.com.flpsno.h02_praticalistview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Button bt_clientes;
    private Button bt_itens;
    private Button bt_pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teladashboard);

        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        bt_clientes = (Button) findViewById(R.id.teladashboard_bt_clientes);
        bt_itens = (Button) findViewById(R.id.teladashboard_bt_itens);
        bt_pedidos = (Button) findViewById(R.id.teladashboard_bt_pedidos);
    }

    private void inicializarAcao() {
        bt_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTela(v);
            }
        });
        //
        bt_itens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTela(v);
            }
        });
        //
        bt_pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTela(v);
            }
        });
    }

    private void chamaTela(View view) {

        switch (view.getId()) {
            case R.id.teladashboard_bt_clientes:
                Intent telaclientes = new Intent(this, TelaClientes.class);
                startActivity(telaclientes);
                break;
            case R.id.teladashboard_bt_itens:
                Intent telaitens = new Intent(this, TelaItens.class);
                startActivity(telaitens);
                break;
            case R.id.teladashboard_bt_pedidos:
                Intent telapedidos = new Intent(this, TelaPedidos.class);
                startActivity(telapedidos);
                break;
        }
    }
}
