package com.example.elder.printstop.model;

public class Administrador extends Pessoa{
    
    private String nivel;
    private String userName;

    public Administrador() {
    }

    public Administrador(String nivel, String userName, int id, String cpf, String nome, String telefone, String email, String senha) {
        super(id, cpf, nome, telefone, email, senha);
        this.nivel = nivel;
        this.userName = userName;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }
    
    
    
    
}
