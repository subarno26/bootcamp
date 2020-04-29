package com.example.galleryproject.Views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.galleryproject.R
import com.example.galleryproject.ViewModel.Viewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.expanded_image.view.*

class ExpandedImage : Fragment() {
    private lateinit var viewmodel: Viewmodel
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var category:String
    private lateinit var timeStamp:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.expanded_image, container, false)
        val bundle = this.arguments
        val mImage = bundle?.getString("ImageURL")
        category = bundle!!.getString("CategoryName")!!
        timeStamp = bundle.getString("TimeStamp")!!

        Log.i("Fetched", category)
        Log.i("Fetched", timeStamp)
        Picasso.get().load(mImage).into(view.expandedImageView)

        view.deleteImage.setOnClickListener {
            deleteImage()
        }
        return view
    }

    private fun deleteImage() {
        viewmodel = ViewModelProvider(this).get(Viewmodel::class.java)
        if (viewmodel.deleteImage(category,timeStamp)){
            Toast.makeText(context,"Deleted successfully",Toast.LENGTH_SHORT).show()
            val manager = activity!!.supportFragmentManager
            manager.popBackStack()
        }

    }
}

