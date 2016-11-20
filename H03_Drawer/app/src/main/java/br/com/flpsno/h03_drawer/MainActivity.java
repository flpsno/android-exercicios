package br.com.flpsno.h03_drawer;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    //
    private FOpc fOpc;
    private FContent fContent;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
        //
        mDrawerToggle.syncState();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        fm = getSupportFragmentManager();
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
        mDrawerLayout.setDrawerListener(mDrawerToggle);
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

        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
