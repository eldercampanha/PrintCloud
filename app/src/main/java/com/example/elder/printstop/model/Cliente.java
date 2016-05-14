package com.example.elder.printstop.model;

import java.util.ArrayList;

public class Cliente extends Pessoa{
    private float saldo;
    private String fotoPerfil;
    private ArrayList<Arquivos> files = new ArrayList<>();


    public Cliente() {
    }

    public Cliente(float saldo, String fotoPerfil, int id, String cpf, String nome, String telefone, String email, String senha) {
        super(id, cpf, nome, telefone, email, senha);
        this.saldo = saldo;
        this.fotoPerfil = fotoPerfil;
        files = new ArrayList<>();
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getFotoperfil() {
        return fotoPerfil;
    }

    public void setFotoperfil(String fotoperfil) {
        this.fotoPerfil = fotoperfil;
    }

    public ArrayList<Arquivos> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<Arquivos> files) {
        this.files = files;
    }
    
}
