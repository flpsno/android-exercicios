package br.com.flpsno.h02_praticalistview.model;

/**
 * Created by Felipe on 17/09/2016.
 */
public class Produto {

    private long idproduto;
    private String codigo;
    private String descricao;
    private int qtd_min;
    private double valor_unitario;

    public Produto() {
        this.codigo = "";
        this.descricao = "";
        this.qtd_min = 0;
        this.valor_unitario = 0;
    }

    public long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(long idproduto) {
        this.idproduto = idproduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtd_min() {
        return qtd_min;
    }

    public void setQtd_min(int qtd_min) {
        this.qtd_min = qtd_min;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }
}
