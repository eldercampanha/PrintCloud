package com.example.elder.printstop.singleton;

import com.example.elder.printstop.model.Arquivos;
import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.model.Pessoa;

import java.util.ArrayList;

/**
 * Created by Elder on 2016-04-30.
 */
public class ClienteEmEvidencia {

    private Cliente cliente = null;

    private static ClienteEmEvidencia ourInstance = new ClienteEmEvidencia();

    public static ClienteEmEvidencia getInstance() {
        return ourInstance;
    }
    private ClienteEmEvidencia() {
    }

    public Cliente getCliente(){
        if(this.cliente == null)
            cliente = new Cliente();

        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCliente(String nome, String email, ArrayList<Arquivos> files, float saldo ){
        getCliente().setNome(nome);
        getCliente().setEmail(email);
        getCliente().setFiles(files);
        getCliente().setSaldo(saldo);
    }

}
