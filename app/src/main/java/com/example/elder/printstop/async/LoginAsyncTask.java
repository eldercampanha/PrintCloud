package com.example.elder.printstop.async;

import android.os.AsyncTask;

import com.example.elder.printstop.util.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Elder on 4/24/2016.
 */
public class LoginAsyncTask extends AsyncTask<String,Integer,Boolean> {

    private LoginInterface mLoginInterface;

    public interface LoginInterface{
        void start();
        void sucess(boolean found);
        void error(String error);
    }

    public LoginAsyncTask(LoginInterface loginInterface){
        mLoginInterface = loginInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mLoginInterface.start();
    }

    @Override
    protected Boolean doInBackground(String... params) {

        String email = params[0];
        String senha = params[1];

        String sql = "SELECT count(*) FROM pessoa WHERE email = '"+email+"' AND senha = '"+senha+"'";
        boolean found = false;
        try {
            ResultSet rs = DBConnection.getInstance().executeQuery(sql);

            if(rs != null) {
                if (rs.next() && rs.getInt(1) > 0)
                    found = true;
            }
            DBConnection.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            mLoginInterface.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            mLoginInterface.error(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            mLoginInterface.error(e.getMessage());
        } catch (InstantiationException e) {
            e.printStackTrace();
            mLoginInterface.error(e.getMessage());
        } catch (NullPointerException e){
            //mLoginInterface.error(e.getMessage());
        }
        return found;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        mLoginInterface.sucess(aBoolean);
    }
}