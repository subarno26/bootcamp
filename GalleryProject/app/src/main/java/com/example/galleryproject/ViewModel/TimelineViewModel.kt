package com.example.galleryproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository
import com.example.galleryproject.Views.Fragments.TimelineModel

class TimelineViewModel :ViewModel(){
    private val repository = Repository()
    private var mTimeList: MutableLiveData<List<TimelineModel>> = MutableLiveData()
    private lateinit var tList:List<TimelineModel>

    fun getTimeline(): LiveData<List<TimelineModel>> {
        repository.getTimeline().listAll().addOnSuccessListener {
            val timeList = mutableListOf<TimelineModel>()
            for (i in it.items) {
                i.metadata.addOnSuccessListener {
                    val timelineModel =
                        TimelineModel(
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
