package com.example.galleryproject.Views.Interfaces


interface ImageCallbackListener {
    fun onImageClick(url: String, categoryName: String, timestamp: String)
}