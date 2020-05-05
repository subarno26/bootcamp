package com.example.galleryproject.Views.Fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryproject.R
import com.squareup.picasso.Picasso

class CategoryAdapter(val categoryList: List<AddCategoryModel>, val mContext: Context?, val callbackListener: CallbackListener) : RecyclerView.Adapter<CategoryAdapter.MyHolder>() {
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
        holder.categoryName.setText(categoryList.get(position).categoryName)
        Picasso.get().load(categoryList.get(position).categoryImage).into(holder.categoryImage)
        holder.itemView.setOnClickListener{
            callbackListener.onItemClick(categoryList[position].categoryName)
        }
    }
}