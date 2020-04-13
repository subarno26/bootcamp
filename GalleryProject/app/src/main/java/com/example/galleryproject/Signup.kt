package com.example.galleryproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.signup.*
import kotlinx.android.synthetic.main.signup.view.*
import java.io.File

class Signup : Fragment(){
    private lateinit var sName:String
    private lateinit var sEmail:String
    private lateinit var sPass:String
    private lateinit var auth: FirebaseAuth
    private lateinit var storageRef:StorageReference
    val IMAGECODE = 1000
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.signup,container,false)
        auth = FirebaseAuth.getInstance()
        view.sSignup.setOnClickListener {
            if (!sValidate()){
                sValidate()
            }
            else {
                auth.createUserWithEmailAndPassword(sEmail, sPass).addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(context, GalleryActivity::class.java))
                    } else {
                        Log.e("Error", "" + task.exception)
                    }
                }
            }
        }

        view.userImage.setOnClickListener{
            uploadImage()
        }
        return view

    }



    private fun uploadImage() {
        storageRef = FirebaseStorage.getInstance().getReference("image_upload")
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent,"Pick image"),IMAGECODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uploadTask = storageRef!!.putFile(data!!.data!!)
        val task = uploadTask.continueWithTask{
            task->
            if (!task.isSuccessful){
                Toast.makeText(context,"Please try again",Toast.LENGTH_SHORT).show()
            }
            storageRef!!.downloadUrl
        }.addOnCompleteListener{
            task ->
            if (task.isSuccessful){
                Toast.makeText(context,"Uploaded successfully",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sValidate():Boolean {
        sName = sNameEdit.text.toString()
        sEmail = sEmailEdit.text.toString()
        sPass = sPasswordEdit.text.toString()
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
        if (!sEmail.contains("@") || !sEmail.contains(".com")){
            sEmailEdit.error = "Enter a valid email address"
            v = false
        }
        if (sPass.length < 6) {
            sPasswordEdit.error = "Password is too short"
            v = false
        }
        if (sName.length < 4) {
            sNameEdit.error = "Name is too short"
            v = false
        }
        return v
    }

}

