package com.example.galleryproject

import android.net.Uri
import com.google.android.gms.tasks.Task

class TimelineModel(val url: Task<Uri>, val timestamp:Long) {
}