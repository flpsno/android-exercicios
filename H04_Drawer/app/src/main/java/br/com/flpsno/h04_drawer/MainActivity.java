package br.com.flpsno.h04_drawer;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    private Context context;
    //
  /*  private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    //
    private FOpc fOpc;
    private FContent fContent;

    private FragmentManager fm;*/
    //
    private Toolbar toolbar;
    //
    private Drawer drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
        //
       // mDrawerToggle.syncState();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //
        /*fm = getSupportFragmentManager();
        //
        fOpc = (FOpc)
                fm.findFragmentById(R.id.telainicial_fopc);
        fOpc.setOnAcionar_Mudar_NomeListener(new FOpc.IFOpc() {
            @Override
            public void acionar_mudar_nome(String nome) {
                fContent.setNome(nome);
                //
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        fContent = (FContent)
                fm.findFragmentById(R.id.telainicial_fcontent);
        //
        mDrawerLayout = (DrawerLayout)
                findViewById(R.id.telainicial_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                mDrawerLayout,
                R.string.drawer_aberto,
                R.string.drawer_fechado
        ) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //
                invalidateOptionsMenu();
            }
        } ;
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //
        mDrawerLayout.setDrawerListener(mDrawerToggle);*/

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Produtos");
        item1.withBadge("20");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Perfil");

//create the drawer and remember the `Drawer` result object
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Toast.makeText(context, "pos: " + position, Toast.LENGTH_SHORT).show();
                        switch (position) {
                            case 1:

                                break;
                            case 2:

                                break;
                        }
                        //
                        //
                        drawer.closeDrawer();
                        return true;
                    }
                })
                .build();
    }

    private void inicializarAcao() {

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

      /*  if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }*/
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
