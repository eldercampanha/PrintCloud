package com.example.elder.printstop.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.elder.printstop.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        testarConexao();
    }

    private void testarConexao() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Connection conn = DriverManager.getConnection("jdbc:mysql://10.0.3.2:3306/mycloudprinterteste2", "admin", "");
                    PreparedStatement stm =  conn.prepareStatement("INSERT INTO tabeladeprecos (descricao, valor)  VALUES ('teste2', '12')");
                    stm.executeUpdate();
                    conn.close();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void btnLogInClicked (View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
}
