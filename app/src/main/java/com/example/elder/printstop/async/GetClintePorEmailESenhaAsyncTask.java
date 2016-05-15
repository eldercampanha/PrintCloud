package com.example.elder.printstop.async;

import android.database.SQLException;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.annotation.BoolRes;

import com.example.elder.printstop.model.Arquivos;
import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.model.dao.ClienteDao;
import com.example.elder.printstop.util.DBConnection;

import java.util.ArrayList;

/**
 * Created by Elder on 5/14/2016.
 */
public class GetClintePorEmailESenhaAsyncTask extends AsyncTask<String ,Integer, Cliente> {

    private GetClientePorEmailESenhaInterface mInterface;

    public interface GetClientePorEmailESenhaInterface{
        void start();
        void success(Cliente cliente);
        void fail (String error);
        void finish();
    }

    public GetClintePorEmailESenhaAsyncTask(GetClientePorEmailESenhaInterface getClientePorEmailESenhaInterface){
        mInterface = getClientePorEmailESenhaInterface;
    }

    @Override
    protected Cliente doInBackground(String... params) {

        String email = params[0];
        String senha = params[1];

        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.findClienteByEmailESenha(email, senha);
        DBConnection.closeConnection();
        return cliente;
    }

    @Override
    protected void onPostExecute(final Cliente cliente) {
        super.onPostExecute(cliente);
        if(cliente != null) {
            new GetAllClientsAsyncTask(new GetArquivosAsyncTask.GetArquivosAsyncTaskInterface() {
                @Override
                public void success(ArrayList<Arquivos> arquivos) {
                    cliente.setFiles(arquivos);
                    mInterface.success(cliente);
                }

                @Override
                public void fail(String error) {
                    mInterface.fail(error);
                }
            }).execute(cliente.getId());
        } else {
            mInterface.fail("GetClientePoremailESenhaAsyncTask Cliente = null");
        }
        DBConnection.closeConnection();
        mInterface.finish();
    }
}
