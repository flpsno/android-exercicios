package br.com.flpsno.h02_praticalistview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.banco.Constantes;
import br.com.flpsno.h02_praticalistview.dao.ConfiguracaoDao;
import br.com.flpsno.h02_praticalistview.dao.PedidoDao;
import br.com.flpsno.h02_praticalistview.model.Configuracao;
import br.com.flpsno.h02_praticalistview.model.Pedido;
import br.com.flpsno.h02_praticalistview.transmissao.Transmissao_Env;
import br.com.flpsno.h02_praticalistview.transmissao.Transmissao_Rec;
import br.com.flpsno.mylib.ToolBox;

/**
 * Created by Nalmir on 24/09/2016.
 */
public class AlarmeAcao extends BroadcastReceiver {

    private ConfiguracaoDao configuracaoDao;
    private Configuracao configuracao;
    //
    private String mensagem;

    @Override
    public void onReceive(Context context, Intent intent) {

        configuracaoDao = new ConfiguracaoDao(context);
        configuracao = configuracaoDao.obterConfiguracao();

        /*
        Toast.makeText(
                context,
                "Executado a Acao do Alarme!!!",
                Toast.LENGTH_SHORT
        ).show();*/
        //
        Log.d("ALARME", "Executou o Alarme!!!");
    }
}
