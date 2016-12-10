package br.com.flpsno.h02_praticalistview.model;

import br.com.flpsno.mylib.ToolBox;

/**
 * Created by Felipe on 06/11/2016.
 */

public class Configuracao {

    private boolean SOMENTE_WIFI;
    private String USUARIO_WS;
    private String SENHA_WS;

    public Configuracao() {
        this.SOMENTE_WIFI = false;
        this.USUARIO_WS = "sem usuario";
        this.SENHA_WS = "sem senha";
    }

    public Configuracao(boolean SOMENTE_WIFI, String USUARIO_WS, String SENHA_WS) {
        this.SOMENTE_WIFI = SOMENTE_WIFI;
        this.USUARIO_WS = USUARIO_WS;
        this.SENHA_WS = SENHA_WS;
    }

    public String getUSUARIO_WS() {
        return USUARIO_WS;
    }

    public void setUSUARIO_WS(String USUARIO_WS) {
        this.USUARIO_WS = USUARIO_WS;
    }

    public String getSENHA_WS() {
        return SENHA_WS;
    }

    public void setSENHA_WS(String SENHA_WS) {
        this.SENHA_WS = SENHA_WS;
    }

    public boolean isSOMENTE_WIFI() {
        return SOMENTE_WIFI;
    }

    public void setSOMENTE_WIFI(boolean SOMENTE_WIFI) {
        this.SOMENTE_WIFI = SOMENTE_WIFI;
    }
}
