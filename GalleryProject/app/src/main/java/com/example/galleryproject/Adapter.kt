package com.example.galleryproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter(val categoryList: List<AddCategoryModel>, val mContext: Context?) : RecyclerView.Adapter<Adapter.MyHolder>() {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        init {
//            itemView.setOnClickListener{TODO("Not yet implemented")
//                Log.e("ItemClick","CLICKED")
//            }
//        }
        var categoryImage: ImageView = itemView.findViewById(R.id.cImage)
        var categoryName:TextView = itemView.findViewById(R.id.categoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.categoryName.setText(categoryList.get(position).categoryName)
        Picasso.get().load(categoryList.get(position).categoryImage).into(holder.categoryImage)
        holder.itemView.setOnClickListener{
            val imagesFragment = Images()
            val expandedImage = ExpandedImage()
            val bundle1 = Bundle()
            val bundle = Bundle()
            bundle.putString("catName", categoryList[position].categoryName)
            bundle1.putString("catName", categoryList[position].categoryName)
            val activity:AppCompatActivity = it.context as AppCompatActivity
            expandedImage.arguments = bundle1
            val transaction = activity.supportFragmentManager.beginTransaction()
            imagesFragment.arguments = bundle
            transaction.addToBackStack("open category")
            transaction.replace(R.id.container,imagesFragment)
            transaction.commit()

        }
    }
}