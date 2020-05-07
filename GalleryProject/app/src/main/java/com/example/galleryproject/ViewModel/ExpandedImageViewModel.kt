package com.example.galleryproject.ViewModel

import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository

class ExpandedImageViewModel : ViewModel() {
    private val repository = Repository()
    fun deleteImage(imageUrl: String,category:String,timestamp:String):Boolean{
        return repository.deleteImage(imageUrl, category, timestamp)
    }
}