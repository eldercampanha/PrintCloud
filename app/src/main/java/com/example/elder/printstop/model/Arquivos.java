package com.example.elder.printstop.model;


public class Arquivos {
    private int id;
    private String nome;
    private long tamanho;
    private int quantidadedePaginas;
    private float valor;
    private String caminho;
    private int fk_id_cliente;

    public Arquivos() {
    }

    public Arquivos(int id, String nome, int tamanho, int quantidadedePaginas, float valor, String caminho, int fk_id_cliente) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.quantidadedePaginas = quantidadedePaginas;
        this.valor = valor;
        this.caminho = caminho;
        this.fk_id_cliente = fk_id_cliente;
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

    public long getTamanho() {
        return tamanho;
    }

    public void setTamanho(long tamanho) {
        this.tamanho = tamanho;
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

    public int getQuantidadedePaginas() {
        return quantidadedePaginas;
    }

    public void setQuantidadedePaginas(int quantidadedePaginas) {
        this.quantidadedePaginas = quantidadedePaginas;
    }

    public int getFk_id_cliente() {
        return fk_id_cliente;
    }

    public void setFk_id_cliente(int fk_id_cliente) {
        this.fk_id_cliente = fk_id_cliente;
    }
}
