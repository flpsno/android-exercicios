package br.com.flpsno.h02_praticalistview.dao;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.flpsno.h02_praticalistview.R;
import br.com.flpsno.h02_praticalistview.model.Configuracao;

/**
 * Created by Felipe on 06/11/2016.
 */

public class ConfiguracaoDao {

    private static final String PREFERENCES_FILE_KEY = "preferences_file_key";
    private static final String SOMENTE_WIFI = "somente_wifi";
    private static final String USUARIO_WS = "usuario_ws";
    private static final String SENHA_WS = "senha_ws";
    //
    private Context context;


    public ConfiguracaoDao(Context context) {
        this.context = context;
    }

    public void atualizaConfiguracao(Configuracao configuracao) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                PREFERENCES_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(SOMENTE_WIFI, configuracao.isSOMENTE_WIFI());
        editor.putString(USUARIO_WS, configuracao.getUSUARIO_WS());
        editor.putString(SENHA_WS, configuracao.getSENHA_WS());
        editor.commit();
    }

    public Configuracao obterConfiguracao() {
        Configuracao cAux = new Configuracao();
        //
        SharedPreferences sharedPref = context.getSharedPreferences(
                PREFERENCES_FILE_KEY, Context.MODE_PRIVATE);
        //
        cAux.setSOMENTE_WIFI(sharedPref.getBoolean(SOMENTE_WIFI, true));
        cAux.setUSUARIO_WS(sharedPref.getString(USUARIO_WS, ""));
        cAux.setSENHA_WS(sharedPref.getString(SENHA_WS, ""));

        return cAux;
    }
}
