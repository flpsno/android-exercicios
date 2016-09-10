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

public class TelaProdutos extends AppCompatActivity {

    private Context context;
    //
    private ListView lv_produtos;
    //
    private ArrayList<HMAux> produtos;
    private SimpleAdapter adapter_produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaprodutos);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        lv_produtos = (ListView) findViewById(R.id.telaprodutos_lv_produtos);
        //
        String[] De = {HMAux.TEXTO_01, HMAux.TEXTO_02, HMAux.TEXTO_03, HMAux.TEXTO_04};
        int[] Para = {R.id.celulaprodutos_iv_produto, R.id.celulaprodutos_tv_nome_produto, R.id.celulaprodutos_tv_qtd_min,
                R.id.celulaprodutos_tv_preco};
        adapter_produtos = new SimpleAdapter(
                context,
                gerarProdutos(),
                R.layout.celulaprodutos,
                De,
                Para
        );
        //
        lv_produtos.setAdapter(adapter_produtos);
    }

    private ArrayList<HMAux> gerarProdutos() {
        ArrayList<HMAux> dados = new ArrayList<>();
        //
        for (int i = 0; i < fotos.length; i++) {
            HMAux item = new HMAux();
            item.put(HMAux.ID, String.valueOf(i + 1));
            item.put(HMAux.TEXTO_01, String.valueOf(fotos[i]));
            item.put(HMAux.TEXTO_02, nomes[i]);
            item.put(HMAux.TEXTO_03, "Qtd. Min: " + qtdmins[i]);
            item.put(HMAux.TEXTO_04, precos[i]);
            dados.add(item);
        }
        //
        return dados;
    }

    private void inicializarAcao() {

    }

    int fotos[] = {
            R.drawable.caixa_para_lembrancinha_princesa,
            R.drawable.caixa_cone_pokemonpokemon_go,
            R.drawable.lembrancinha_realeza_festa_principe,
            R.drawable.caixa_lacinho_festa_real,
            R.drawable.caixa_coroa_cha_de_fraldas,
            R.drawable.bis_duplo_realeza_cha_de_fraldas,
            R.drawable.lembrancinha_realeza_cha_de_bebe,
            R.drawable.caixa_para_lembrancinha_festa_realeza,
            R.drawable.livro_de_colorir_pokemon_pokemon
    };

    String nomes[] = {
            "Caixa para lembrancinha",
            "Caixa Cone Pokémon",
            "Lembrancinha Realeza",
            "Caixa Lacinho",
            "Caixa Coroa",
            "Bis Duplo Realeza",
            "Lembrancinha Realeza",
            "Caixa para lembrancinha",
            "Livro de colorir Pokemon"
    };

    String qtdmins[] = {
            "20",
            "20",
            "20",
            "20",
            "20",
            "20",
            "20",
            "20",
            "20"
    };

    String precos[] = {
            "R$ 3,50",
            "R$ 3,50",
            "R$ 3,50",
            "R$ 3,50",
            "R$ 3,50",
            "R$ 1,80",
            "R$ 3,50",
            "R$ 3,50",
            "R$ 3,00"
    };


    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent(context, MainActivity.class);
        startActivity(mIntent);
        //
        this.finish();
    }

}
