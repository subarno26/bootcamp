package com.example.galleryproject.ViewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository
import com.example.galleryproject.Views.Fragments.ImageModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class CategoryImagesViewModel : ViewModel(){
    private val repository = Repository()
    private var mImageList : MutableLiveData<List<ImageModel>> = MutableLiveData()
    fun loadImages(categoryName: String): LiveData<List<ImageModel>> {
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
        return mImageList
    }

    fun storeImages(categoryName: String,uri: Uri){
        return repository.storeImages(categoryName, uri)
    }
}