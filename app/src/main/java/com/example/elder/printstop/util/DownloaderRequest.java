package com.example.elder.printstop.util;

import android.app.DownloadManager;
import android.net.Uri;

import java.net.URI;

/**
 * Created by Elder on 5/16/2016.
 */
public class DownloaderRequest extends DownloadManager.Request{
    /**
     * @param uri the HTTP or HTTPS URI to download.
     */
    public DownloaderRequest(Uri uri) {
        super(uri);
    }
}
