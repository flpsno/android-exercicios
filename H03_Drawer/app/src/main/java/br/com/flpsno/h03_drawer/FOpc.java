package br.com.flpsno.h03_drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by nalmir on 08/10/2016.
 */
public class FOpc extends Fragment {

    private Button btn_mudar_nome;

    public interface IFOpc {
        public void acionar_mudar_nome(String nome);
    }

    private IFOpc delegate;

    public void setOnAcionar_Mudar_NomeListener(IFOpc delegate) {
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fopc, container, false);
        //
        inicialiarVariavel(view);
        inicializraAcao();
        //
        return view;
    }

    private void inicialiarVariavel(View view) {
        btn_mudar_nome = (Button)
                view.findViewById(R.id.fopc_btn_mudar_nome);
    }

    private void inicializraAcao() {
        btn_mudar_nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delegate != null){
                    delegate.acionar_mudar_nome("Android N(24) ");
                }
            }
        });
    }
}
