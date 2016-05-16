package com.example.elder.printstop.async;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.elder.printstop.model.Arquivos;
import com.example.elder.printstop.model.dao.ArquivosDao;
import com.example.elder.printstop.util.DBConnection;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Elder on 5/14/2016.
 */
public class GetArquivosAsyncTask extends AsyncTask <Integer, Integer, ArrayList<Arquivos>> {

    private GetArquivosAsyncTaskInterface mInterface;

    public interface GetArquivosAsyncTaskInterface{
        void success(ArrayList<Arquivos> arquivos);
        void fail(String error);
    }

    public GetArquivosAsyncTask(GetArquivosAsyncTaskInterface getArquivosAsyncTaskInterface){
        mInterface = getArquivosAsyncTaskInterface;
    }

    @Override
    protected ArrayList<Arquivos> doInBackground(Integer... params) {

        int id = params[0];

        ArquivosDao arquivosDao = new ArquivosDao();
        ArrayList<Arquivos> listaArquivos = arquivosDao.getFilesByClienteId(id);

        if(listaArquivos != null)mInterface.success(listaArquivos);
        DBConnection.closeConnection();
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Arquivos> arquivoses) {
        super.onPostExecute(arquivoses);
    }
}
