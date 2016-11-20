package br.com.flpsno.h03_drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nalmir on 08/10/2016.
 */
public class FContent extends Fragment {

    private TextView tv_nome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fcontent, container, false);
        //
        inicializarVariavel(view);
        inicializarAcao();
        //
        return view;
    }

    private void inicializarVariavel(View view) {
        tv_nome = (TextView)
                view.findViewById(R.id.fcontent_tv_nome);
    }

    private void inicializarAcao() {

    }

    public void setNome(String nome){
        tv_nome.setText(nome);
    }
}
