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
import com.example.elder.printstop.model.Arquivos;
import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.model.Pessoa;
import com.example.elder.printstop.singleton.ClienteEmEvidencia;
import com.example.elder.printstop.util.DBConnection;

import java.util.ArrayList;

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
        updateEvidenceClienteFromWebService();
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
                      //  updateEvidenceClienteFromWebService();
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

    private void updateEvidenceClienteFromWebService() {

        ClienteEmEvidencia.getInstance().getCliente().setNome("Teste");
        ClienteEmEvidencia.getInstance().getCliente().setCpf("1234567-7");
        ClienteEmEvidencia.getInstance().getCliente().setEmail("teste@gmail.com");
        ClienteEmEvidencia.getInstance().getCliente().setSenha("12345");
        ClienteEmEvidencia.getInstance().getCliente().setFotoperfil("path to foto de perfil");
        ClienteEmEvidencia.getInstance().getCliente().setSaldo(10);
        ClienteEmEvidencia.getInstance().getCliente().setTelefone("(16)1234-1234");


        String[] file = new String[5];
        file[0] = "http://www.milfont.org/blog/wp-content/upload/Manual.pdf";
        file[1] = "https://www.novatec.com.br/livros/objective-c/capitulo9788575222911.pdf";
        file[2] = "http://sisfiesportal.mec.gov.br/arquivos/oe_1_fies.pdf";
        file[3] = "http://www.ista.org/forms/2Aoverview.pdf";
        file[4] = "https://www.nibo.com.br/wp-content/uploads/2014/03/Como-abrir-uma-empresa-em-4-passos.pdf";

        Arquivos arquivo;
        ClienteEmEvidencia.getInstance().getCliente().setFiles(new ArrayList<Arquivos>());
        for(int i = 0 ;  i< 5; i++){
            arquivo = new Arquivos();
            arquivo.setCaminho(file[i]);
            arquivo.setNome("File "+ i);
            ClienteEmEvidencia.getInstance().getCliente().getFiles().add(arquivo);
        }
    }

    private boolean verificaCampos() {
        return true;
    }

    public void showMEsssage(String msg){
        Log.i("SSS",msg);
    }

}
