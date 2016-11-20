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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.R;
import br.com.flpsno.h02_praticalistview.adapter.Adapter_Clientes;
import br.com.flpsno.h02_praticalistview.banco.Constantes;
import br.com.flpsno.h02_praticalistview.banco.HMAux;
import br.com.flpsno.h02_praticalistview.dao.ClienteDao;

public class TelaClientes extends AppCompatActivity {

    private Context context;
    //
    private Toolbar toolbar;
    //
    private ListView lv_clientes;
    //
    private ArrayList<HMAux> clientes;
    private Adapter_Clientes adapter_clientes;
    //
    private ClienteDao clienteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaclientes);
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
        clienteDao = new ClienteDao(context);
        //
        lv_clientes = (ListView) findViewById(R.id.telaclientes_lv_clientes);
        //
        adapter_clientes = new Adapter_Clientes(
                context,
                R.layout.celulaclientes,
                clienteDao.obterClientes_hm()
        );
        lv_clientes.setAdapter(adapter_clientes);
    }

    private void inicializarAcao() {
        lv_clientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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

        if (id == android.R.id.home){
            onBackPressed();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void chamarDetalhes(long idproduto) {
        Intent mIntent = new Intent(context, TelaEdicaoCliente.class);
        mIntent.putExtra(Constantes.PARAMETRO_ID, idproduto);
        //
        startActivity(mIntent);
        //
        finish();
    }
}
