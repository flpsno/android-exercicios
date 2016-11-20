package br.com.flpsno.h02_praticalistview.model;

/**
 * Created by Felipe on 06/11/2016.
 */

public class Configuracao {

    private boolean SOMENTE_WIFI;

    public Configuracao() {
        this.SOMENTE_WIFI = false;
    }

    public Configuracao(boolean SOMENTE_WIFI) {
        this.SOMENTE_WIFI = SOMENTE_WIFI;
    }

    public boolean isSOMENTE_WIFI() {
        return SOMENTE_WIFI;
    }

    public void setSOMENTE_WIFI(boolean SOMENTE_WIFI) {
        this.SOMENTE_WIFI = SOMENTE_WIFI;
    }
}
