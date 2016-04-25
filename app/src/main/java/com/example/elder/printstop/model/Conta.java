package com.example.elder.printstop.model;
import java.util.Date;
//import java.util.GregorianCalendar;


public class Conta {
    private int id;
    private Date data;
    private Date hora;
    private float valor;
    private String tipodeMovimentacao;
    private float saldoAnterior;
    private float saldoAtual;

    public Conta() {
    }

    public Conta(int id, Date data, Date hora, float valor, String tipodeMovimentacao, float saldoAnterior, float saldoAtual) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.tipodeMovimentacao = tipodeMovimentacao;
        this.saldoAnterior = saldoAnterior;
        this.saldoAtual = saldoAtual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTipodemovimentacao() {
        return tipodeMovimentacao;
    }

    public void setTipodemovimentacao(String tipodemovimentacao) {
        this.tipodeMovimentacao = tipodemovimentacao;
    }

    public float getSaldoanterior() {
        return saldoAnterior;
    }

    public void setSaldoanterior(float saldoanterior) {
        this.saldoAnterior = saldoanterior;
    }

    public float getSaldoatual() {
        return saldoAtual;
    }

    public void setSaldoatual(float saldoatual) {
        this.saldoAtual = saldoatual;
    }
    
    
}
