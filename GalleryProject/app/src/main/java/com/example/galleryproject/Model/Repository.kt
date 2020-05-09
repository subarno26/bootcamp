package com.example.galleryproject.Model

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference

class Repository (){
    private var firebaseModel = FirebaseModel()

    fun checkUserLogin():Boolean = firebaseModel.checkUserLogin()
    fun login(email:String,password:String) =firebaseModel.login(email, password)
    fun signUp(Name:String, Email:String,Password:String, uri: Uri?):Boolean = firebaseModel.signUp(Name, Email,Password,uri)
    fun loadData():CollectionReference = firebaseModel.loadData()
    fun addCategory(uri: Uri,categoryName: String):Boolean = firebaseModel.addCategory(uri,categoryName)
    fun loadImages(categoryName: String) = firebaseModel.loadImages(categoryName)

    fun storeImages(categoryName: String,uri: Uri):LiveData<Result<Boolean>>{
        val result : MutableLiveData<Result<Boolean>> = MutableLiveData()
        firebaseModel.storeImages(categoryName, uri).observeForever{
            it.onSuccess{
                result.value = Result.success(it)
            }
            it.onFailure{
                result.value = Result.failure(it)
            }
        }
        return result
    }

    fun deleteImage(imageUrl: String, category:String,timestamp:String):Boolean = firebaseModel.deleteImage(imageUrl,category, timestamp)
    fun getUserDetails() = firebaseModel.getUserDetails()

    fun updateUserImage(uri: Uri) : LiveData<Result<Boolean>>{
        val result: MutableLiveData<Result<Boolean>> = MutableLiveData()
        firebaseModel.updateUserImage(uri).observeForever{
            it.onSuccess{
                result.value = Result.success(it)
            }
            it.onFailure{
                result.value = Result.failure(it)
            }
        }
        return result
    }
    fun getTimeline() :StorageReference = firebaseModel.getTimeline()
    fun logout() = firebaseModel.logout()
}