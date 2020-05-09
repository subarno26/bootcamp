package com.example.galleryproject.Views.Fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.galleryproject.R
import com.example.galleryproject.ViewModel.AddCategoryViewModel
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.android.synthetic.main.add_categories.*
import kotlinx.android.synthetic.main.add_categories.view.*

class AddCategory :Fragment(){
    private var viewModel = AddCategoryViewModel()
    private var uri:Uri ?= null
    private var loadingDialog:LoadingDialog ?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.add_categories,container,false)
        viewModel = ViewModelProvider(this).get(AddCategoryViewModel::class.java)
        loadingDialog = LoadingDialog(activity!!)
        setObservers()

        view.categoryImage.setOnClickListener{
            @RequiresApi(Build.VERSION_CODES.M)
            if (context?.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(
                    arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ),5
                )
            }
            else {
                val categoryImg = Intent()
                categoryImg.type = "image/*"
                categoryImg.action = Intent.ACTION_PICK
                startActivityForResult(categoryImg, 2)
            }
        }

        view.addCategoryBtn.setOnClickListener {
            if (uri == null){
                Toast.makeText(context,getString(R.string.select_image_for_category),Toast.LENGTH_SHORT).show()
            }
            else if (TextUtils.isEmpty(categoryEdit.editText!!.text)){
                categoryEdit.error = getString(R.string.prompt_enter_category_name)
            }
            else {
                val categoryName = categoryEdit.editText!!.text.toString()
               viewModel.addCategory(uri!!,categoryName)

            }
        }

        return view
    }

    private fun setObservers() {
        viewModel.getCategoryStatus().observe(viewLifecycleOwner, Observer {
            when(it){
                AddCategoryViewModel.AddCatProgress.SHOW_PROGRESS-> showProgress()
                AddCategoryViewModel.AddCatProgress.HIDE_PROGRESS -> hideProgress()
            }
        })
    }

    private fun showProgress() {
        loadingDialog!!.startLoadingDialog("Adding category, please wait.")
    }

    private fun hideProgress() {
        loadingDialog!!.dismissDialog()
        activity!!.navigationView.menu.getItem(0).isChecked = true
        val category =
            Categories()
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,category)
        transaction.commit()
        Toast.makeText(context,getString(R.string.category_success),Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data!= null && requestCode == 2){
            uri = data.data!!
            categoryImage.setImageURI(uri)
        }
    }


}