package br.com.flpsno.h02_praticalistview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ParallelExecutorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TelaPedidos extends AppCompatActivity {

    private Context context;
    //
    private ListView lv_pedidos;
    //
    private SimpleAdapter adapter_pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telapedidos);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        lv_pedidos = (ListView) findViewById(R.id.telapedidos_lv_pedidos);
        //
        String[] De = {HMAux.TEXTO_01, HMAux.TEXTO_02, HMAux.TEXTO_03, HMAux.TEXTO_04};
        int[] Para = {R.id.celulapedidos_tv_codigo_pedido, R.id.celulapedidos_tv_cliente,
            R.id.celulapedidos_tv_total_item, R.id.celulapedidos_tv_valor_total};
        adapter_pedidos = new SimpleAdapter(
                context,
                gerarPedios(),
                R.layout.celulapedidos,
                De,
                Para
        );
        //
        lv_pedidos.setAdapter(adapter_pedidos);
    }

    private ArrayList<HMAux> gerarPedios() {
        ArrayList<HMAux> dados = new ArrayList<>();
        //
        for (int i = 0; i < pedidos.length ; i++) {
            HMAux item = new HMAux();
            item.put(HMAux.ID, String.valueOf(i+1));
            item.put(HMAux.TEXTO_01, pedidos[i]);
            item.put(HMAux.TEXTO_02, clientes[i]);
            item.put(HMAux.TEXTO_03, total_item[i]);
            item.put(HMAux.TEXTO_04, valor_total[i]);
            dados.add(item);
        }
        //
        return dados;
    }

    private void inicializarAcao() {

    }

    String pedidos[] = {
            "Pedido 7CA320",
            "Pedido 7C8AD8",
            "Pedido 7C1BBF",
            "Pedido 7BBE1B",
            "Pedido 785C05",
            "Pedido 7821DC",
            "Pedido 74EEAF",
            "Pedido 70B20A",
            "Pedido 6EEAF9",
            "Pedido 6D1F5B"
    };

    String clientes[] = {
            "Francielle Ramos",
            "Juh Valadão",
            "marcela cerqueira de paula",
            "Andréa Jannini Cangussú",
            "Luciana Castanheira",
            "Rafaele Hora",
            "marili da silva machado lariuxi",
            "Catarina Veloso",
            "Maristefany",
            "Isabelle Amorim Bezerra"
    };

    String total_item[] = {
            "1 produto",
            "1 produto",
            "1 produto",
            "2 produtos",
            "1 produto",
            "4 produtos",
            "1 produto",
            "5 produtos",
            "1 produto",
            "4 produtos"
    };

    String valor_total[] = {
            "R$ 50,00",
            "R$ 198,81",
            "R$ 63,31",
            "R$ 229,58",
            "R$ 240,08",
            "R$ 298,13",
            "R$ 148,94",
            "R$ 399,47",
            "R$ 205,30",
            "R$ 251,25"
    };

    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent(context, MainActivity.class);
        startActivity(mIntent);
        //
        this.finish();
    }
}
