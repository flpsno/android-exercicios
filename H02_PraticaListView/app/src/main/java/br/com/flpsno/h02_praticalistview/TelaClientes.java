package br.com.flpsno.h02_praticalistview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TelaClientes extends AppCompatActivity {

    private Context context;
    //
    private ListView lv_clientes;
    //
    private ArrayList<HMAux> clientes;
    private SimpleAdapter adapter_clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaclientes);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        lv_clientes = (ListView) findViewById(R.id.telaclientes_lv_clientes);
        //
        String[] De = {HMAux.TEXTO_01, HMAux.TEXTO_02, HMAux.TEXTO_03, HMAux.TEXTO_04};
        int[] Para = {R.id.celulaclientes_tv_nome, R.id.celulaclientes_tv_email,
                R.id.celulaclientes_tv_telefone, R.id.celulaclientes_tv_estado};
        adapter_clientes = new SimpleAdapter(
                context,
                gerarClientes(100),
                R.layout.celulaclientes,
                De,
                Para
        );
        lv_clientes.setAdapter(adapter_clientes);
    }

    private ArrayList<HMAux> gerarClientes(int quantidade) {
        ArrayList<HMAux> dados = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            HMAux item = new HMAux();
            item.put(HMAux.ID, String.valueOf(i));
            item.put(HMAux.TEXTO_01, "Nome do Cliente - " + String.valueOf(i));
            item.put(HMAux.TEXTO_02, "clientes" + String.valueOf(i) + "@gmail.com");
            item.put(HMAux.TEXTO_03, "11 1234-4568");
            item.put(HMAux.TEXTO_04, "SP");
            dados.add(item);
        }
        //
        return dados;
    }

    private void inicializarAcao() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
