package com.example.elder.printstop.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.elder.printstop.R;
import com.example.elder.printstop.async.LoginAsyncTask;
import com.example.elder.printstop.model.Pessoa;
import com.example.elder.printstop.util.DBConnection;

public class Login extends AppCompatActivity {

    private TextView txtEmail;
    private TextView txtSenha;
    private TextView txtAuthentificationError;
    private ProgressDialog progress;
    private WebView my_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        testarConexao();

        txtEmail = (TextView)findViewById(R.id.txt_email);
        txtSenha = (TextView)findViewById(R.id.txt_senha);
        txtAuthentificationError = (TextView)findViewById(R.id.txt_authentification_error);

        progress = new ProgressDialog(Login.this);
        progress.setTitle( "Loggin in..");
        progress.setMessage("Please wait");
        progress.setIndeterminate(true);

    }


    private void testarConexao() {
    }

    public void btnLogInClicked (View view){

        final String email = txtEmail.getText().toString();
        final String senha = txtSenha.getText().toString();

        if(verificaCampos())

            new LoginAsyncTask(new LoginAsyncTask.LoginInterface(){
                @Override
                public void start() {
                    showMEsssage("Logging...");
                    progress.show();
                }

                @Override
                public void finish() {
                    progress.hide();
                }

                @Override
                public void sucess(boolean found) {
                    if(found) {

                        //TODO RETORNAR OBJETO NO CASO DE SUCESS
                        Pessoa.eviencia.setEmail(email);
                        Pessoa.eviencia.setSenha(senha);

                        showMEsssage("Success");
                        Login.this.finish();
                        Intent intent = new Intent(Login.this, MainScreen.class);
                        startActivity(intent);
                        try{
                            DBConnection.getInstance().closeConnection();
                        } catch ( Exception ex){
                            ex.printStackTrace();
                        }
                    }else {
                        showMEsssage("Fail");
                        txtAuthentificationError.setVisibility(View.VISIBLE);
                    }

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
        Log.i("SSS",msg);
    }
}
