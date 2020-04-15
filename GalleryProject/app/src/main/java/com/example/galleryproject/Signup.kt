package com.example.galleryproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.signup.*
import kotlinx.android.synthetic.main.signup.view.*
import java.io.File
import java.util.*

class Signup : Fragment(){
    private lateinit var sName:String
    private lateinit var sEmail:String
    private lateinit var sPass:String
    private lateinit var auth: FirebaseAuth
    private lateinit var storageRef:StorageReference
    private lateinit var firestoreDB:FirebaseFirestore
    private lateinit var currentUID:String
    private var uri:Uri ?= null
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
                        currentUID = auth.currentUser!!.uid
                        uploadImage()
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
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"Pick image"),0)
            //uploadImage()
        }


        return view

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && data != null){
            uri = data.data
            Log.e("URI",uri.toString())
            //val bitmap = MediaStore.Images.Media.getBitmap()
            userImage.setImageURI(uri)
            Log.d("Image display","working")
        }
    }



    private fun uploadImage() {
        if (uri == null){
            Toast.makeText(context,"Please select image",Toast.LENGTH_SHORT).show()
            uri = Uri.parse("android.resource://com.example.galleryproject/" + R.drawable.user)
            Log.e("URI IS","$uri")
        }
            val filename = UUID.randomUUID().toString()
            storageRef = FirebaseStorage.getInstance().getReference("/images/$filename")
            storageRef.putFile(uri!!).addOnSuccessListener {
                Toast.makeText(context, "Image uploaded successfully!", Toast.LENGTH_SHORT).show()
                storageRef.downloadUrl.addOnSuccessListener {
                    Log.d("Location", "$it")
                    savetodatabase(it.toString())
                }
            }.addOnFailureListener{
                Log.e("Unable to upload","$it")
            }



    }

    private fun savetodatabase(imageID:String) {
        //val uID = auth.uid.toString()
        Log.e("IMAGE URI",imageID)
        firestoreDB = FirebaseFirestore.getInstance()
        val collection = firestoreDB.collection("UsersDetails").document(currentUID.toString())
        val userModel = UserModel(sName,sEmail,imageID)
        collection.set(userModel).addOnSuccessListener {
            Toast.makeText(context,"stored in db",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener(){
            Log.e("Error","$it")
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

