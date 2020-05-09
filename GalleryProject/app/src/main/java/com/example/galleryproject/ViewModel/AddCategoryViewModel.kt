package com.example.galleryproject.ViewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository

class AddCategoryViewModel : ViewModel() {
    private val repository = Repository()
    private var addCategoryStatus = MediatorLiveData<AddCatProgress>()

    fun getCategoryStatus(): LiveData<AddCatProgress>{
        return addCategoryStatus
    }

    fun addCategory(uri: Uri, categoryName: String){
        addCategoryStatus.value = AddCatProgress.SHOW_PROGRESS
        addCategoryStatus.addSource(repository.addCategory(uri, categoryName), Observer {
            it.onSuccess{
                addCategoryStatus.value = AddCatProgress.HIDE_PROGRESS
            }
            it.onFailure{
                addCategoryStatus.value = AddCatProgress.HIDE_PROGRESS
            }
        })
    }

    enum class AddCatProgress{
        SHOW_PROGRESS,
        HIDE_PROGRESS
    }
}