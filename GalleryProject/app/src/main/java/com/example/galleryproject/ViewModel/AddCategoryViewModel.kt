package com.example.galleryproject.ViewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository

class AddCategoryViewModel : ViewModel() {
    private val repository = Repository()
    fun addCategory(uri: Uri, categoryName: String):Boolean{
        return repository.addCategory(uri,categoryName)
    }
}