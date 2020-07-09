package com.example.roomexample.Util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.Adapter.DatabaseAdapter
import com.example.roomexample.ViewModel.EmployeeViewModel

class DeleteEntry(var viewmodel:EmployeeViewModel,var adapter: DatabaseAdapter) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.adapterPosition
        viewmodel.deleteEntry(adapter.getEntryAt(pos))
    }
}