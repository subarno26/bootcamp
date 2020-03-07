package com.example.androidstorage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void savePref(View view) {
        EditText editPref = findViewById(R.id.editText);
        String text = editPref.getText().toString();

        if(text.isEmpty()){
            Toast.makeText(this, "Field cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("textKey",text);
        editor.commit();
        Toast.makeText(this,"Saved successfully",Toast.LENGTH_SHORT).show();
        }
     }


    public void retrievePref(View view) {
        SharedPreferences sp = getSharedPreferences("MyPreferences",MODE_PRIVATE);
        Log.d("preference",sp.getString("textKey","No preference found"));
        String showPref = sp.getString("textKey","No preference found");
        TextView textView = findViewById(R.id.textView);
        textView.setText(showPref);
    }

}
