package com.example.galleryproject.Views.Activity

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
import com.example.galleryproject.Views.Fragments.LoadingDialog
import com.example.galleryproject.Views.Fragments.Signup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var email:String ?= null
    private var password:String?=null
    private lateinit var viewmodel: Viewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(Viewmodel::class.java)
        if (viewmodel.checkUserLogin()){
            startActivity(Intent(this,
                GalleryActivity::class.java))
        }

        signupText.setOnClickListener {
            signup()
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
        val loadingDialog = LoadingDialog(this)
        loadingDialog.startLoadingDialog("Signing in, please wait.")
        viewmodel.login(email!!,password!!).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    loadingDialog.dismissDialog()
                    Toast.makeText(this,"Signed in successfully",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,
                        GalleryActivity::class.java))
                } else {
                    Toast.makeText(this,"Email id and password do not match",Toast.LENGTH_SHORT).show()
                    Log.e("ERROR", "signInWithEmail:failure", task.exception)

                }
            }



    }

    fun signup() {
        val manager = supportFragmentManager
        val transact = manager.beginTransaction()
        val sFragment = Signup()
        transact.replace(R.id.container,sFragment)
        transact.commit()
    }

}
