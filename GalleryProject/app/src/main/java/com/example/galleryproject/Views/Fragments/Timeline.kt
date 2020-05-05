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
import com.example.galleryproject.ViewModel.Viewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference

class Timeline:Fragment() {
    private var viewmodel: Viewmodel = Viewmodel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.timeline,container,false)
        val recyclerView = view.findViewById(R.id.timelineRecycler) as RecyclerView

        recyclerView.layoutManager = GridLayoutManager(context,3)
        viewmodel = ViewModelProvider(this).get(Viewmodel::class.java)
        viewmodel.getTimeline().observe(viewLifecycleOwner, Observer {
            val tAdapter =
                TimelineAdapter(
                    it,
                    context
                )
            recyclerView.adapter = tAdapter
        })

        return view
    }
}