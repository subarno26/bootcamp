package com.example.galleryproject.ViewModel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.example.galleryproject.Model.Repository
import com.example.galleryproject.Views.Fragments.ImageModel
import com.example.galleryproject.Views.Fragments.LoadingDialog
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class CategoryImagesViewModel : ViewModel() {
    private val repository = Repository()
    private var mImageList : MutableLiveData<List<ImageModel>> = MutableLiveData()
    private var photosStatus = MediatorLiveData<PhotoStatus>()
    //private val loadingDialog = LoadingDialog(context)

    fun getPhotosStatus(): LiveData<PhotoStatus>{
        return photosStatus
    }


    fun loadImages(categoryName: String): LiveData<List<ImageModel>> {
        //loadingDialog.startLoadingDialog("TEST")
        repository.loadImages(categoryName)
            .addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
                if (e != null) {
                    //Log.w(TAG, "Listen Fail", e)
                    mImageList.value = null
                    return@EventListener
                }
                val savedImagesList: MutableList<ImageModel> = mutableListOf()
                for (doc in value!!) {
                    val imageItem =
                        ImageModel(
                            doc.getString("downloadURL")!!,
                            doc.getString("timestamp")!!,
                            doc.getString("categoryName")!!
                        )
                    savedImagesList.add(imageItem)
                }
                mImageList.value = savedImagesList
                //Log.d(TAG, savedImagesList.toString())
            })
        //loadingDialog.dismissDialog()
        return mImageList

    }

    fun storeImages(categoryName: String,uri: Uri){
        photosStatus.value = PhotoStatus.SHOW_PROGRESS
        photosStatus.addSource(
            repository.storeImages(
                categoryName, uri
            ), Observer {
                it.onSuccess{
                    photosStatus.value = PhotoStatus.HIDE_PROGRESS
                    Log.i("Images","HIDE")
                }
                it.onFailure{
                    photosStatus.value = PhotoStatus.HIDE_PROGRESS
                    Log.i("Images","SHOW")
                }
            }
        )

    }

    enum class PhotoStatus{
        SHOW_PROGRESS,
        HIDE_PROGRESS
    }
}