package com.example.elder.printstop.model.dao;

import com.example.elder.printstop.async.GetAllClientsAsyncTask;
import com.example.elder.printstop.async.GetArquivosAsyncTask;
import com.example.elder.printstop.model.Arquivos;
import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.singleton.ClienteEmEvidencia;
import com.example.elder.printstop.util.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Elder on 5/14/2016.
 */
public class ClienteDao {


    public Cliente findClienteByEmailESenha(String email, String senha) {

        Cliente cliente = null;
        String queryGetAllClientes = "SELECT * FROM pessoa, cliente WHERE idpessoa = fk_idpessoa_pessoa_cliente and email = '" + email + "' and senha = '" + senha + "'";

        try {
            ResultSet rs = DBConnection.getInstance().executeQuery(queryGetAllClientes);
            if(rs == null)
                cliente = null;

            while (rs.next()) {
                cliente = new Cliente();

                cliente.setId(rs.getInt("idpessoa"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setSaldo(rs.getFloat("saldo"));
                cliente.setFotoperfil("fotoperfil");
            }

        } catch (IllegalAccessException e) {

            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

        }

        return cliente;
    }
}
