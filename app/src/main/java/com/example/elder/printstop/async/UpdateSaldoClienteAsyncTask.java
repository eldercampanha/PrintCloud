package com.example.elder.printstop.async;

import android.os.AsyncTask;
import android.speech.tts.Voice;

import com.example.elder.printstop.model.dao.ClienteDao;
import com.example.elder.printstop.util.DBConnection;

/**
 * Created by Elder on 5/16/2016.
 */
public class UpdateSaldoClienteAsyncTask extends AsyncTask<Object, Void, Object> {

    private UpdateSaldoClienteAsyncTaskInterface mInterface;

    public UpdateSaldoClienteAsyncTask(UpdateSaldoClienteAsyncTaskInterface updateSaldoClienteAsyncTaskInterface){
        mInterface = updateSaldoClienteAsyncTaskInterface;
    }

    public interface UpdateSaldoClienteAsyncTaskInterface{
        void sucess();
        void fail();
    }

    @Override
    protected Void doInBackground(Object... params) {

        int id = (int)params[0];
        float novoSaldo = (float)params[1];

        ClienteDao clienteDao = new ClienteDao();
        int result = clienteDao.updateSaldoCliente(id, novoSaldo);
        if(result > 0){
            mInterface.sucess();
        }
        else
            mInterface.fail();
        DBConnection.closeConnection();
        return null;
    }


}
