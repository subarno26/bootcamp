package com.example.galleryproject.Views.Fragments


interface ImageCallbackListener {
    fun onImageClick(url: String, categoryName: String, timestamp: String)
}