package br.com.flpsno.h02_praticalistview.model;

import br.com.flpsno.h02_praticalistview.banco.Constantes;

/**
 * Created by Felipe on 17/10/2016.
 */

public class Pedido {

    private long IDPEDIDO;
    private String PEDIDO_ELO7;
    private String STATUS_ELO7;
    private String DATA_PEDIDO;
    private Double VALOR_TOTAL;
    private Double VALOR_FRETE;
    private String TIPO_FRETE;
    private String COMPRADOR;
    private String STATUS;

    public Pedido() {
        this.IDPEDIDO = -1;
        this.PEDIDO_ELO7 = "SEM PEDIDO";
        this.STATUS_ELO7 = "SEM STATUS";
        this.DATA_PEDIDO = "SEM DATA";
        this.VALOR_TOTAL = 0.0;
        this.VALOR_FRETE = 0.0;
        this.TIPO_FRETE = "SEM TIPO FRETE";
        this.COMPRADOR = "SEM COMPRADOR";
        this.STATUS = Constantes.STATUS_PEDIDO_OK;
    }

    private String DATA_PEDIDO2;

    public void setIDPEDIDO(long IDPEDIDO) {
        this.IDPEDIDO = IDPEDIDO;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String status) {
        this.STATUS = status;
    }

    public String getDATA_PEDIDO2() {
        return DATA_PEDIDO2;
    }

    public void setDATA_PEDIDO2(String DATA_PEDIDO2) {
        this.DATA_PEDIDO2 = DATA_PEDIDO2;
    }

    public String getCOMPRADOR() {
        return COMPRADOR;
    }

    public void setCOMPRADOR(String COMPRADOR) {
        this.COMPRADOR = COMPRADOR;
    }

    public Pedido(Long IDPEDIDO, String PEDIDO_ELO7, String STATUS_ELO7, String DATA_PEDIDO, Double VALOR_TOTAL,
                  Double VALOR_FRETE, String TIPO_FRETE, String COMPRADOR, String STATUS) {
        this.IDPEDIDO = IDPEDIDO;
        this.PEDIDO_ELO7 = PEDIDO_ELO7;
        this.STATUS_ELO7 = STATUS_ELO7;
        this.DATA_PEDIDO = DATA_PEDIDO;
        this.VALOR_TOTAL = VALOR_TOTAL;
        this.VALOR_FRETE = VALOR_FRETE;
        this.TIPO_FRETE = TIPO_FRETE;
        this.COMPRADOR = COMPRADOR;
        this.STATUS = STATUS;
    }

    public long getIDPEDIDO() {
        return IDPEDIDO;
    }

    public void setIDPEDIDO(Long IDPEDIDO) {
        this.IDPEDIDO = IDPEDIDO;
    }

    public String getPEDIDO_ELO7() {
        return PEDIDO_ELO7;
    }

    public void setPEDIDO_ELO7(String PEDIDO_ELO7) {
        this.PEDIDO_ELO7 = PEDIDO_ELO7;
    }

    public String getSTATUS_ELO7() {
        return STATUS_ELO7;
    }

    public void setSTATUS_ELO7(String STATUS_ELO7) {
        this.STATUS_ELO7 = STATUS_ELO7;
    }

    public String getDATA_PEDIDO() {
        return DATA_PEDIDO;
    }

    public void setDATA_PEDIDO(String DATA_PEDIDO) {
        this.DATA_PEDIDO = DATA_PEDIDO;
    }

    public Double getVALOR_TOTAL() {
        return VALOR_TOTAL;
    }

    public void setVALOR_TOTAL(Double VALOR_TOTAL) {
        this.VALOR_TOTAL = VALOR_TOTAL;
    }

    public Double getVALOR_FRETE() {
        return VALOR_FRETE;
    }

    public void setVALOR_FRETE(Double VALOR_FRETE) {
        this.VALOR_FRETE = VALOR_FRETE;
    }

    public String getTIPO_FRETE() {
        return TIPO_FRETE;
    }

    public void setTIPO_FRETE(String TIPO_FRETE) {
        this.TIPO_FRETE = TIPO_FRETE;
    }
}
