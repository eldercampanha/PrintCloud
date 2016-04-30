package com.example.elder.printstop.model;

import java.util.ArrayList;

public class Pessoa {
    
    private int id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    public static Pessoa eviencia = new Pessoa();
    private ArrayList<Arquivos> files = new ArrayList<>();


    public Pessoa() {
    }

    public Pessoa(int id, String cpf, String nome, String telefone, String email, String senha) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public ArrayList<Arquivos> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<Arquivos> files) {
        this.files = files;
    }
}
