package com.example.galleryproject.ViewModel

import android.app.Activity
import android.app.Application
import android.net.Uri
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository
import com.example.galleryproject.Views.Fragments.AddCategoryModel
import com.example.galleryproject.Views.Fragments.ImageModel
import com.example.galleryproject.Views.Fragments.LoadingDialog
import com.example.galleryproject.Views.Fragments.TimelineModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class Viewmodel : ViewModel(){
    private val repository = Repository()
    private var mCategoryList: MutableLiveData<List<AddCategoryModel>> = MutableLiveData()
    private var mImageList : MutableLiveData<List<ImageModel>> = MutableLiveData()
    private var mTimeList: MutableLiveData<List<TimelineModel>> = MutableLiveData()
    private lateinit var tList:List<TimelineModel>
    val categoryList = mutableListOf<AddCategoryModel>()


    fun checkUserLogin(): Boolean {
        return repository.checkUserLogin()
    }

    fun login(email:String, password:String): Task<AuthResult> {
        return repository.login(email, password)
    }
    fun signup(Name:String, Email:String, Password:String, uri: Uri?): Boolean {
        return repository.signup(Name, Email, Password, uri)

    }

    fun loadData():LiveData<List<AddCategoryModel>>{
        if (categoryList.size>0){
            categoryList.clear()
        }
        repository.loadData().addSnapshotListener{ snapshot, exception ->
            if (exception!=null){
                return@addSnapshotListener
            }
            if (snapshot!=null){
                for(doc in snapshot.documentChanges){
                    val fetchedCategory =
                        AddCategoryModel(
                            doc.document.getString("categoryName")!!,
                            doc.document.getString("categoryImage")!!
                        )
                    categoryList.add(fetchedCategory)
                }
            }

            mCategoryList.value = categoryList

        }
        return mCategoryList
    }

    fun addCategory(uri: Uri, categoryName: String):Boolean{
        return repository.addCategory(uri,categoryName)
    }

    fun loadImages(categoryName: String):LiveData<List<ImageModel>>{
//        if (imageList.size>0){
//            imageList.clear()
//        }
//        repository.loadImages(categoryName).addSnapshotListener{ snapshot, exception ->
//            if (exception != null){
////                mImageList.value = null
//                return@addSnapshotListener
//            }
//            if (snapshot!=null){
//
//                for(doc in snapshot.documentChanges){
//                    val fetched = ImageModel(
//                        doc.document.getString("downloadURL")!!,
//                        doc.document.getString("timestamp")!!,
//                        doc.document.getString("categoryName")!!
//                    )
//                    imageList.add(fetched)
//
//
//                }
//                mImageList.value = imageList
//                Log.i("List of images",imageList.toString())
//                Log.i("Live data list ", mImageList.toString())
//            }
//
//        }
//        return mImageList
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

    fun deleteImage(imageUrl: String,category:String,timestamp:String):Boolean{
        return repository.deleteImage(imageUrl, category, timestamp)
    }
    fun getUserDetails(): Task<DocumentSnapshot> {
        return repository.getUserDetails()
    }

    fun logout(){
        return repository.logout()
    }


    fun updateUserImage(uri: Uri){
        return repository.updateUserImage(uri)
    }

    fun getTimeline():LiveData<List<TimelineModel>>{
        repository.getTimeline().listAll().addOnSuccessListener {
            val timeList = mutableListOf<TimelineModel>()
            for (i in it.items) {
                i.metadata.addOnSuccessListener {
                    val timelineModel =
                        TimelineModel(
                            i.downloadUrl,
                            it.creationTimeMillis

                        )

                    timeList.add(timelineModel)

                    tList = timeList.sortedByDescending {
                        it.timestamp as Long
                    }

                    mTimeList.value = tList
                }
            }
        }
        return mTimeList
    }


}