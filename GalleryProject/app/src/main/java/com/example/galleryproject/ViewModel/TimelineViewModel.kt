package com.example.galleryproject.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryproject.Model.Repository
import com.example.galleryproject.Views.Fragments.TimelineModel

class TimelineViewModel :ViewModel(){
    private val repository = Repository()
    private var mTimeList: MutableLiveData<List<TimelineModel>> = MutableLiveData()
    private lateinit var tList:List<TimelineModel>
    private var timeLineStatus = MutableLiveData<TimelineProgress>()

    fun getTimelineStatus(): LiveData<TimelineProgress> {
        return timeLineStatus
    }

    fun getTimeline(): LiveData<List<TimelineModel>> {
        timeLineStatus.value = TimelineProgress.SHOW_PROGRESS
        repository.getTimeline().listAll().addOnSuccessListener {
            if (it != null) {
                timeLineStatus.value = TimelineProgress.HIDE_PROGRESS
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
            } else{
                Log.i("Error loading timeline", "$it")
                timeLineStatus.value = TimelineProgress.HIDE_PROGRESS
            }
        }
        return mTimeList
    }

    enum class TimelineProgress{
        SHOW_PROGRESS,
        HIDE_PROGRESS
    }
}
