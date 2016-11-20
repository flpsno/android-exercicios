package br.com.flpsno.h03_viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by nalmir on 01/10/2016.
 */
public class F02 extends Fragment {

    private Context context;
    //
    private ListView lv_nomes;
    private ArrayList<String> nomes;
    private ArrayAdapter<String> adapter_nomes;

    //
    public interface IListas {
        public void acionarMudancaTexto(String texto);
    }

    private IListas delegate;

    public void setOnNomeSelecionadoListener(IListas delegate) {
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.f02,
                container,
                false
        );

        inicializarVariavel(view);
        inicializarAcao();

        return view;
    }

    private void inicializarVariavel(View view) {
        context = getActivity();
        //
        lv_nomes = (ListView)
                view.findViewById(R.id.f02_lv_nomes);
        //
        gerarNomes();

    }

    private void inicializarAcao() {
        lv_nomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nome = (String) parent.getItemAtPosition(position);
                //
                if (delegate != null) {
                    delegate.acionarMudancaTexto(nome);
                }
            }
        });
    }

    private void gerarNomes() {
        nomes = new ArrayList<>();
        //
        nomes.add("Android");
        nomes.add("iOs");
        nomes.add("Windows Phone");
        nomes.add("Black Berry");
        nomes.add("Symbian");
        nomes.add("WebOs");
        //
        adapter_nomes = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                nomes
        );
        //
        lv_nomes.setAdapter(adapter_nomes);
    }
}
