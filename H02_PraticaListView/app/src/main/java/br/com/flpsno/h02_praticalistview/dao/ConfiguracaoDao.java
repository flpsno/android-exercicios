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
        editor.commit();
    }

    public Configuracao obterConfiguracao() {
        Configuracao cAux = new Configuracao();
        //
        SharedPreferences sharedPref = context.getSharedPreferences(
                PREFERENCES_FILE_KEY, Context.MODE_PRIVATE);
        //
        cAux.setSOMENTE_WIFI(sharedPref.getBoolean(SOMENTE_WIFI, true));

        return cAux;
    }
}
