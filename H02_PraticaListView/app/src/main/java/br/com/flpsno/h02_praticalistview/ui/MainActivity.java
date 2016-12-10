package br.com.flpsno.h02_praticalistview.ui;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.R;
import br.com.flpsno.h02_praticalistview.banco.Constantes;
import br.com.flpsno.h02_praticalistview.banco.HMAux;
import br.com.flpsno.h02_praticalistview.dao.PedidoDao;
import br.com.flpsno.h02_praticalistview.model.Pedido;
import br.com.flpsno.h02_praticalistview.service.ServiceMain;
import br.com.flpsno.h02_praticalistview.transmissao.Transmissao_Env;
import br.com.flpsno.h02_praticalistview.transmissao.Transmissao_Rec;
import br.com.flpsno.mylib.ToolBox;

public class MainActivity extends AppCompatActivity {

    private static final long MENU_CLIENTES = 0L;
    private static final long MENU_PRODUTOS = 1L;
    private static final long MENU_PEDIDOS = 2L;

    private Context context;
    //
    private Toolbar toolbar;
    //
    private Transmissao_Rec rec;
    //
    private ProgressDialog progressDialog;
    //
    //
    private Drawer result;
    //
    private PrimaryDrawerItem item_clientes;
    private PrimaryDrawerItem item_produtos;
    private PrimaryDrawerItem item_pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        new DrawerBuilder().withActivity(this).build();

        inicializarVariavel();
        inicializarAcao();

        Intent mIntent = new Intent(MainActivity.this, ServiceMain.class);
        startService(mIntent);
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //
        //if you want to update the items at a later time it is recommended to keep it in a variable
        item_clientes = new PrimaryDrawerItem()
                .withIdentifier(MENU_CLIENTES)
                .withName("Clientes")
                .withIcon(R.mipmap.ic_launcher);
        item_produtos = new PrimaryDrawerItem()
                .withIdentifier(MENU_PRODUTOS)
                .withName("Produtos")
                .withIcon(R.mipmap.ic_launcher);
        item_pedidos = new PrimaryDrawerItem()
                .withIdentifier(MENU_PEDIDOS)
                .withName("Pedidos")
                .withIcon(R.mipmap.ic_launcher);
        //create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withSelectedItem(-1)
                .addDrawerItems(
                        item_clientes,
                        item_produtos,
                        item_pedidos
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {


                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        if (drawerItem.getIdentifier() == MENU_CLIENTES) {
                            Intent mIntentClientes = new Intent(MainActivity.this, TelaClientes.class);
                            startActivity(mIntentClientes);
                        }
                        //
                        if (drawerItem.getIdentifier() == MENU_PRODUTOS) {
                            Intent mIntentProdutos = new Intent(MainActivity.this, TelaProdutos.class);
                            startActivity(mIntentProdutos);
                        }
                        //
                        if (drawerItem.getIdentifier() == MENU_PEDIDOS) {
                            Intent mIntentPedidos = new Intent(MainActivity.this, TelaPedidos.class);
                            startActivity(mIntentPedidos);
                        }
                        //
                        return true;
                    }
                })
                .build();
    }

    private void inicializarAcao() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                chamarConfiguracoes();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void chamarConfiguracoes() {
        Intent mIntent = new Intent(context, TelaConfiguracoes.class);
        startActivity(mIntent);
        //
        finish();
    }

    private class Sincronismo extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Sincronizando os Pedidos");
            progressDialog.setMessage("Iniciando o sincronismo.");
            progressDialog.setIcon(R.mipmap.ic_launcher);
            //
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Integer... params) {

            try {
                Thread.sleep(1000);
                publishProgress(-1);
                Thread.sleep(1500);

                Gson gson = new Gson();
                //
                Transmissao_Env env = new Transmissao_Env();


                String resposta = ToolBox.comunicacao(
                        Constantes.WEB_WS_PEDIDO,
                        gson.toJson(env)
                );

                String[] resp = resposta.split("#WSFLPSNO#");

                rec = gson.fromJson(resp[1], Transmissao_Rec.class
                );
                //
                ArrayList<Pedido> pedidos = rec.getPedidos();
                //
                PedidoDao pedidoDao = new PedidoDao(context);
                //
                for (int i = 0; i < pedidos.size(); i++) {
                    Pedido pAux = new Pedido();
                    //
                    pAux = pedidos.get(i);

                    // se não encontrou, insere
                    if (pedidoDao.obterPedidoByID(pAux.getIDPEDIDO()) == null) {
                        pedidoDao.incluirPedido(pAux);

                        // senão, atualiza
                    } else {
                        pedidoDao.atualizarPedido(pAux);
                    }

                    publishProgress(i + 1, pedidos.size());
                }

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            int valor = values[0];

            if (valor == -1) {
                progressDialog.setMessage("Baixando pedidos do servidor.");
            } else {
                int valor2 = values[1];
                progressDialog.setMessage("Processando: " + String.valueOf(valor) + " de "
                        + String.valueOf(valor2));
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressDialog.dismiss();
        }
    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HMAux item = (HMAux) parent.getItemAtPosition(position);

            switch (Integer.parseInt(item.get(HMAux.ID))) {
                case 0:
                    Intent mIntentClientes = new Intent(MainActivity.this, TelaClientes.class);
                    startActivity(mIntentClientes);
                    break;
                case 1:
                    Intent mIntentProdutos = new Intent(MainActivity.this, TelaProdutos.class);
                    startActivity(mIntentProdutos);
                    break;
                case 2:
                    Intent mIntentPedidos = new Intent(MainActivity.this, TelaPedidos.class);
                    startActivity(mIntentPedidos);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
    /*    Intent mIntent = new Intent(MainActivity.this, ServiceMain.class);
        stopService(mIntent);*/

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
