package com.example.asynctasksession;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private String url = "https://i2.wp.com/www.mariodelafuente.org/putolinux/wp-content/uploads/2017/03/antivirus-para-android.png?fit=800%2C799";
    ImageView image;
    InputStream input;
    OutputStream outputStream;
    URL urlll;

    ProgressBar progressBar;
    ProgressDialog progressDialog;
    TextView progressText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progress_bar);

        progressText = findViewById(R.id.textView);
        progressText.setAlpha(0);
        progressBar.setAlpha(0);
       // url = "https://images.pexels.com/photos/1226302/pexels-photo1226302.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";

    }

    public void imageButton(View view) {
            if (isConnected()) {
                progressBar.setAlpha(1);
                progressText.setAlpha(1);
                new DownloadAsync().execute(url);
            }
            else {
                Toast.makeText(MainActivity.this,"Unable to connect to network",Toast.LENGTH_SHORT).show();
            }
    }


    public boolean isConnected() {
        Context context = getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);

        if ( connectivity != null) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();

            if (info != null) {

                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }

        return false;
    }

    private class DownloadAsync extends AsyncTask<String,String,String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressBar = new ProgressBar(MainActivity.this);
            progressBar.setIndeterminate(false);
            progressBar.setMax(100);

//            progressDialog = new ProgressDialog(MainActivity.this);
//            progressDialog.setMessage("Downloading");
//            //progressDialog.setIndeterminate(false);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            progressDialog.show();
        }







        protected String doInBackground(String... URL) {
                int count;
                String imageURL = url;
                int FileSize;
                Bitmap bitmap = null;
                URLConnection urlConnection;

                try {

                    urlll = new URL(URL[0]);
                    urlConnection = urlll.openConnection();
                    urlConnection.connect();
                    FileSize = urlConnection.getContentLength();

                    // Download Image from URL
                    input = new java.net.URL(imageURL).openStream();
                    String sdcard = Environment.getExternalStorageDirectory().getPath();
                    Log.e("STORAGE LOCATION", sdcard);
                    outputStream = new FileOutputStream(sdcard + "/demo.jpg");
                    // Decode Bitmap
                    //bitmap = BitmapFactory.decodeStream(input);
                    Log.i("TAGGG", "This section is working!");
                    //publishProgress();
                    byte data[] = new byte[1024];
                    long total = 0;
                    while ((count = input.read(data)) != -1) {
                        Log.i("progress", "Downloading...");
                        while (!isConnected()){
                            Thread.sleep(1);
                           // Toast.makeText(MainActivity.this,"Paused",Toast.LENGTH_SHORT).show();
                        }
                        total += count;
                        Thread.sleep(50);
                        publishProgress("" + (int) (total * 100 / FileSize));
                        outputStream.write(data, 0, count);
                    }
                    outputStream.flush();
                    outputStream.close();
                    input.close();
//                   input = new java.net.URL(imageURL).openStream();
                    //Log.i("WORKING","asdfghjkl;");
                    //bitmap = BitmapFactory.decodeStream(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            return null;

        }

        public void setError(){
            progressText.setText("Paused");
        }
        @Override
        protected void onProgressUpdate(String... progress) {
            Log.i("progress","working progress");
            progressBar.setProgress(Integer.parseInt(progress[0]));
            progressText.setText(Integer.parseInt(progress[0]) + "/" + progressBar.getMax());
            //progressDialog.setProgress(Integer.parseInt(progress[0]));

        }


        @Override
        protected void onPostExecute(String unused) {
            //image.setImageBitmap(result);

           // progressDialog.dismiss();




            String GetPath = Environment.getExternalStorageDirectory().toString() + "/demo.jpg";


            Log.i("PATH STORAGE",GetPath);
            image.setImageDrawable(Drawable.createFromPath(GetPath));
            Log.i("Error","running");
            progressText.setAlpha(0);
            progressBar.setAlpha(0);
            Toast.makeText(MainActivity.this, "Download Completed!", Toast.LENGTH_SHORT).show();



        }

    }
}
