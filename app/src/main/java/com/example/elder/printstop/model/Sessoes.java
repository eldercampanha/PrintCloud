package com.example.elder.printstop.model;
import java.util.Date;
//import java.util.GregorianCalendar;


public class Sessoes {
    private int idSessao;
    private Date data;
    private Date horaInicio;
    private Date horaFim;

    public Sessoes() {
    }

    public Sessoes(int idSessao, Date data, Date horaInicio, Date horaFim) {
        this.idSessao = idSessao;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }
    
    
}
