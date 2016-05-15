package com.example.elder.printstop.async;

import android.os.AsyncTask;

import com.example.elder.printstop.util.Downloader;

import java.io.File;

/**
 * Created by Elder on 5/14/2016.
 */
public class DownloadPdfAsyncTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] params) {

        Downloader.DownloadFile( (String)params[0], (File)params[1]);
        return null;
    }
}
