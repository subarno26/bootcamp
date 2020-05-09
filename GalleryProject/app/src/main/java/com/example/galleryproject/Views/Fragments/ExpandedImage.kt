package com.example.galleryproject.Views.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.galleryproject.R
import com.example.galleryproject.ViewModel.ExpandedImageViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.expanded_image.view.*

class ExpandedImage : Fragment() {
    private var viewModel = ExpandedImageViewModel()
    private var category:String ?= null
    private var timeStamp:String?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.expanded_image, container, false)
        val bundle = this.arguments
        val mImage = bundle?.getString("ImageURL")
        category = bundle!!.getString("CategoryName")
        timeStamp = bundle.getString("TimeStamp")
        Picasso.get().load(mImage).into(view.expandedImageView)

        view.deleteImage.setOnClickListener {
            deleteImage(mImage.toString())
        }
        return view
    }

    private fun deleteImage(imageUrl: String) {
        viewModel = ViewModelProvider(this).get(ExpandedImageViewModel::class.java)
        if (viewModel.deleteImage(imageUrl,category!!,timeStamp!!)){
            Toast.makeText(context,"Deleted successfully",Toast.LENGTH_SHORT).show()
            val manager = activity!!.supportFragmentManager
            manager.popBackStack()
        }

    }
}

