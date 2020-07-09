package com.example.roomexample.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.R
import com.example.roomexample.room.Employee
import com.example.roomexample.databinding.ItemLayoutBinding

class DatabaseAdapter(private val eList : List<Employee>, private val context:Context) : RecyclerView.Adapter<DatabaseAdapter.MyViewHolder>(){
    class MyViewHolder(val binding:ItemLayoutBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(layoutInflater,
            R.layout.item_layout,parent,false)
        return MyViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return eList.size
    }

    fun getEntryAt(ePosition: Int): Employee {
        return eList[ePosition]
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = eList[position]
        holder.binding.employeeItem = currentItem

    }
}