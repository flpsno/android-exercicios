package br.com.flpsno.h03_viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class    MainActivity extends AppCompatActivity {

    private Context context;
    //
    private ViewPager pager;
    private ArrayList<CfgP> telas;

    private F01 f01;
    private F02 f02;
    private F03 f03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        pager = (ViewPager)
                findViewById(R.id.telainicial_pager);
        //
        gerarTelas();
    }

    private void gerarTelas() {
        f01 = new F01();
        f01.setOnAcionadoListener(new F01.ABotao() {
            @Override
            public void avisaraperto() {
                pager.setCurrentItem(
                        pager.getAdapter().getCount()-1
                );
            }
        });
        f02 = new F02();
        f02.setOnNomeSelecionadoListener(new F02.IListas() {
            @Override
            public void acionarMudancaTexto(String texto) {
                f03.mudarValor(texto);
            }
        });
        f03 = new F03();
        //
        telas = new ArrayList<>();
        telas.add(new CfgP("Titulo 01", f01));
        telas.add(new CfgP("Lista de Nome", f02));
        telas.add(new CfgP("Texto Fina", f03));
        //
        pager.setAdapter(
                new TelasAdapter(
                        getSupportFragmentManager(),
                        telas
                )
        );
    }

    private void inicializarAcao() {

    }

    private class TelasAdapter extends FragmentPagerAdapter{

        private ArrayList<CfgP> telas;

        public TelasAdapter(FragmentManager fm, ArrayList<CfgP> telas) {
            super(fm);
            this.telas = telas;
        }

        @Override
        public Fragment getItem(int position) {
            return telas.get(position).getFrg();
        }

        @Override
        public int getCount() {
            return telas.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return telas.get(position).getTitulo();
        }
    }

}
