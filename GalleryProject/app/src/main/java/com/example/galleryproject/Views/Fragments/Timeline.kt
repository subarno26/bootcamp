package com.example.galleryproject.Views.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryproject.R
import com.example.galleryproject.ViewModel.TimelineViewModel

class Timeline:Fragment() {
    private var viewModel = TimelineViewModel()
    private var loadingDialog:LoadingDialog ?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.timeline,container,false)
        val recyclerView = view.findViewById(R.id.timelineRecycler) as RecyclerView
        viewModel = ViewModelProvider(this).get(TimelineViewModel::class.java)
        setObservers()
        loadingDialog = LoadingDialog(activity!!)
        recyclerView.layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
        viewModel.getTimeline().observe(viewLifecycleOwner, Observer {
            val tAdapter =
                TimelineAdapter(
                    it,
                    context
                )
            recyclerView.adapter = tAdapter
        })

        return view
    }

    private fun setObservers() {
        viewModel.getTimelineStatus().observe(viewLifecycleOwner, Observer {
            when(it){
                TimelineViewModel.TimelineProgress.SHOW_PROGRESS -> showProgress()
                TimelineViewModel.TimelineProgress.HIDE_PROGRESS -> hideProgress()
            }
        })
    }

    private fun showProgress() {
        loadingDialog!!.startLoadingDialog("Loading your timeline")
    }

    private fun hideProgress() {
        loadingDialog!!.dismissDialog()
    }
}