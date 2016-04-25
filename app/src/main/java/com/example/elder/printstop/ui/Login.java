package com.example.elder.printstop.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elder.printstop.R;
import com.example.elder.printstop.async.LoginAsyncTask;
import com.example.elder.printstop.util.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends AppCompatActivity {

    private TextView txtEmail;
    private TextView txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        testarConexao();

        txtEmail = (TextView)findViewById(R.id.txt_email);
        txtSenha = (TextView)findViewById(R.id.txt_senha);
    }

    private void testarConexao() {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                try {
//                    Class.forName("com.mysql.jdbc.Driver").newInstance();
//                    Connection conn = DriverManager.getConnection("jdbc:mysql://10.0.3.2:3306/mycloudprinterteste2", "admin", "");
//                    PreparedStatement stm =  conn.prepareStatement("INSERT INTO tabeladeprecos (descricao, valor)  VALUES ('teste2', '12')");
//                    stm.executeUpdate();
//                    conn.close();

//                try {
//                    DBConnection.getInstance().executeUpdate("INSERT INTO tabeladeprecos (descricao, valor)  VALUES ('teste2', '12')");
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }


//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }

//            }
//        }).start();
    }


    public void btnLogInClicked (View view){


        String email = txtEmail.getText().toString();
        String senha = txtSenha.getText().toString();

        if(verificaCampos())
            new LoginAsyncTask(new LoginAsyncTask.LoginInterface(){
                @Override
                public void start() {
                    showMEsssage("Logging...");
                }

                @Override
                public void sucess(boolean found) {
                    if(found) {
                        showMEsssage("Success");
                        Intent intent = new Intent(Login.this, MainScreen.class);
                        startActivity(intent);
                    }else
                        showMEsssage("Fail");


                }

                @Override
                public void error(String error) {
                    showMEsssage(error);
                }
            }).execute(email, senha);
        else{
            //TRATAR ERROR
        }
            //
    }

    private boolean verificaCampos() {
        return true;
    }

    public void showMEsssage(String msg){
//        Toast.makeText(this,msg, Toast.LENGTH_SHORT );
        Log.i("SSS",msg);
    }
}
