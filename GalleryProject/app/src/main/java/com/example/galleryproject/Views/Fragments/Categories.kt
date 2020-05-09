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
import com.example.galleryproject.ViewModel.CategoriesViewModel
import com.example.galleryproject.Views.Adapters.CategoryAdapter
import com.example.galleryproject.Views.Interfaces.CategoryCallbackListener

class Categories:Fragment(),
    CategoryCallbackListener {
    private var viewModel = CategoriesViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.categories, container, false)
        val recyclerView = view.findViewById(R.id.recycler) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(context,3)
        viewModel =ViewModelProvider(this).get(CategoriesViewModel::class.java)
        viewModel.loadData().observe(viewLifecycleOwner, Observer {
            val cCategoryAdapter =
                CategoryAdapter(
                    it,
                    context,
                    this
                )
            recyclerView.adapter = cCategoryAdapter

        })


        return view
    }


    override fun onItemClick(categoryName: String) {
        val imagesFragment = CategoryImages()
        val bundle = Bundle()
        bundle.putString("catName", categoryName)
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        imagesFragment.arguments = bundle
        transaction.replace(R.id.container,imagesFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}

