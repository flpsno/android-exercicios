package br.com.flpsno.h02_praticalistview.model;

/**
 * Created by Felipe on 03/12/2016.
 */

public class StatusPedido {

    private Long IDSTATUSPEDIDO;
    private String CODIGO;
    private String DESCRICAO;

    public StatusPedido(Long IDSATTUSPEDIDO, String CODIGO, String DESCRICAO) {
        this.IDSTATUSPEDIDO = IDSATTUSPEDIDO;
        this.CODIGO = CODIGO;
        this.DESCRICAO = DESCRICAO;
    }

    public StatusPedido() {
        this.IDSTATUSPEDIDO = -1L;
        this.CODIGO = "Sem Código";
        this.DESCRICAO = "Sem Descrição";
    }

    public Long getIDSTATUSPEDIDO() {
        return IDSTATUSPEDIDO;
    }

    public void setIDSTATUSPEDIDO(Long IDSTATUSPEDIDO) {
        this.IDSTATUSPEDIDO = IDSTATUSPEDIDO;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public String getDESCRICAO() {
        return DESCRICAO;
    }

    public void setDESCRICAO(String DESCRICAO) {
        this.DESCRICAO = DESCRICAO;
    }
}
