package com.example.elder.printstop.async;

import android.os.AsyncTask;

import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.model.dao.ClienteDao;

/**
 * Created by Elder on 5/16/2016.
 */
public class UpdateClienteAsyncTask extends AsyncTask {

    private UpdateClienteAsyncTaskInterface mInterface;

    public  interface UpdateClienteAsyncTaskInterface{
        void start();
        void success();
        void fail();
        void finish();
    }

    public UpdateClienteAsyncTask(UpdateClienteAsyncTaskInterface updateClienteAsyncTaskInterface){
        mInterface = updateClienteAsyncTaskInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mInterface.start();
    }

    @Override
    protected Object doInBackground(Object[] params) {

        Cliente cliente = (Cliente)params[0];
        ClienteDao clienteDao = new ClienteDao();
        int result = clienteDao.updateCliente(cliente);
        if(result > 0)
            mInterface.success();
        else
            mInterface.fail();

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        mInterface.finish();
    }
}
