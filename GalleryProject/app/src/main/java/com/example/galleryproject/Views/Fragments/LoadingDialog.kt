package com.example.galleryproject.Views.Fragments

import android.app.Activity
import android.app.AlertDialog
import com.example.galleryproject.R
import kotlinx.android.synthetic.main.dialog_progress.view.*

class LoadingDialog(activity: Activity) {
    private val mActivity = activity
    private lateinit var dialog:AlertDialog
    fun startLoadingDialog(dialogMessage : String){
        val builder = AlertDialog.Builder(mActivity)
        val inflater = mActivity.layoutInflater
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