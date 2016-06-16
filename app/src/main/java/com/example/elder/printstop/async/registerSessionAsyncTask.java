package com.example.elder.printstop.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.model.dao.ClienteDao;
import com.example.elder.printstop.model.dao.SessoesDao;
import com.example.elder.printstop.util.DBConnection;

/**
 * Created by Elder on 2016-06-16.
 */
public class registerSessionAsyncTask extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] params) {

        int id = (int)params[0];
        float novoSaldo = (float)params[1];
        float saldoAntigo = (float)params[2];

        SessoesDao sessoesDao = new SessoesDao();
        int result = sessoesDao.registerSessao(id, novoSaldo, saldoAntigo);
        DBConnection.closeConnection();
        return null;
    }
}
