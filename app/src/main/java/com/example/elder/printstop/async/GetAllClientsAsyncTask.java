package com.example.elder.printstop.async;

import android.os.AsyncTask;

import com.example.elder.printstop.model.Arquivos;
import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.singleton.ClienteEmEvidencia;
import com.example.elder.printstop.util.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elder on 5/13/2016.
 */
public class GetAllClientsAsyncTask extends AsyncTask {

    private GetArquivosAsyncTask.GetArquivosAsyncTaskInterface mInterface;

    public interface  GetAllClientsAsyncTaskInterface{
        void success(ArrayList<Arquivos> arquivos);
        void fail(String error);
    }

    public GetAllClientsAsyncTask(GetArquivosAsyncTask.GetArquivosAsyncTaskInterface getArquivosAsyncTaskInterface){
        mInterface = getArquivosAsyncTaskInterface;
    }

    @Override
    protected Object doInBackground(Object[] params) {

//        String queryGetAllClientes = "SELECT * FROM pessoa, cliente WHERE idpessoa = fk_idpessoa_pessoa_cliente ORDER BY nome";
//
//        try {
//            ResultSet rs = DBConnection.getInstance().executeQuery(queryGetAllClientes);
//
//            ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
//
//            while (rs.next()) {
//                Cliente cliente = new Cliente();
//                cliente.setId(rs.getInt("idpessoa"));
//                cliente.setCpf(rs.getString("cpf"));
//                cliente.setNome(rs.getString("nome"));
//                cliente.setTelefone(rs.getString("telefone"));
//                cliente.setEmail(rs.getString("email"));
//                cliente.setSenha(rs.getString("senha"));
//                cliente.setSaldo(rs.getFloat("saldo"));
//                cliente.setFotoperfil("fotoperfil");
//                listaClientes.add(cliente);

//                DBConnection.getInstance().closeConnection();
                int clienteId = (int)params[0];

                String querySelect01 = "SELECT * FROM arquivos WHERE fk_idcliente_cliente_arquivos = " + clienteId;

        ResultSet rs = null;
        try {
            rs = DBConnection.getInstance().executeQuery(querySelect01);
            ArrayList<Arquivos> listaArquivos = new ArrayList<Arquivos>();

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

            mInterface.success(listaArquivos);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

               // cliente.setFiles(listaArquivos);

//                if(((String)params[0]).equals(cliente.getEmail())){
//                    ClienteEmEvidencia.getInstance().setCliente(cliente);
//                }

        return null;
    }

}
