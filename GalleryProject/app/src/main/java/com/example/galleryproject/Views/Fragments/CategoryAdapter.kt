package com.example.galleryproject.Views.Fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galleryproject.R
import com.squareup.picasso.Picasso

class CategoryAdapter(private val categoryList: List<AddCategoryModel>, private val mContext: Context?, private val categoryCallbackListener: CategoryCallbackListener) : RecyclerView.Adapter<CategoryAdapter.MyHolder>() {
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var categoryImage: ImageView = itemView.findViewById(R.id.cImage)
        var categoryName:TextView = itemView.findViewById(R.id.categoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(
                mContext
            ).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.categoryName.text = categoryList[position].categoryName
        Glide.with(mContext!!).load(categoryList[position].categoryImage).into(holder.categoryImage)
        //Picasso.get().load(categoryList[position].categoryImage).into(holder.categoryImage)
        holder.itemView.setOnClickListener{
            categoryCallbackListener.onItemClick(categoryList[position].categoryName)
        }
    }
}