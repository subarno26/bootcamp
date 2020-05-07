package com.example.galleryproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository
import com.example.galleryproject.Views.Fragments.AddCategoryModel

class CategoriesViewModel : ViewModel(){
    private val repository = Repository()
    private var mCategoryList: MutableLiveData<List<AddCategoryModel>> = MutableLiveData()
    val categoryList = mutableListOf<AddCategoryModel>()

    fun loadData(): LiveData<List<AddCategoryModel>> {
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
}