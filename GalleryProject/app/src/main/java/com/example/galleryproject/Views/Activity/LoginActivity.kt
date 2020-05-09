package com.example.galleryproject.Views.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.galleryproject.R
import com.example.galleryproject.ViewModel.LoginViewModel
import com.example.galleryproject.Views.Fragments.LoadingDialog
import com.example.galleryproject.Views.Fragments.Signup
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    private var email:String ?= null
    private var password:String?=null
    private var viewModel = LoginViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        if (viewModel.checkUserLogin()){
            startActivity(Intent(this,
                GalleryActivity::class.java))
        }

        signupText.setOnClickListener {
            signUp()
        }
        loginBtn.setOnClickListener {
            login()
        }

    }

    private fun validate():Boolean{
        var valid = true
        email = emailEdit.editText!!.text.toString()
        password = passwordEdit.editText!!.text.toString()

        if (TextUtils.isEmpty(email)){
            emailEdit.error = getString(R.string.required_field)
            valid = false
        }
        else if (TextUtils.isEmpty(password)){
            passwordEdit.error = getString(R.string.password_prompt)
            valid = false
        }
        return valid
    }

    private fun login() {
        if (!validate()){
            return
        }
        val loadingDialog = LoadingDialog(this)
        loadingDialog.startLoadingDialog(getString(R.string.sign_in_dialog))
        viewModel.login(email!!,password!!).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    loadingDialog.dismissDialog()
                    Toast.makeText(this,getString(R.string.successful_sign_in),Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,
                        GalleryActivity::class.java))
                } else {
                    loadingDialog.dismissDialog()
                    Toast.makeText(this,getString(R.string.email_pass_error),Toast.LENGTH_SHORT).show()
                    Log.e("ERROR", "signInWithEmail:failure", task.exception)

                }
            }



    }

    private fun signUp() {
        val manager = supportFragmentManager
        val transact = manager.beginTransaction()
        val sFragment = Signup()
        transact.replace(R.id.container,sFragment)
        transact.addToBackStack(null)
        transact.commit()
    }

}
