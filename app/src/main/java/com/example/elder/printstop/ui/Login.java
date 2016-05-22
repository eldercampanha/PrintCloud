package com.example.elder.printstop.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.elder.printstop.R;
import com.example.elder.printstop.async.GetClintePorEmailESenhaAsyncTask;
import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.singleton.ClienteEmEvidencia;

public class Login extends AppCompatActivity {

    private TextView txtEmail;
    private TextView txtSenha;
    private TextView txtAuthentificationError;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        testarConexao();

        txtEmail = (TextView)findViewById(R.id.lbl_email);
        txtSenha = (TextView)findViewById(R.id.txt_senha);
        txtAuthentificationError = (TextView)findViewById(R.id.txt_authentification_error);

        progress = new ProgressDialog(Login.this);
        progress.setTitle( "Loggin in..");
        progress.setMessage("Please wait");
        progress.setIndeterminate(true);
        progress.setCancelable(false);

    }


    private void testarConexao() {
    }

    public void btnLogInClicked (View view){

        final String email = txtEmail.getText().toString();
        final String senha = txtSenha.getText().toString();

        if(verificaCampos())
            updateEvidenceClienteFromWebService(email,senha);
        else
            txtAuthentificationError.setVisibility(View.VISIBLE);
    }

    private void updateEvidenceClienteFromWebService(String email, String senha) {
        showMEsssage("Logging...");
        progress.show();

        new GetClintePorEmailESenhaAsyncTask(new GetClintePorEmailESenhaAsyncTask.GetClientePorEmailESenhaInterface() {
            @Override
            public void start() {

            }

            @Override
            public void success(Cliente cliente) {
                ClienteEmEvidencia.getInstance().setCliente(cliente);
                Login.this.finish();
                Intent intent = new Intent(Login.this, MainScreen.class);
                startActivity(intent);
            }

            @Override
            public void fail(String error) {
                        showMEsssage("Fail");
                        txtAuthentificationError.setVisibility(View.VISIBLE);
                        progress.hide();
            }

            @Override
            public void finish() {
            }
        }).execute(email, senha);

    }

    private boolean verificaCampos() {

        return txtEmail.getText().toString() != "" && txtSenha.getText().toString() != "";
    }

    public void showMEsssage(String msg){
        Log.i("SSS",msg);
    }

}
