package com.example.galleryproject.Views.Fragments

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryproject.R
import com.example.galleryproject.ViewModel.CategoryImagesViewModel
import kotlinx.android.synthetic.main.category_images.view.*
import java.io.ByteArrayOutputStream

class CategoryImages : Fragment(), ImageCallbackListener {

    private var categoryName:String ?= null
    private var loadingDialog:LoadingDialog ?= null
    private var viewmodel = CategoryImagesViewModel()
    private lateinit var uri:Uri
    private lateinit var recycler : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.category_images,container,false)
        val bundle = this.arguments
        categoryName = bundle?.getString("catName").toString()
        view.textView.text = categoryName

        recycler = view.findViewById(R.id.recyclerImages) as RecyclerView
        recycler.layoutManager = GridLayoutManager(this.context,3)
        loadingDialog = LoadingDialog(activity!!)

        loadImages()

        view.chooseGallery.setOnClickListener{
            val galleryIntent = Intent()
            galleryIntent.type = "image/*"
            galleryIntent.action = Intent.ACTION_PICK
            startActivityForResult(galleryIntent,3)
        }

        view.chooseCamera.setOnClickListener{
            @RequiresApi(Build.VERSION_CODES.M)
            if (context?.checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(
                    arrayOf(
                        android.Manifest.permission.CAMERA
                    ),2
                )
            }
            else {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, 4)
            }
        }


        return view
    }

    private fun loadImages() {

        viewmodel = ViewModelProvider(this).get(CategoryImagesViewModel::class.java)
        viewmodel.loadImages(categoryName.toString()).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val imagesAdapter = ImagesAdapter(context, this)
            imagesAdapter.listChange(it)
            recycler.adapter = imagesAdapter
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data!=null && requestCode == 3){
            uri = data.data!!
            Log.e("URI GALLERY: ","$uri")
            //Toast.makeText(context,"Gallery",Toast.LENGTH_SHORT).show()
            loadingDialog!!.startLoadingDialog("Uploading image, please wait.")
            storeImage()
        }
        if (data!= null && requestCode == 4 ){
            val photo: Bitmap = data.extras?.get("data") as Bitmap
            uri = getImageUri(context,photo)
            Log.e("URI CAMERA: ","$uri")
            //Toast.makeText(context,"camera",Toast.LENGTH_SHORT).show()
            loadingDialog!!.startLoadingDialog("Uploading image, please wait.")
            storeImage()
        }
    }

    private fun getImageUri(context: Context?, photo: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG,100,bytes)
        val path: String = MediaStore.Images.Media.insertImage(
            context?.getContentResolver(),
            photo,
            "Title",
            null
        )
        val image = Uri.parse(path)
        return image


    }

    private fun storeImage() {
        viewmodel.storeImages(categoryName!!,uri)
        Toast.makeText(context,"Please wait while the image is being loaded.",Toast.LENGTH_LONG).show()
        loadingDialog!!.dismissDialog()
    }

    override fun onImageClick(url: String, categoryName: String, timestamp: String) {
        val expandedImage = ExpandedImage()
        val bundle = Bundle()
        bundle.putString("ImageURL",url)
        bundle.putString("CategoryName",categoryName)
        bundle.putString("TimeStamp",timestamp)
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        expandedImage.arguments = bundle
        transaction.replace(R.id.container,expandedImage)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}