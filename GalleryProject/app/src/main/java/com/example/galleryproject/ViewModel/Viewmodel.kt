package com.example.galleryproject.ViewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryproject.*
import com.example.galleryproject.Model.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot
import java.sql.Time

class Viewmodel : ViewModel(){
    private val repository = Repository()
    private var mCategoryList: MutableLiveData<List<AddCategoryModel>> = MutableLiveData()
    private var mImageList : MutableLiveData<List<ImageModel>> = MutableLiveData()
    private var mTimeList: MutableLiveData<List<TimelineModel>> = MutableLiveData()
    private lateinit var tList:List<TimelineModel>
    fun login(email:String, password:String): Task<AuthResult> {
        return repository.login(email, password)
    }

    fun signup(Name:String, Email:String, Password:String, uri: Uri?): Boolean {
        return repository.signup(Name, Email, Password, uri)
    }

    fun loadData():LiveData<List<AddCategoryModel>>{
        repository.loadData().get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val categoryList = mutableListOf<AddCategoryModel>()
                for (doc: DocumentSnapshot in task.result!!) {
                    val fetchedCategory = AddCategoryModel(
                        doc.getString("categoryName")!!,
                        doc.getString("categoryImage")!!
                    )
                    categoryList.add(fetchedCategory)
                    //addData(categoryList)
                }

                mCategoryList.value = categoryList


            } else {
                Log.e("error", "$task.exception")

            }
        }
        return mCategoryList
    }

    fun addCategory(uri: Uri, categoryName: String):Boolean{
        return repository.addCategory(uri,categoryName)
    }

    fun loadImages(categoryName: String):LiveData<List<ImageModel>>{
        repository.loadImages(categoryName).addSnapshotListener{snapshot, exception ->
            if (exception != null){
                return@addSnapshotListener
            }
            if (snapshot!=null){
                val imageList:MutableList<ImageModel> = mutableListOf()
                for(doc in snapshot.documentChanges){
//                        val imageModel = doc.document.toObject(ImageModel::class.java)
                    val fetched = ImageModel(
                        doc.document.getString("downloadURL")!!,
                        doc.document.getString("timestamp")!!,
                        doc.document.getString("categoryName")!!
                    )
                    imageList.add(fetched)
                }
                mImageList.value = imageList
            }

        }
        return mImageList
    }

    fun storeImages(categoryName: String,uri: Uri){
        return repository.storeImages(categoryName, uri)
    }

    fun deleteImage(category:String,timestamp:String):Boolean{
        return repository.deleteImage(category, timestamp)
    }
    fun getUserDetails(): Task<DocumentSnapshot> {
        return repository.getUserDetails()
    }


    fun updateUserImage(uri: Uri){
        return repository.updateUserImage(uri)
    }

    fun getTimeline():LiveData<List<TimelineModel>>{
        repository.getTimeline().listAll().addOnSuccessListener {
            val timeList = mutableListOf<TimelineModel>()
            for (i in it.items) {
                i.metadata.addOnSuccessListener {
                    val timelineModel = TimelineModel(
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