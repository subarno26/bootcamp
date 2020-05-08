package com.example.galleryproject.Views.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.galleryproject.R
import com.example.galleryproject.ViewModel.SignupViewModel
import com.example.galleryproject.Views.Activity.GalleryActivity
import com.example.galleryproject.Views.Activity.LoginActivity
import kotlinx.android.synthetic.main.signup.*
import kotlinx.android.synthetic.main.signup.view.*

class Signup : Fragment(){
    private var viewmodel = SignupViewModel()
    private var sName:String ?= null
    private var sEmail:String?= null
    private var sPass:String?= null
    private var uri:Uri ?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.signup,container,false)
        viewmodel = ViewModelProvider(this).get(SignupViewModel::class.java)
        view.sSignup.setOnClickListener {
            if (!sValidate()){
//                sValidate()
            }
            else {
                if (viewmodel.signup(sName!!,sEmail!!,sPass!!,uri)) {
                    val intent = Intent(context,
                        GalleryActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(context,"Sign up successful",Toast.LENGTH_SHORT).show()
                }

            }
        }

        view.userImage.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"Pick image"),0)
        }

        view.existingUserButton.setOnClickListener {
            startActivity(Intent(context,LoginActivity::class.java))
        }


        return view

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && data != null){
            uri = data.data
            Log.e("URI",uri.toString())
            userImage.setImageURI(uri)
            Log.d("Image display","working")
        }
    }

    private fun sValidate():Boolean {
        sName = sNameEdit.editText!!.text.toString()
        sEmail = sEmailEdit.editText!!.text.toString()
        sPass = sPasswordEdit.editText!!.text.toString()
        var v = true
        if (TextUtils.isEmpty(sName)) {
            sNameEdit.error = "Required Field"
            v = false
        }
        if (TextUtils.isEmpty(sEmail)) {
            sEmailEdit.error = "Required Field"
            v = false
        }
        if (TextUtils.isEmpty(sPass)) {
            sPasswordEdit.error = "Required Field"
            v = false
        }
        if (!sEmail!!.contains("@") || !sEmail!!.contains(".com")){
            sEmailEdit.error = "Enter a valid email address"
            v = false
        }
        if (sPass!!.length < 6) {
            sPasswordEdit.error = "Password is too short"
            v = false
        }
        if (sName!!.length < 4) {
            sNameEdit.error = "Name is too short"
            v = false
        }
        return v
    }

}

