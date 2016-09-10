package br.com.flpsno.h02_praticalistview;

import android.content.Context;
import android.content.Intent;
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
    private Adapter_Clientes adapter_clientes;

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
        adapter_clientes = new Adapter_Clientes(
                context,
                R.layout.celulaclientes,
                gerarClientes()
        );
        lv_clientes.setAdapter(adapter_clientes);
    }

    private ArrayList<HMAux> gerarClientes() {
        ArrayList<HMAux> dados = new ArrayList<>();
        //
        for (int i = 0; i < nomes.length; i++) {
            HMAux item = new HMAux();
            item.put(HMAux.ID, String.valueOf(i + 1));
            item.put(HMAux.TEXTO_01, nomes[i]);
            item.put(HMAux.TEXTO_02, emails[i]);
            item.put(HMAux.TEXTO_03, telefones[i]);
            item.put(HMAux.TEXTO_04, estados[i]);
            dados.add(item);
        }
        //
        return dados;
    }

    private void inicializarAcao() {

    }

    String nomes[] = {
            "Barbara", "Liliane Ferreira de Oliveira", "Raquel Amaral", "Luciana Sadalla",
            "Marcela Resende Oliveira", "Monique class Almeida Vieira", "Patricia Gouvêa",
            "Cristiane Correia Cavalcante", "PAULA CAVIGLIA SCHADLICH", "Renata Ferreira Salvany",
    };

    String emails[] = {
            "babi_mota_souza@hotmail.com", "lilianellk@hotmail.com", "rakel_rocha@yahoo.com.br",
            "lucianafranklin@yahoo.com.br", "marcelaresende@gmail.com", "moniqueclass03@gmail.com",
            "plg.duarte@hotmail.com", "rocris2005@ig.com.br", "paulacaviglia@hotmail.com",
            "rena_luiza@hotmail.com",
    };

    String estados[] = {
            "RJ", "RJ", "AC", "SP", "RJ", "RJ", "SP", "SP", "SP", "RJ",
    };

    String telefones[] = {
            "39043608", "(24) 99858-0470", "", "50554839", "81916000", "(21) 99409-0573",
            "38448141", "56623638", "(11) 98122-1950",
            "",
    };

    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent(context, MainActivity.class);
        startActivity(mIntent);
        //
        this.finish();
    }
}
