package br.com.flpsno.h03_viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by nalmir on 01/10/2016.
 */
public class F01 extends Fragment {

    private Context context;

    private Button btn_mostrar_mensagem;
    private CheckBox cb_mostrar_mensagem;

    public interface ABotao {
        public void avisaraperto();
    }

    private ABotao delegate;

    public void setOnAcionadoListener(ABotao delegate) {
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.f01,
                container,
                false
        );
        //
        inicializarVariavel(view);
        inicializarAcao();
        //
        return view;
    }

    private void inicializarVariavel(View view) {
        context = getActivity();
        //
        btn_mostrar_mensagem = (Button)
                view.findViewById(R.id.f01_btn_motrar_mensagem);
        cb_mostrar_mensagem = (CheckBox)
                view.findViewById(R.id.f01_cb_motrar_mensagem);

    }

    private void inicializarAcao() {
        btn_mostrar_mensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_mostrar_mensagem.isChecked()) {
                    exibirMensagem();
                } else {
                    informarQueUmaAcaoAconteceu();
                }
            }
        });
    }

    private void exibirMensagem() {
        Toast.makeText(
                context,
                "Oi Nóis Aqui!!!",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void informarQueUmaAcaoAconteceu() {
        if (delegate != null) {
            delegate.avisaraperto();
        }
    }
}
