package br.com.flpsno.h02_praticalistview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TelaProdutos extends AppCompatActivity {

    private Context context;
    //
    private ListView lv_produtos;
    //
    private ArrayList<HMAux> produtos;
    private Adapter_Produtos adapter_produtos;

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
        //
        adapter_produtos = new Adapter_Produtos(
                context,
                R.layout.celulaprodutos,
                gerarProdutos()
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
            item.put(HMAux.TEXTO_03, String.valueOf(qtdmins[i]));
            item.put(HMAux.TEXTO_04, String.valueOf(precos[i]));
            dados.add(item);
        }
        //
        return dados;
    }

    private void inicializarAcao() {
        lv_produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAux item = (HMAux) parent.getItemAtPosition(position);
                //
                Intent mIntent = new Intent(context, TelaEdicaoProduto.class);
                mIntent.putExtra(Constantes.VALOR_IMAGEM_PRODUTO, Integer.parseInt(item.get(HMAux.TEXTO_01)));
                mIntent.putExtra(Constantes.VALOR_NOME_PRODUTO, item.get(HMAux.TEXTO_02));
                mIntent.putExtra(Constantes.VALOR_QTD_MINIMA,item.get(HMAux.TEXTO_03));
                mIntent.putExtra(Constantes.VALOR_PRECO,item.get(HMAux.TEXTO_04));
                startActivityForResult(mIntent, 10);
            }
        });

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

    int qtdmins[] = {
            20,
            20,
            20,
            20,
            20,
            20,
            20,
            20,
            20
    };

    double precos[] = {
            3.50,
            3.50,
            3.50,
            3.50,
            3.50,
            1.80,
            3.50,
            3.50,
            3.00
    };


    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent(context, MainActivity.class);
        startActivity(mIntent);
        //
        this.finish();
    }

}
