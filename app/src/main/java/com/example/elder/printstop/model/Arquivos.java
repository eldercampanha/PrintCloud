package com.example.elder.printstop.model;


public class Arquivos {
    private int id;
    private String nome;
    private int tamanho;
    private int quantidadedePaginas;
    private float valor;
    private String caminho;

    public Arquivos() {
    }

    public Arquivos(int id, String nome, int tamanho, int quantidadedePaginas, float valor, String caminho) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.quantidadedePaginas = quantidadedePaginas;
        this.valor = valor;
        this.caminho = caminho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidadedepaginas() {
        return quantidadedePaginas;
    }

    public void setQuantidadedepaginas(int quantidadedepaginas) {
        this.quantidadedePaginas = quantidadedepaginas;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    
    
}
