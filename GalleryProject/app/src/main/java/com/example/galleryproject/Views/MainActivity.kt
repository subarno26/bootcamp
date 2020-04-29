package com.example.galleryproject.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.galleryproject.R
import com.example.galleryproject.ViewModel.Viewmodel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel: Viewmodel
    private lateinit var auth: FirebaseAuth
    private lateinit var email:String
    private lateinit var password:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){
            startActivity(Intent(this,
                GalleryActivity::class.java))
        }
    }


    private fun validate():Boolean{
        var valid = true
        email = emailEdit.text.toString()
        password = passwordEdit.text.toString()

        if (TextUtils.isEmpty(email)){
            emailEdit.error = "Required field"
            valid = false
        }
        else if (TextUtils.isEmpty(password)){
            passwordEdit.error = "Please enter password"
            valid = false
        }
        return valid
    }

    fun login(view: View) {
        if (!validate()){
            return
        }
        viewmodel = ViewModelProvider(this).get(Viewmodel::class.java)
        viewmodel.login(email,password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Signed in successfully",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,
                        GalleryActivity::class.java))
                } else {
                    Toast.makeText(this,"Email id and password do not match",Toast.LENGTH_SHORT).show()
                    Log.e("ERROR", "signInWithEmail:failure", task.exception)

                }
            }



    }

    fun signup(view: View) {
        val manager = supportFragmentManager
        val transact = manager.beginTransaction()
        val sFragment = Signup()
        transact.replace(R.id.container,sFragment)
        transact.commit()
    }

}
