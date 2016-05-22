package com.example.elder.printstop.async;

import android.app.DownloadManager;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.print.PrintManager;

import com.example.elder.printstop.R;
import com.example.elder.printstop.util.Downloader;

import java.io.File;

/**
 * Created by Elder on 5/14/2016.
 */
public class DownloadPdfAsyncTask extends AsyncTask {

    private DownloadPdfAsyncTaskInterface mInterface;
    private Context mContext;

    public DownloadPdfAsyncTask(Context context, DownloadPdfAsyncTaskInterface downloadPdfAsyncTaskInterface){
        mContext = context;
        mInterface = downloadPdfAsyncTaskInterface;
    }

    public  interface DownloadPdfAsyncTaskInterface{
        void onStart();
        void onFinish(long id);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mInterface.onStart();
    }

    @Override
    protected Object doInBackground(Object[] params) {

        DownloadManager downloadManager = (DownloadManager)mContext.getSystemService(mContext.DOWNLOAD_SERVICE);
        Uri Download_Uri = (Uri)params[0];
        String name = (String)params[1];
        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setDestinationInExternalPublicDir(Environment.getExternalStorageState(),name);

        mInterface.onFinish(downloadManager.enqueue(request));

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
