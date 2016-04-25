package com.example.elder.printstop.model;
import java.util.Date;
//import java.util.GregorianCalendar;

public class CaixaGeral {
    private int id;
    private String tipodeMovimento;
    private String formadePagamento;
    private float saldoAnterior;
    private float valor;
    private float saldoAtual;
    private Date data;
    private Date hora;

    public CaixaGeral() {
    }

    public CaixaGeral(int id, String tipodeMovimento, String formadePagamento, float saldoAnterior, float valor, float saldoAtual, Date data, Date hora) {
        this.id = id;
        this.tipodeMovimento = tipodeMovimento;
        this.formadePagamento = formadePagamento;
        this.saldoAnterior = saldoAnterior;
        this.valor = valor;
        this.saldoAtual = saldoAtual;
        this.data = data;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipodeMovimento() {
        return tipodeMovimento;
    }

    public void setTipodeMovimento(String tipodeMovimento) {
        this.tipodeMovimento = tipodeMovimento;
    }

    public String getFormadePagamento() {
        return formadePagamento;
    }

    public void setFormadePagamento(String formadePagamento) {
        this.formadePagamento = formadePagamento;
    }

    public float getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(float saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(float saldoAtual) {
        this.saldoAtual = saldoAtual;
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
    
    
}
