package com.example.galleryproject.ViewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

class ProfileViewModel : ViewModel() {
    private val repository = Repository()
    private var updateStatus =  MediatorLiveData<UpdateProgress>()
    fun getUserDetails(): Task<DocumentSnapshot> {
        return repository.getUserDetails()
    }

    fun logout(){
        return repository.logout()
    }

    fun getUpdateStatus():LiveData<UpdateProgress>{
        return updateStatus
    }


    fun updateUserImage(uri: Uri){
        updateStatus.value = UpdateProgress.SHOW_PROGRESS
        updateStatus.addSource(
            repository.updateUserImage(uri)
            , Observer {
                it.onSuccess{
                    updateStatus.value = UpdateProgress.HIDE_PROGRESS
                }
                it.onFailure{
                    updateStatus.value = UpdateProgress.HIDE_PROGRESS
                }
            }
        )
    }

    enum class UpdateProgress{
        SHOW_PROGRESS,
        HIDE_PROGRESS
    }
}