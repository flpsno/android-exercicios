package br.com.flpsno.h02_praticalistview.model;

/**
 * Created by Felipe on 21/09/2016.
 */
public class Cliente {

    private long idcliente;
    private String nome;
    private String email;
    private String telefone;
    private String cidade;
    private String estado;
    private Status status;

    public Cliente() {
        this.nome = "SEM NOME";
        this.email = "SEM EMAIL";
        this.telefone = "SEM TELEFONE";
        this.cidade = "SEM CIDADE";
        this.estado = "SEM ESTADO";
    }

    public Status getSTATUS() {
        return status;
    }

    public void setSTATUS(Status STATUS) {
        this.status = STATUS;
    }
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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

    public enum Status {
        OK, INSERIR, ATUALIZAR, EXCLUIR
    }
}
