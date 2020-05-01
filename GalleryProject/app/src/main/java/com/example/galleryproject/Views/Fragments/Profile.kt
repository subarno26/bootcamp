package com.example.galleryproject.Views.Fragments

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.galleryproject.R
import com.example.galleryproject.ViewModel.Viewmodel
import com.example.galleryproject.Views.Activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.profile.view.*

class Profile : Fragment() {
    private lateinit var viewmodel: Viewmodel
    private lateinit var storageRef:StorageReference
    private lateinit var auth: FirebaseAuth
    private lateinit var db:FirebaseFirestore
    private lateinit var uri: Uri
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.profile,container,false)
        viewmodel = ViewModelProvider(this).get(Viewmodel::class.java)
        userDetails()
        view.logoutBtn.setOnClickListener {
            viewmodel.logout()
            startActivity(Intent(context,
                MainActivity::class.java))
        }

        view.uploadProfile.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"Upload image"),1)
            
        }

        return view
    }
    
    private lateinit var imageProgress:ProgressDialog

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && data!= null){
            uri = data.data!!

            imageProgress = ProgressDialog(context)
            imageProgress.setTitle("Updating profile")
            imageProgress.setMessage("Please wait")
            imageProgress.show()
            
            profile_image.setImageURI(uri)
            updateImage()
        }
    }

    private fun updateImage() {
        viewmodel.updateUserImage(uri)
        imageProgress.dismiss()
    }





    private lateinit var progress:ProgressDialog
    private fun userDetails() {
        progress = ProgressDialog(context)
        progress.setTitle("Fetching user data")
        progress.setMessage("Please wait")
        progress.show()
        viewmodel.getUserDetails()
        .addOnSuccessListener { document ->
            if (document != null) {
                Log.e("DATA", "DocumentSnapshot data: ${document.data}")
                username.text = document.getString("username")
                userEmail.text = document.getString("emailID")
                Picasso.get().load(document.getString("imageID")).into(profile_image)
                progress.dismiss()
            } else {
                Log.e("NO DOCUMENT", "No such document")
            }
        }
            .addOnFailureListener { exception ->
                Log.e("Failed", "get failed with ", exception)
                Toast.makeText(context,"Unable to fetch user details, please try again later.",Toast.LENGTH_SHORT).show()
            }
    }
}