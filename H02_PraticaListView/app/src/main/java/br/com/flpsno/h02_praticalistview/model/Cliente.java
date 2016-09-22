package br.com.flpsno.h02_praticalistview.model;

/**
 * Created by Felipe on 21/09/2016.
 */
public class Cliente {

    private long idcliente;
    private String nome;
    private String email;
    private String telefone;
    private String estado;

    public Cliente() {
        this.nome = "";
        this.email = "";
        this.telefone = "";
        this.estado = "";
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
