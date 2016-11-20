package br.com.flpsno.h02_praticalistview.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.flpsno.h02_praticalistview.R;
import br.com.flpsno.h02_praticalistview.adapter.Adapter_Produtos;
import br.com.flpsno.h02_praticalistview.banco.Constantes;
import br.com.flpsno.h02_praticalistview.banco.HMAux;
import br.com.flpsno.h02_praticalistview.dao.ProdutoDao;

public class TelaProdutos extends AppCompatActivity {

    private Context context;
    //
    private Toolbar toolbar;
    //
    private ListView lv_produtos;
    //
    private Adapter_Produtos adapter_produtos;
    //
    private ProdutoDao produtoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaprodutos);
        //
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
        lv_produtos = (ListView) findViewById(R.id.telaprodutos_lv_produtos);
        //
        produtoDao = new ProdutoDao(context);
        //
        adapter_produtos = new Adapter_Produtos(
                context,
                R.layout.celulaprodutos,
                produtoDao.obterProdutos_hm()
        );
        //
        lv_produtos.setAdapter(adapter_produtos);
    }

    private void inicializarAcao() {
        lv_produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAux item = (HMAux) parent.getItemAtPosition(position);
                //
                chamarDetalhes(Long.parseLong(item.get(HMAux.ID)));
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent(context, MainActivity.class);
        startActivity(mIntent);
        //
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_incluir_contato) {

            chamarDetalhes(-1L);

            return true;
        }

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void chamarDetalhes(long idproduto) {
        Intent mIntent = new Intent(context, TelaEdicaoProduto.class);
        mIntent.putExtra(Constantes.PARAMETRO_ID, idproduto);
        //
        startActivity(mIntent);
        //
        finish();
    }
}
