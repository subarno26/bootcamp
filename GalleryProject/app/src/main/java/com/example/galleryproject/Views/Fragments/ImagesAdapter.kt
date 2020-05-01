package com.example.galleryproject.Views.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryproject.R
import com.squareup.picasso.Picasso

class ImagesAdapter(val mContext: Context?) : RecyclerView.Adapter<ImagesAdapter.ImageHolder>() {
    private lateinit var mImageList: List<ImageModel>
    class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cImage : ImageView = itemView.findViewById(R.id.catImages)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
       val v: View = LayoutInflater.from(mContext).inflate(R.layout.images_layout,parent,false)
        return ImageHolder(
            v
        )
    }

    override fun getItemCount(): Int {
        //Log.i("Imagelist size",imageList.size.toString())
        return mImageList.size

    }

    fun listChange(images: List<ImageModel>){
        mImageList = images
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        Picasso.get().load(mImageList[position].downloadURL).into(holder.cImage)
        holder.itemView.setOnClickListener{
            val expandedImage =
                ExpandedImage()
            val bundle = Bundle()
            bundle.putString("ImageURL",mImageList[position].downloadURL)
            bundle.putString("CategoryName",mImageList[position].CategoryName)
            bundle.putString("TimeStamp",mImageList[position].Timestamp)
            val activity: AppCompatActivity = it.context as AppCompatActivity
            val transaction = activity.supportFragmentManager.beginTransaction()
            expandedImage.arguments = bundle
            transaction.replace(R.id.container,expandedImage)
            transaction.addToBackStack("expanded image")
            transaction.commit()
        }
    }
}