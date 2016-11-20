package br.com.flpsno.h02_praticalistview.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.R;
import br.com.flpsno.h02_praticalistview.adapter.Adapter_Pedidos;
import br.com.flpsno.h02_praticalistview.banco.Constantes;
import br.com.flpsno.h02_praticalistview.banco.HMAux;
import br.com.flpsno.h02_praticalistview.dao.PedidoDao;

public class TelaPedidos extends AppCompatActivity {

    private Context context;
    //
    private Toolbar toolbar;
    //
    private ListView lv_pedidos;
    //
    private Adapter_Pedidos adapter_pedidos;
    //
    private PedidoDao pedidoDao;
    //
    private String codigo;
    private String comprador;
    private String status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telapedidos);
        //
        inicializarVariavel();
        inicializarAcao();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

/*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.telapedidos,
                container,
                false
        );
        //
        inicializarVariavel(view);
        inicializarAcao();
        //
        return view;
    }*/

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //
        lv_pedidos = (ListView) findViewById(R.id.telapedidos_lv_pedidos);
        //
        pedidoDao = new PedidoDao(context);
        //
        adapter_pedidos = new Adapter_Pedidos(
                context,
                R.layout.celulapedidos,
                pedidoDao.obterPedidosTodos_hm()
        );
        //
        lv_pedidos.setAdapter(adapter_pedidos);
    }

    private void inicializarAcao() {
        lv_pedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAux item = (HMAux) parent.getItemAtPosition(position);
                //
                chamarDetalhes(Long.parseLong(item.get(HMAux.ID)));
            }
        });
    }

    private void chamarDetalhes(long idpedido) {
        Intent mIntent = new Intent(context, TelaEdicaoPedido.class);
        mIntent.putExtra(Constantes.PARAMETRO_ID, idpedido);
        //
        startActivity(mIntent);
        //
        finish();
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pedidos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_pedidos_filtrar:
                montar_filtro();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void montar_filtro() {
        LayoutInflater mInflater = LayoutInflater.from(context);
        //
        final View textEntryView = mInflater.inflate(R.layout.filtropedidos, null);
        //
        final EditText et_codigo = (EditText) textEntryView.findViewById(R.id.filtropedidos_et_codigo);
        final EditText et_comprador = (EditText) textEntryView.findViewById(R.id.filtropedidos_et_comprador);
        final Spinner sp_status = (Spinner) textEntryView.findViewById(R.id.filtropedidos_sp_status);
        //
        final ArrayAdapter adapter_status = new ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                gerarStatus()
        );
        adapter_status.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        //
        sp_status.setAdapter(adapter_status);
        //
        et_codigo.setText(codigo);
        et_comprador.setText(comprador);
        //
        AlertDialog.Builder alerta = new AlertDialog.Builder(TelaPedidos.this);
        alerta.setIcon(R.mipmap.ic_launcher);
        alerta.setTitle("Filtro dos Pedidos");
        alerta.setView(textEntryView);
        //
        alerta.setNegativeButton("Cancelar", null);
        alerta.setPositiveButton("Filtrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                codigo = et_codigo.getText().toString();
                comprador = et_comprador.getText().toString();
                status = sp_status.getSelectedItem().toString();
                //

                adapter_pedidos = new Adapter_Pedidos(
                        context,
                        R.layout.celulapedidos,
                        pedidoDao.obterPedidosComFiltro_hm(codigo, comprador, status)
                );
                //
                lv_pedidos.setAdapter(adapter_pedidos);
            }
        });
        //
        alerta.show();
    }

    private ArrayList<String> gerarStatus() {
        ArrayList<String> status = new ArrayList<>();
        //
        status.add("Aguardando Frete");
        status.add("Aguardando Pagamento");
        status.add("Pago");
        status.add("Finalizado");
        //
        return status;
    }
}
