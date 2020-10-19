package com.unitedfoods.allcashu.Uitls;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

enum DownloadStatus {IDLE,PROCESSING,NOT_INITIALISED,FLALLED_OR_EMPTY,OK}
public class GetWebService extends AsyncTask<String,Void,String> {

    private static final String TAG = "GetWebService";
    private DownloadStatus mDownloadStatus;
    private ProgressDialog progressDialog;
    private StringBuffer result;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    public GetWebService(Context context){
        this.context = context;
        this.mDownloadStatus = DownloadStatus.IDLE;
    }

    @Override
    protected void onPreExecute() {
//        progressDialog = new ProgressDialog(context);
//
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Please wait...");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//
//        // show it
//        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        if (strings == null){
            mDownloadStatus = DownloadStatus.NOT_INITIALISED;
            return null;
        }

        try {
            mDownloadStatus = DownloadStatus.PROCESSING;
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            Log.d(TAG, "doInBackground: The response code was "+response);

            InputStream input = connection.getInputStream();

             result = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            for (String line = reader.readLine(); line != null; line = reader.readLine())
            {
                result.append(line).append("\n");
            }
            
            mDownloadStatus = DownloadStatus.OK;
            return result.toString();

        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground: Invalid URL: " + e.getMessage() );
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: IO Exception reading data: "+e.getMessage());
        }catch (SecurityException e){
            Log.e(TAG, "doInBackground: Security Exception. Needs permission?"+e.getMessage());
        }finally {
            if (connection != null){
                connection.disconnect();
            }
            if (reader != null){

                try
                {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "doInBackground: Error closing stream" +e.getMessage());
                }

            }
        }

        mDownloadStatus = DownloadStatus.FLALLED_OR_EMPTY;
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: parameter"+s);
//        progressDialog.dismiss();
    }

}
