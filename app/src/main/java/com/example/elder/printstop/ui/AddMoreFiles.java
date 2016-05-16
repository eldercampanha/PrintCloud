package com.example.elder.printstop.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.elder.printstop.R;

public class AddMoreFiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_more_files);
    }

    public void btnScanClicked(View view){
        finish();
    }
}
