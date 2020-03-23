package com.example.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        textDesc = findViewById(R.id.textDesc);
//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }

    }

    public void httpDisplay(View view) {
//        try {
//            URL url = new URL("https://image.freepik.com/free-photo/image-human-brain_99433-298.jpg");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.connect();
//            InputStream inputStream = urlConnection.getInputStream();
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            imageView.setImageBitmap(bitmap);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

        new AysncDownload().execute();

    }

    public void glideDisplay(View view) {
        Glide.with(this)
                .load("https://image.freepik.com/free-photo/image-human-brain_99433-298.jpg")
                .fitCenter()
                .override(800,800)
                .into(imageView);

        textDesc.setText("Image loaded with Glide!");
    }


    public class AysncDownload extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... URL) {
            Bitmap bitmap = null;
            try {
                URL url = new URL("https://image.freepik.com/free-photo/image-human-brain_99433-298.jpg");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

            }  catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;

        }

        @Override
        protected void onPostExecute(Bitmap bitmapResult) {
            imageView.setImageBitmap(bitmapResult);
            textDesc.setText("Image loaded with HTTPUrlConnection!");
        }
    }
}
