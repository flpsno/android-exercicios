package br.com.flpsno.h03_viewpager;

import android.support.v4.app.Fragment;

/**
 * Created by nalmir on 01/10/2016.
 */
public class CfgP {

    private String titulo;
    private Fragment frg;

    public CfgP(String titulo, Fragment frg) {
        this.titulo = titulo;
        this.frg = frg;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Fragment getFrg() {
        return frg;
    }

    public void setFrg(Fragment frg) {
        this.frg = frg;
    }
}
