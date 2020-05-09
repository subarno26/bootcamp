package com.example.galleryproject.ViewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository

class SignupViewModel:ViewModel() {
    private val repository = Repository()

    fun signUp(Name:String, Email:String, Password:String, uri: Uri?): Boolean {
        return repository.signUp(Name, Email, Password, uri)

    }

}