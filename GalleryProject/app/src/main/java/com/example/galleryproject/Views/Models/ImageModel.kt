package com.example.galleryproject.Views.Models

class ImageModel {
    val downloadURL: String
    val timestamp: String
    val categoryName :String

    constructor(downloadURL: String, timestamp: String,categoryName :String) {
        this.downloadURL = downloadURL
        this.timestamp = timestamp
        this.categoryName = categoryName
    }
}