package com.example.elder.printstop.ui;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.example.elder.printstop.AddMoreFiles;
import com.example.elder.printstop.R;
import com.example.elder.printstop.adapter.RecyclerViewAdapterMainScreen;
import com.example.elder.printstop.model.FileToPrint;
import com.example.elder.printstop.model.Pessoa;
import com.example.elder.printstop.util.MyWebViewClient;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapterMainScreen mRecyclerViewAdapterMainScreen;
    private ProgressDialog progress;
    private WebView webview;
    private String printCloudInfoUrl = "https://lh4.ggpht.com/IOAolyiGssaiu7zU7RDqoamzRzsugMaCGCDj8RteqmU3MCvp1Bel_rZVCWc0uxY3lRsP=w300";
    public static FileToPrint selectedPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        setWebView();
        setRecyclerView();
        loadWebView(printCloudInfoUrl);
    }

    public void setRecyclerView(){
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapterMainScreen = new RecyclerViewAdapterMainScreen(this,getPdfFilesOnDevice());
        mRecyclerView.setAdapter(mRecyclerViewAdapterMainScreen);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_main_files_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
    }

    public void setWebView(){
        progress = new ProgressDialog(this);
        progress.setTitle( "Loading Page..");
        progress.setMessage("Please wait");
        progress.setIndeterminate(true);
        webview = (WebView)findViewById(R.id.my_webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.setWebViewClient(new MyWebViewClient(this,new MyWebViewClient.WebClienteInterface() {
            @Override
            public void start() { progress.show();}
            @Override
            public void finish() { progress.dismiss();}
        }));

    }
    public void loadWebView(String url){
        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
    }


    public ArrayList<FileToPrint> getFilesForUser(Pessoa pessoa){

        //RETORNAR ARQUIVOS DA PESSOA NO BANCO

        ArrayList<FileToPrint> fakeDeleteDepois = new ArrayList<>();

        FileToPrint f1 = new FileToPrint();
        f1.setName("File 1");
        f1.setFileSize(200);
        f1.setFileLocation("https://www.novatec.com.br/livros/objective-c/capitulo9788575222911.pdf");

        fakeDeleteDepois.add(f1);
        return fakeDeleteDepois;
    }

    public void btnHelpClicked(View view){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    public void btnAddMoreFilesClicked (View view){
        Intent intent = new Intent(this, AddMoreFiles.class);
        startActivity(intent);
    }

    public ArrayList<FileToPrint> getPdfFilesOnDevice() {

        ArrayList<FileToPrint> files = new ArrayList<>();
        String[] arquivos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).list();
        String pathUSB  = getApplicationContext().getExternalCacheDir().getAbsolutePath();
        Log.i("MainScreen", Environment.getExternalStorageDirectory().getAbsolutePath());// + APP_THUMBNAIL_PATH_SD_CARD;)
        Log.i("MainScreen", pathUSB);// + APP_THUMBNAIL_PATH_SD_CARD;)

        if(arquivos != null) {
            for (String s : arquivos) {
                FileToPrint file = new FileToPrint();
                file.setName(s);
                file.setFileSize(20);
                files.add(file);
            }
        }
        return files;
    }

    public void btnPrinterClicked(View view){
        createWebPrintJob(webview);

    }

    private void createWebPrintJob(WebView webView) {

            // Get a PrintManager instance
            PrintManager printManager = (PrintManager) this
                    .getSystemService(Context.PRINT_SERVICE);

            // Get a print adapter instance
            PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

            // Create a print job with name and adapter instance
            String jobName = getString(R.string.app_name) + " Document";
            PrintJob printJob = printManager.print(jobName, printAdapter,
                    new PrintAttributes.Builder().build());

            // Save the job object for later status checking
         //   mPrintJobs.add(printJob);
    }


    public void btnLogoutClicked(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
