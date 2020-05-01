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
import com.google.firebase.firestore.FirebaseFirestore

class Categories:Fragment() {
    private lateinit var viewmodel: Viewmodel
    private lateinit var auth: FirebaseAuth
    private lateinit var firestoreRef: FirebaseFirestore
    private lateinit var cCategoryAdapter: CategoryAdapter
    private lateinit var categoryList: ArrayList<AddCategoryModel>
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.categories, container, false)
        recyclerView = view.findViewById(R.id.recycler) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(context,3)
        auth = FirebaseAuth.getInstance()
        firestoreRef = FirebaseFirestore.getInstance()

        categoryList = arrayListOf()
        viewmodel =ViewModelProvider(this).get(Viewmodel::class.java)
        viewmodel.loadData().observe(viewLifecycleOwner, Observer {
            cCategoryAdapter =
                CategoryAdapter(
                    it,
                    context
                )
            recyclerView.adapter = cCategoryAdapter

        })


        return view
    }


}

