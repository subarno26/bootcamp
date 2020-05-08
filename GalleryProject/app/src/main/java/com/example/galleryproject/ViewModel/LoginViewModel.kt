package com.example.galleryproject.ViewModel

import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class LoginViewModel : ViewModel(){
    private val repository = Repository()

    fun checkUserLogin(): Boolean {
        return repository.checkUserLogin()
    }

    fun login(email:String, password:String): Task<AuthResult> {
        return repository.login(email, password)
    }

}