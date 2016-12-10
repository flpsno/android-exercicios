package br.com.flpsno.h02_praticalistview.model;

/**
 * Created by Felipe on 03/12/2016.
 */

public class Item {

    private Long ID;
    private String CODIGO;

    public Item(Long ID, String CODIGO) {
        this.ID = ID;
        this.CODIGO = CODIGO;

    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }
}
