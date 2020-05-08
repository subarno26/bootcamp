package com.example.galleryproject.Views.Fragments

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.example.galleryproject.R
import kotlinx.android.synthetic.main.dialog_progress.view.*

class LoadingDialog(context: Context) {
    private val mContext = context
    private lateinit var dialog: Dialog
    fun startLoadingDialog(dialogMessage : String){
        val builder = AlertDialog.Builder(mContext)
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_progress,null)
        val text =view.progressText
        text.text = dialogMessage
        builder.setView(view)
        dialog = builder.create()
        dialog.show()
    }
    fun dismissDialog(){
        dialog.dismiss()
    }
}