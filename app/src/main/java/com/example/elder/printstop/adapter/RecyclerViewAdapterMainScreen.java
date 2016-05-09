package com.example.elder.printstop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elder.printstop.R;
import com.example.elder.printstop.model.Arquivos;
import com.example.elder.printstop.model.FileToPrint;
import com.example.elder.printstop.ui.MainScreen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elder on 3/5/2016.
 */
public class RecyclerViewAdapterMainScreen extends RecyclerView.Adapter<RecyclerViewAdapterMainScreen.Holder> {

    private final Context mContext;
    private final ArrayList<Arquivos> mFiles;
    private View mView;
    private WebView webView;
    private RecyclerViewAdapterMainScreenInterface _interface;

    public interface RecyclerViewAdapterMainScreenInterface{
        void fileCliked(int file);
    }

    public RecyclerViewAdapterMainScreen(Context context, ArrayList<Arquivos> files, RecyclerViewAdapterMainScreenInterface recyclerViewAdapterMainScreenInterface) {
        mContext = context;
        mFiles = files;
        webView = (WebView) ((Activity) mContext).findViewById(R.id.my_webview);
        this._interface = recyclerViewAdapterMainScreenInterface;
    }


    @Override
    public RecyclerViewAdapterMainScreen.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        mView = LayoutInflater.from(mContext).inflate(R.layout.row_main_file, parent, false);
        Holder holder = new Holder(mView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterMainScreen.Holder holder, final int position) {


        final Arquivos fileToPrint = mFiles.get(position);

        if (fileToPrint != null) {
            holder.fileName.setText(fileToPrint.getNome());
            holder.fileInfo.setText("Size: " + fileToPrint.getTamanho());

            holder.fileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txt = (TextView) v;
                    _interface.fileCliked(position);
//                    webView.loadUrl(fileToPrint.getFileLocation());
//                    webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url="+ "http://2012books.lardbucket.org/pdfs/music-theory.pdf");

                }
            });
        }
    }
    private void showPdf(String name) {

//        File file = new File(Environment.getExternalStorageDirectory()+"/Mypdf/Read.pdf");
        String filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(filepath + "/" + name);

        PackageManager packageManager = mContext.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        mContext.startActivity(intent);
    }


    private void render(String fileName) {

        try {
            ImageView mImageView = (ImageView) ((Activity) mContext).findViewById(R.id.img_pdf_file);
            int REQ_WIDTH = 1000;//mImageView.getWidth();
            int REQ_HEIGHT = mImageView.getHeight() + 1000;
            int currentPage = 0;


            Bitmap bitmap = Bitmap.createBitmap(REQ_WIDTH, REQ_HEIGHT, Bitmap.Config.ARGB_4444);

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            File file = new File(path + "/" + fileName);
            PdfRenderer pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));

            Matrix m = mImageView.getMatrix();
            Rect rec = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            pdfRenderer.openPage(currentPage).render(bitmap, rec, m, PdfRenderer.Page.RENDER_MODE_FOR_PRINT);
            mImageView.setImageMatrix(m);
            mImageView.setImageBitmap(bitmap);
            mImageView.invalidate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView fileName;
        public TextView fileInfo;

        public Holder(View itemView) {
            super(itemView);

            fileName = (TextView) itemView.findViewById(R.id.txt_file_name);
            fileInfo = (TextView) itemView.findViewById(R.id.txt_file_info);
        }
    }
}
