package com.example.galleryproject.Views.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galleryproject.R
import com.example.galleryproject.Views.Models.ImageModel
import com.example.galleryproject.Views.Interfaces.ImageCallbackListener

class ImagesAdapter(private val mContext: Context?, private val imageCallbackListener: ImageCallbackListener) : RecyclerView.Adapter<ImagesAdapter.ImageHolder>() {
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
        return mImageList.size

    }

    fun listChange(images: List<ImageModel>){
        mImageList = images
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        Glide.with(mContext!!).load(mImageList[position].downloadURL).into(holder.cImage)
        //Picasso.get().load(mImageList[position].downloadURL).into(holder.cImage)
        holder.itemView.setOnClickListener{
            imageCallbackListener.onImageClick(mImageList[position].downloadURL,mImageList[position].categoryName,mImageList[position].timestamp)

        }
    }
}