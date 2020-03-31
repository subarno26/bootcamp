package com.example.kotlin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val username:String = username.text.toString()
        val password:String = password.text.toString()

    }

    fun submit(view: View) {
        if (username.length() == 0){
            Toast.makeText(this,"Username cannot be empty",Toast.LENGTH_SHORT).show()
        }
        else if (password.length() == 0){
            Toast.makeText(this,"Password cannot be empty",Toast.LENGTH_SHORT).show()
        }
        else if (username.length() > 8){
            Toast.makeText(this,"Too long",Toast.LENGTH_SHORT).show()
        }
        else if (password.length()<8){
            Toast.makeText(this,"Password is too short",Toast.LENGTH_SHORT).show()
        }
        else{
            intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }


    }
}
