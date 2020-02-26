package com.example.androidsession2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sign extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        TextView setName, setPhone, setEmail, setPassword;

        setName = findViewById(R.id.getName);
        setEmail = findViewById(R.id.getEmail);
        setPhone = findViewById(R.id.getPhone);

        Bundle b1 = getIntent().getExtras();
        String sname = b1.getString("username");
        String sPhone = b1.getString("phone");
        String sEmail = b1.getString("email");




        setName.setText(sname);
        setEmail.setText(sEmail);
        setPhone.setText(sPhone);

    }

    public void browser(View view) {
        EditText link = findViewById(R.id.editText2);
        String url = link.getText().toString();
        if (url.indexOf("https://www") < 0 ) {
            url = "https://www." + url;
        }
        Intent explicit = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(explicit);
    }

    public void checkPermission(View view) {

        if (ContextCompat.checkSelfPermission(sign.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(sign.this,
                    new String[]{Manifest.permission.CAMERA},1
                    );
        }
        else {
            Toast.makeText(this, "Permissions have already been granted!", Toast.LENGTH_SHORT).show();
        }
    }
}
