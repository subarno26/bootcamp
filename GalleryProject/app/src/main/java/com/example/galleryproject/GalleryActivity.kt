package com.example.galleryproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class GalleryActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
    }

    fun logout(view: View) {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        startActivity(Intent(this,MainActivity::class.java))
    }
}
