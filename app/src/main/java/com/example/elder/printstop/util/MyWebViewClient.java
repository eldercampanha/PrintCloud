package com.example.elder.printstop.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.elder.printstop.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Elder on 2016-04-30.
 */
public class MyWebViewClient extends WebViewClient {


    private WebClienteInterface _webClienteInterface;
    private List<PrintJob> mPrintJobs = new ArrayList<>();
    private Context _context;

    public interface WebClienteInterface{
        void start();
        void finish();
    }

    public MyWebViewClient( Context context, WebClienteInterface webClienteInterface){
        _webClienteInterface = webClienteInterface;
        _context = context;
    }



    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        _webClienteInterface.start();
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('ndfHFb-c4YZDc-GSQQnc-LgbsSe ndfHFb-c4YZDc-to915-LgbsSe VIpgJd-TzA9Ye-eEGnhe ndfHFb-c4YZDc-LgbsSe')[0].style.display='none'; })()");
        super.onPageFinished(view, url);
        _webClienteInterface.finish();
    }

}
