package com.example.elder.printstop.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import android.webkit.WebView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Elder on 2016-05-02.
 */
public class PrintJobAdapter extends PrintDocumentAdapter {

    private String _fileName;
    private PrintJobAdapterInterface _interface;

    public interface  PrintJobAdapterInterface{
        void onFinish(PrintJobAdapter adapter);
        void cancelled();
    }

    public PrintJobAdapter(String fileName, PrintJobAdapterInterface printJobAdapterInterface){
        _fileName = fileName;
        _interface = printJobAdapterInterface;

    }

    @Override
    public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback){
        InputStream input = null;
        OutputStream output = null;

        try {
            File path = new File(Environment.getExternalStoragePublicDirectory(Environment.MEDIA_MOUNTED),_fileName);
            input = new FileInputStream(path);
            output = new FileOutputStream(destination.getFileDescriptor());

            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }

            callback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
                @Override
                public void onCancel() {
                    _interface.cancelled();
                }
            });

        } catch (FileNotFoundException ee){
            //Catch exception
        } catch (Exception e) {
            //Catch exception
        } finally {
            try {
                if( (input != null) && output != null) {
                    input.close();
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras){

        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }

        PrintDocumentInfo pdi = new PrintDocumentInfo.Builder(_fileName).setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT).build();

        callback.onLayoutFinished(pdi, true);
    }

    @Override
    public void onFinish() {
        super.onFinish();
        Log.i("SSS","Finish printing");

        _interface.onFinish(this);
    }


}
