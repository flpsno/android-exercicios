package br.com.flpsno.h02_praticalistview.model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Felipe on 27/11/2016.
 */

public class Log {

    private long idlog;
    private Date datalog;
    private Time horalog;
    private String titulo;
    private String conteudo;

    public Log(long idlog, Date datalog, Time horalog, String titulo, String conteudo) {
        this.idlog = idlog;
        this.datalog = datalog;
        this.horalog = horalog;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public Log() {
    }

    public long getIdlog() {
        return idlog;
    }

    public void setIdlog(long idlog) {
        this.idlog = idlog;
    }

    public Date getDatalog() {
        return datalog;
    }

    public void setDatalog(Date datalog) {
        this.datalog = datalog;
    }

    public Time getHoralog() {
        return horalog;
    }

    public void setHoralog(Time horalog) {
        this.horalog = horalog;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
