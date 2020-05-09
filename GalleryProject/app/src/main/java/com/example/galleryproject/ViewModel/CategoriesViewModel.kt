package com.example.galleryproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository
import com.example.galleryproject.Views.Models.AddCategoryModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class CategoriesViewModel : ViewModel(){
    private val repository = Repository()
    private var mCategoryList: MutableLiveData<List<AddCategoryModel>> = MutableLiveData()



    fun loadData(): LiveData<List<AddCategoryModel>> {
        repository.loadData()
            .addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
                if (e != null) {
                    //Log.w(TAG, "Listen Fail", e)
                    mCategoryList.value = null
                    return@EventListener
                }
                val categoryList = mutableListOf<AddCategoryModel>()
                for (doc in value!!) {
                    val fetchedCategory =
                        AddCategoryModel(
                            doc.getString("categoryName")!!,
                            doc.getString("categoryImage")!!
                        )
                    categoryList.add(fetchedCategory)
                }
                mCategoryList.value = categoryList
                //Log.d(TAG, savedImagesList.toString())
            })
        return mCategoryList
    }
}