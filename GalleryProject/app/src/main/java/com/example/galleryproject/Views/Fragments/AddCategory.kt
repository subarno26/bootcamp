package com.example.galleryproject.Views.Fragments

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.galleryproject.R
import com.example.galleryproject.ViewModel.Viewmodel
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.android.synthetic.main.add_categories.*
import kotlinx.android.synthetic.main.add_categories.view.*

class AddCategory :Fragment(){
    private var viewmodel: Viewmodel = Viewmodel()
    private var uri:Uri ?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.add_categories,container,false)

        view.categoryImage.setOnClickListener{
            val categoryImg = Intent()
            categoryImg.type = "image/*"
            categoryImg.action = Intent.ACTION_PICK
            startActivityForResult(categoryImg,2)
        }

        view.addCategoryBtn.setOnClickListener {
            if (uri == null){
                Toast.makeText(context,"Please select an image for the category",Toast.LENGTH_SHORT).show()
            }
            else if (TextUtils.isEmpty(categoryEdit.text)){
                Toast.makeText(context,"Please enter name of the category",Toast.LENGTH_SHORT).show()
            }
            else {
                val loadingDialog = LoadingDialog(activity!!)
                loadingDialog.startLoadingDialog("Adding category, please wait.")
                val categoryName = categoryEdit.text.toString()
                viewmodel = ViewModelProvider(this).get(Viewmodel::class.java)
                if (viewmodel.addCategory(uri!!,categoryName)){
                    loadingDialog.dismissDialog()
                    activity!!.navigationView.menu.getItem(0).setChecked(true)
                    val category =
                        Categories()
                    val transaction = activity!!.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.container,category)
                    transaction.commit()
                    Toast.makeText(context,"Category added successfully",Toast.LENGTH_SHORT).show()
                }



            }
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data!= null && requestCode == 2){
            uri = data.data!!
            categoryImage.setImageURI(uri)
        }
    }
}