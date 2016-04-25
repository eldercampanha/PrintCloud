package com.example.elder.printstop.model;

public class Cliente extends Pessoa{
    private float saldo;
    private String fotoPerfil;

    public Cliente() {
    }

    public Cliente(float saldo, String fotoPerfil, int id, String cpf, String nome, String telefone, String email, String senha) {
        super(id, cpf, nome, telefone, email, senha);
        this.saldo = saldo;
        this.fotoPerfil = fotoPerfil;
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
    
    
    
}
