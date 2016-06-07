package com.example.elder.printstop.async;

import android.os.AsyncTask;
import android.print.PrintJob;
import android.print.PrintJobInfo;
import android.util.Log;

/**
 * Created by Elder on 2016-06-07.
 */
public class printAsyncTask extends AsyncTask {

    private printAsyncTaskInterface _printAsyncTaskInterface;


    public printAsyncTask(printAsyncTaskInterface mInterface){
        _printAsyncTaskInterface = mInterface;
    }

    public interface printAsyncTaskInterface{
        void onStart();
        void onFinish();
        void onSuccess(int numberOfPages);
        void onFail();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        _printAsyncTaskInterface.onStart();
    }

    @Override
    protected Object doInBackground(Object[] params) {

        PrintJob pj = (PrintJob)params[0];
        try {
            while (pj.getInfo().getState() != PrintJobInfo.STATE_COMPLETED && pj.getInfo().getState() != PrintJobInfo.STATE_CANCELED) {
                Log.i("SSS", "\nPrint Job info - " + pj.getInfo().getState());
            }
            Log.i("SSS", "\nPrint Job info - " + pj.getInfo().toString());
            int pages = getNumberOfPages(pj.getInfo().toString());
            if(pj.getInfo().getState() == PrintJobInfo.STATE_COMPLETED)
                _printAsyncTaskInterface.onSuccess(pages);
            else
                _printAsyncTaskInterface.onFail();
        } catch (Exception ex){

        }
        return null;

    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        _printAsyncTaskInterface.onFinish();
    }

    private int getNumberOfPages(String str){


        String strAux = str.substring(str.indexOf("Range[")+6);
        String numFirst = "";
        String numSecond = "";
        String aux = "";
        for(int i = 0; i < strAux.length(); i++ ){

            if(strAux.charAt(i) == '-') {
                numFirst = aux;
                aux = "";
            } else if(strAux.charAt(i) == ']'){
                numSecond = aux;
                break;
            } else {
                aux += strAux.charAt(i);
            }
        }
        Log.i("SSS", "first "+ numFirst + " second " + numSecond);

        int numPages;
        if(numSecond.equals("<all pages>")) {

            String aux1 = str.substring(str.indexOf("pageCount=") + 10);
            String strNumPages = "";
            Log.i("SSS", " aux = " + aux1);
            for (int i = 0; i < aux1.length(); i++) {
                if (aux1.charAt(i) == ',') {
                    break;
                } else {
                    strNumPages += aux1.charAt(i);
                }
            }
            Log.i("SSS", " number of pages = " + strNumPages);
            numPages = Integer.valueOf(strNumPages.trim());
        }
        else {
            numPages = Integer.valueOf(numSecond.trim()) - Integer.valueOf(numFirst.trim()) + 1;
            Log.i("SSS", "Pages :: " + numPages);
        }

        return numPages;
    }
}
