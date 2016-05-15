package com.example.elder.printstop.model.dao;

import android.database.SQLException;

import com.example.elder.printstop.async.GetArquivosAsyncTask;
import com.example.elder.printstop.model.Arquivos;
import com.example.elder.printstop.util.DBConnection;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Elder on 5/14/2016.
 */
public class ArquivosDao {

    public ArrayList<Arquivos> getFilesByClienteId(int id) {

        String querySelect01 = "SELECT * FROM arquivos WHERE fk_idcliente_cliente_arquivos = " + id;
        ArrayList<Arquivos> listaArquivos = new ArrayList<Arquivos>();
        ResultSet rs = null;

        try {
            rs = DBConnection.getInstance().executeQuery(querySelect01);

            while(rs.next()) {
                Arquivos arquivo = new Arquivos();
                arquivo.setId(rs.getInt("idarquivo"));
                arquivo.setNome(rs.getString("nome"));
                arquivo.setTamanho(rs.getLong("tamanho"));
                arquivo.setQuantidadedePaginas(rs.getInt("quantidadedepaginas"));
                arquivo.setValor(rs.getFloat("valor"));
                arquivo.setCaminho(rs.getString("caminhodoarquivo"));
                arquivo.setFk_id_cliente(rs.getInt("fk_idcliente_cliente_arquivos"));
                listaArquivos.add(arquivo);
            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            String error = e.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return listaArquivos;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
