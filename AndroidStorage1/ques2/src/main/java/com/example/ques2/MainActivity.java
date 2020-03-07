package com.example.ques2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String text;
    String FILE = "myFilename";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);

    }

    public void writeFile(View view) {
        try {
            text = editText.getText().toString();
            FileOutputStream fout = openFileOutput(FILE,MODE_PRIVATE);
            fout.write(text.getBytes());
            fout.close();
            Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(View view) {
        //editText.getText().clear();
        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE);
            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            Log.d("fileInput",temp);
            editText.setText(temp);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
