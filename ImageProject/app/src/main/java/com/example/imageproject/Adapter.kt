package com.example.imageproject

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class Adapter(val iList : List<Bitmap>, val context : Context) : RecyclerView.Adapter<Myviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return Myviewholder(view)
    }

    override fun getItemCount(): Int {
        return iList.size
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        holder.rImage.setImageBitmap(iList[position])
    }
}

class Myviewholder(itemview: View) : RecyclerView.ViewHolder(itemview){
    val rImage = itemView.img_item
}
