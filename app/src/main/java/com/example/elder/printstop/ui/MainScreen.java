package com.example.elder.printstop.ui;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import com.example.elder.printstop.R;
import com.example.elder.printstop.adapter.PrintJobAdapter;
import com.example.elder.printstop.adapter.RecyclerViewAdapterMainScreen;
import com.example.elder.printstop.async.DownloadPdfAsyncTask;
import com.example.elder.printstop.async.UpdateSaldoClienteAsyncTask;
import com.example.elder.printstop.async.printAsyncTask;
import com.example.elder.printstop.model.Arquivos;
import com.example.elder.printstop.model.Cliente;
import com.example.elder.printstop.model.FileToPrint;
import com.example.elder.printstop.singleton.ClienteEmEvidencia;
import com.example.elder.printstop.util.MyWebViewClient;
import com.mysql.management.util.Str;

import java.io.File;
import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private TextView txtName;
    private TextView txtSaldo;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapterMainScreen mRecyclerViewAdapterMainScreen;
    private ProgressDialog progress;
    private WebView webview;
    private Cliente _cliente;
    private int selectedFileIndex;
    private PrintJob pj;
    private BroadcastReceiver receiver;
    private PrintManager printManager;
    private ArrayList<Long> downloadedFilesId = new ArrayList();
    private ArrayList<PrintJob> printJobList = new ArrayList();
    private ArrayList downloadedFilesIndex = new ArrayList();
    final IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

    @Override
    protected void onStop() {
        if(receiver != null && receiver.isOrderedBroadcast()) unregisterReceiver(receiver);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        txtName = (TextView)findViewById(R.id.txt_user_name);
        txtSaldo = (TextView)findViewById(R.id.txt_user_saldo);
        printManager = (PrintManager) MainScreen.this.getSystemService(Context.PRINT_SERVICE);
        _cliente = ClienteEmEvidencia.getInstance().getCliente();

        updateScreen();
        setProgressDialog(getString(R.string.LoadingPage), getString(R.string.PleaseWait));
        setWebView();
        setRecyclerView();
        setReceiver();

        //LIST ALL DOWNLOADED FILES ON DEVICE
        getAllFilesOfDir(Environment.getExternalStoragePublicDirectory(Environment.MEDIA_MOUNTED));
    }

    private void setProgressDialog(String title, String message) {
        progress = new ProgressDialog(this);
        progress.setTitle(title);
        progress.setMessage(message);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
    }

    private void getAllFilesOfDir(File directory) {
        Log.i("SSS", "Directory: " + directory.getAbsolutePath() + "\n");
        final File[] files = directory.listFiles();
        if ( files != null ) {
            for ( File file : files ) {
                if ( file != null ) {
                    if ( file.isDirectory() ) {  // it is a folder...
                        for(String s : file.list()){
                            Log.i("SSS", "File in the folder: " + s + "\n");
                        }
                        getAllFilesOfDir(file);
                    }
                    else {  // it is a file...
                        Log.i("SSS", "File: " + file.getAbsolutePath() + "\n");
                    }
                }
            }
        }
    }

    private void updateScreen() {
        txtName.setText(ClienteEmEvidencia.getInstance().getCliente().getNome());
        txtSaldo.setText(String.format("R$ %.2f", ClienteEmEvidencia.getInstance().getCliente().getSaldo()));
    }

    public void setRecyclerView(){
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_main_files_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapterMainScreen = new RecyclerViewAdapterMainScreen(this,
                ClienteEmEvidencia.getInstance().getCliente().getFiles(),
                new RecyclerViewAdapterMainScreen.RecyclerViewAdapterMainScreenInterface() {
                    @Override
                    public void fileCliked(int file) {
                        selectedFileIndex = file;
                        loadWebView(ClienteEmEvidencia.getInstance().getCliente().getFiles().get(file).getNome(), ClienteEmEvidencia.getInstance().getCliente().getCpf());
                    }
                });
        mRecyclerView.setAdapter(mRecyclerViewAdapterMainScreen);
    }

    public void setWebView(){
        webview = (WebView)findViewById(R.id.my_webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setWebViewClient(new MyWebViewClient(this,new MyWebViewClient.WebClienteInterface() {
            @Override
            public void start() {
                progress.show();
            }
            @Override
            public void finish() { progress.dismiss();}
        }));
    }
    public void loadWebView(String name, String cpf){

        setProgressDialog(getString(R.string.LoadingPage), getString(R.string.PleaseWait));
        try {
            //Server na Casa do Rodolfo
            String url =getString(R.string.driveGooglePdfViewrURL)+
                        getString(R.string.rodolfoHouseAplicationServerAddress)
                        +cpf+"/"+name;

            //Server Online
//            String url =getString(R.string.driveGooglePdfViewrURL)+
//                        getString(R.string.serverOnlineAplicationServerAddress)
//                        +name;

            //url = url.replace(" ", "%20");
            Log.i("SSS","Load WebView url - " + url);
            webview.loadUrl(url);
        } catch (Exception ex){
            Log.i("SSS", ex.getMessage());
            ex.printStackTrace();
        }
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

        if(selectedFileIndex > -1 && selectedFileIndex <
                ClienteEmEvidencia.getInstance().getCliente().getFiles().size()) {

            //baixar e imprimir pela casa do Rodolfo
            createWebPrintJob(getString(R.string.rodolfoHouseAplicationServerAddress),
                    _cliente.getCpf(),_cliente.getFiles().get(selectedFileIndex).getNome());

//            //baixar e imprimir pelo servidor online
//            createWebPrintJob(getString(R.string.serverOnlineAplicationServerAddress),
//                    _cliente.getCpf(),_cliente.getFiles().get(selectedFileIndex).getNome());
        }
    }

    private void updateSaldoCliente(float novoSaldo) {

        new UpdateSaldoClienteAsyncTask(new UpdateSaldoClienteAsyncTask.UpdateSaldoClienteAsyncTaskInterface() {

            @Override
            public void sucess() {}
            @Override
            public void fail() {}

        }).execute(_cliente.getId(),novoSaldo);
    }

    public void showDadosUsuarioClicked (View view){
        Intent intent = new Intent(this, UpdateCliente.class);
        startActivity(intent);
    }

    private boolean createWebPrintJob(String url,final String cpf, final String name) {

        for(PrintJob pj : printJobList){
            printManager.getPrintJobs().remove(pj);
        }

      //CASA DO ROLDOLFO
        url = url + "/" +cpf+ "/" + name;

//      //SERVIDOR ONLINE
//        url = url + name;
        Uri Download_Uri = Uri.parse(url.replace(" ","%20"));
        Log.i("SSS", "Main Screen Link pra Download " + url);
        if(pj != null )printManager.getPrintJobs().get(0).cancel();



        if(downloadedFilesIndex.contains(selectedFileIndex)){
            printDownloadedFile(name);
        } else {
            new DownloadPdfAsyncTask(this, new DownloadPdfAsyncTask.DownloadPdfAsyncTaskInterface() {
                @Override

                public void onStart() {
                    setProgressDialog(getString(R.string.DownloadingFile), getString(R.string.PleaseWait));
                    progress.show();
                }

                @Override
                public void onFinish(long id) {
                    Log.i("SSS", "donwload id - " + id);
                    MainScreen.this.downloadedFilesId.add(id);
                    downloadedFilesIndex.add(selectedFileIndex);
                }
            }).execute(Download_Uri, name);
        }

        return true;
    }

    private void setReceiver(){

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                progress.hide();
                setProgressDialog(getString(R.string.LoadingPage), getString(R.string.PleaseWait));
                printDownloadedFile(ClienteEmEvidencia.getInstance().getCliente().getFiles().get(selectedFileIndex).getNome());
            }
        };
        registerReceiver(receiver, filter);
    }
    private void atualizarSaldo(int numberOfPages){

        float novoSaldo = ClienteEmEvidencia.getInstance().getCliente().getSaldo();

        Arquivos file = ClienteEmEvidencia.getInstance().getCliente().getFiles().get(selectedFileIndex);

        float preco = (file.getValor() / Float.valueOf(String.valueOf(file.getQuantidadedePaginas())));

        if(numberOfPages == -1)
            novoSaldo -= file.getValor();
        else
            novoSaldo -= preco * numberOfPages;

                ClienteEmEvidencia.getInstance().getCliente().setSaldo(novoSaldo);
                updateSaldoCliente(novoSaldo);
                updateScreen();
                pj.cancel();
//                txtSaldo.setText(String.format("R$ %.2f" + novoSaldo));
    }
    private void printDownloadedFile(String name) {

        final String jobName = MainScreen.this.getString(R.string.app_name) + " - " + name;
        setProgressDialog("Printing..",null);
        PrintJobAdapter adpater = new PrintJobAdapter(name,new PrintJobAdapter.PrintJobAdapterInterface() {
            @Override
            public void onFinish(PrintJobAdapter adapter, float valor1) {
                Log.i("SSS","Print Job Adapter - onFinish on Main screen - "+ pj.getInfo());

                new printAsyncTask(new printAsyncTask.printAsyncTaskInterface() {
                    @Override
                    public void onStart() {
                        progress.show();
                    }

                    @Override
                    public void onFinish() {
                        progress.hide();
                    }

                    @Override
                    public void onSuccess(final int numberOfPages) {
                        Log.i("SSS", "atualizar saldo  ");

                        MainScreen.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                atualizarSaldo(numberOfPages);
                                progress.hide();
                            }
                        });
                    }

                    @Override
                    public void onFail() {

                    }
                }).execute(pj);
            }

            @Override
            public void cancelled() {}
        });
        pj = printManager.print(jobName,adpater ,null);
    }

    public void removeAllAppDownloadedFiles(){
        for(long id : downloadedFilesId){
            ((DownloadManager)this.getSystemService(this.DOWNLOAD_SERVICE)).remove(id);
        }
    }


    public void btnLogoutClicked(View view){
        removeAllAppDownloadedFiles();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

}
