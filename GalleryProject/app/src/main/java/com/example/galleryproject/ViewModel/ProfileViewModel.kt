package com.example.galleryproject.ViewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

class ProfileViewModel : ViewModel() {
    private val repository = Repository()
    fun getUserDetails(): Task<DocumentSnapshot> {
        return repository.getUserDetails()
    }

    fun logout(){
        return repository.logout()
    }


    fun updateUserImage(uri: Uri){
        return repository.updateUserImage(uri)
    }
}