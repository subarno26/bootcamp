package com.example.galleryproject.Views.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import com.example.galleryproject.R
import kotlinx.android.synthetic.main.dialog_progress.view.*

class LoadingDialog(activity: Activity) {
    private val mActivity = activity
    private lateinit var dialog:AlertDialog
    @SuppressLint("InflateParams")
    fun startLoadingDialog(dialogMessage : String){
        val builder = AlertDialog.Builder(mActivity)
        val inflater = mActivity.layoutInflater
        val view = inflater.inflate(R.layout.dialog_progress,null)
        val text =view.progressText
        text.text = dialogMessage
        builder.setView(view)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
    fun dismissDialog(){
        dialog.dismiss()
    }
}