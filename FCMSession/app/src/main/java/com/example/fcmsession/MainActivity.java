package com.example.fcmsession;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private TextView text1, text2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        imageView  = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String data = intent.getStringExtra("data");
        String URL = intent.getStringExtra("URL");

        text1.setText(title);
        text2.setText(data);

        Glide.with(this).load(URL)
                .fitCenter()
                .into(imageView);

    }
}
