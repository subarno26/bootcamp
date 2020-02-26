package com.example.androidsession2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }



    public void signup(View view) {
        EditText user = findViewById(R.id.username);
        EditText emailID = findViewById(R.id.email);
        EditText passwordd = findViewById(R.id.password);
        EditText phoneNumber = findViewById(R.id.phone);

        String userText = user.getText().toString();
        String emailText = emailID.getText().toString();
        String passwordInput = passwordd.getText().toString();
        String phoneInput = phoneNumber.getText().toString();



        if (userText.isEmpty() || emailText.isEmpty() || passwordInput.isEmpty() || phoneInput.isEmpty()){
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        }
        else if (userText.length() > 10){
            Toast.makeText(this, "The username entered is too long!", Toast.LENGTH_SHORT).show();
        }
        else  if (phoneInput.length() < 10){
            Toast.makeText(this , "The phone number entered is too short",Toast.LENGTH_SHORT).show();
        }

        else  if (phoneInput.length() > 10){
            Toast.makeText(this , "The phone number entered is too long",Toast.LENGTH_SHORT).show();
        }
        else if (passwordInput.length() < 8){
            Toast.makeText(this, "The password is too short", Toast.LENGTH_SHORT).show();
        }else if (!emailText.contains("@")){
            Toast.makeText(this, "Please enter the correct email id", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this , sign.class);
            intent.putExtra("username", userText);
            intent.putExtra("email", emailText);
            intent.putExtra("password", passwordInput);
            intent.putExtra("phone", phoneInput);
            startActivity(intent);
        }


    }
}
