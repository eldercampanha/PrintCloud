package com.example.elder.printstop.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.elder.printstop.R;
import com.example.elder.printstop.async.UpdateClienteAsyncTask;
import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.singleton.ClienteEmEvidencia;

public class UpdateCliente extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cliente);

        txtNome = (TextView)findViewById(R.id.txt_nome);
        txtEmail = (TextView)findViewById(R.id.txt_email);
        txtTelefone = (TextView)findViewById(R.id.txt_telefone);
    }

    public void btnSalvarClicked(View view){


        if(validaCampos()){

            ClienteEmEvidencia.getInstance().getCliente().setNome(txtNome.getText().toString());
            ClienteEmEvidencia.getInstance().getCliente().setEmail(txtEmail.getText().toString());
            ClienteEmEvidencia.getInstance().getCliente().setTelefone(txtTelefone.getText().toString());

            new UpdateClienteAsyncTask(new UpdateClienteAsyncTask.UpdateClienteAsyncTaskInterface() {
                @Override
                public void start() {

                }

                @Override
                public void success() {

                    UpdateCliente.this.finish();
                }

                @Override
                public void fail() {

                }

                @Override
                public void finish() {

                }
            }).execute(ClienteEmEvidencia.getInstance().getCliente());
        }
    }

    private boolean validaCampos() {
        return true;
    }

    public void btnCancelarClicked(View view){
        finish();
    }

}
