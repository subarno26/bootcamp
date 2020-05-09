package com.example.galleryproject.Model

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.galleryproject.*
import com.example.galleryproject.Views.Fragments.AddCategoryModel
import com.example.galleryproject.Views.Fragments.ImageModel
import com.example.galleryproject.Views.Fragments.UserModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

@Suppress("UNREACHABLE_CODE")
class FirebaseModel {
    private val auth = FirebaseAuth.getInstance()
    private lateinit var storageRef: StorageReference
    private lateinit var firestore: FirebaseFirestore
    private lateinit var currentUID: String

    fun checkUserLogin(): Boolean {
        val currentUser = auth.currentUser
        var loggedIn = false
        if (currentUser != null){
            loggedIn = true
        }
        return loggedIn
    }

    fun login(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun signUp(Name: String, Email: String, Password: String, uri: Uri?): Boolean {
        auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                currentUID = auth.currentUser!!.uid
                uploadImage(Name, Email, uri)
                //startActivity(Intent(context, GalleryActivity::class.java))
            } else {
                Log.e("Error", "" + task.exception)
            }
        }
        return true
    }

    private fun uploadImage(Name: String, Email: String, uri: Uri?) {
        var uri1 = uri
        if (uri == null) {
            //Toast.makeText(MainActivity(), "Please select image", Toast.LENGTH_SHORT).show()
            uri1 = Uri.parse("android.resource://com.example.galleryproject/" + R.drawable.user)
            Log.e("URI IS", "$uri1")
        }
        val filename = UUID.randomUUID().toString()
        storageRef = FirebaseStorage.getInstance().getReference("/images/$filename")
        storageRef.putFile(uri1!!).addOnSuccessListener {
//            Toast.makeText(MainActivity(), "Image uploaded successfully!", Toast.LENGTH_SHORT)
//                .show()
            storageRef.downloadUrl.addOnSuccessListener {
                Log.d("Location", "$it")
                savetodatabase(Name, Email, it.toString())
            }
        }.addOnFailureListener {
            Log.e("Unable to upload", "$it")
        }
    }

    private fun savetodatabase(sName: String, sEmail: String, imageID: String) {
        //val uID = auth.uid.toString()
        Log.e("IMAGE URI", imageID)
        firestore = FirebaseFirestore.getInstance()
        val collection = firestore.collection("UsersDetails").document(currentUID)
        val userModel =
            UserModel(
                sName,
                sEmail,
                imageID
            )
        collection.set(userModel).addOnSuccessListener {
            Log.e("FIREBASE MODEL ", "successful")
            //Toast.makeText(MainActivity(), "stored in db", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Log.e("Error", "$it")
        }

    }

    fun loadData() : CollectionReference {
        val userID = auth.uid.toString()
        //val source = Source.CACHE
        firestore = FirebaseFirestore.getInstance()
        return firestore.collection("UsersDetails").document(userID).collection("Categories")

    }

    fun addCategory(uri: Uri, categoryName: String):MutableLiveData<Result<Boolean>>{
        val result = MutableLiveData<Result<Boolean>>()
        val filename = UUID.randomUUID().toString()
        firestore = FirebaseFirestore.getInstance()
        storageRef = FirebaseStorage.getInstance().getReference("CategoryImages/$filename")
       storageRef.putFile(uri).addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener {
                Log.d("Location", "$it")
                uploadCategory(categoryName,it.toString()).addSnapshotListener{snapshot, exception ->
                    if (exception!=null){
                        return@addSnapshotListener
                    }
                    if (snapshot!=null){
                        result.value = Result.success(true)
                    }
                    else{
                        result.value = exception?.let { it1 -> Result.failure(it1) }
                    }

                }
            }
        }.addOnFailureListener {
            Log.e("Unable to upload", "$it")
        }
        return result

    }
    private fun uploadCategory(categoryName: String, categoryImageID: String): DocumentReference {
        val userId = auth.uid.toString()

        val categoryInfo =
            AddCategoryModel(
                categoryName,
                categoryImageID
            )

        val documentReference:DocumentReference= firestore.collection("UsersDetails").document(userId)
            documentReference.collection("Categories").document(categoryName)
            .set(categoryInfo)
        return documentReference

    }

    fun loadImages(categoryName: String): CollectionReference {
        val uId = auth.uid.toString()
        firestore = FirebaseFirestore.getInstance()
        return firestore.collection("UsersDetails").document(uId)
            .collection("Categories").document(categoryName)
            .collection("Category images")
    }

    fun storeImages(categoryName: String,uri: Uri) : MutableLiveData<Result<Boolean>>{
        val result = MutableLiveData<Result<Boolean>>()
        val user = auth.uid.toString()
        val filename = UUID.randomUUID().toString()
        storageRef = FirebaseStorage.getInstance().getReference("CategoryImages/$user/$filename")
        storageRef.putFile(uri).addOnCompleteListener {
            storageRef.downloadUrl.addOnSuccessListener {
                Log.d("Location", "$it")
                //Toast.makeText(context,"Uploaded successfully",Toast.LENGTH_SHORT).show()
                uploadToDatabase(it.toString(),categoryName).addSnapshotListener{snapshot, exception ->
                    if (exception!=null){
                        return@addSnapshotListener
                    }
                    if (snapshot!= null){
                        result.value = Result.success(true)
                    }
                    else{
                        result.value = exception?.let { it1 -> Result.failure(it1) }
                    }
                }
            }
        }.addOnFailureListener{
            Log.e("Unable to upload","$it")
        }
        return result
    }

    private fun uploadToDatabase(file: String,categoryName: String) : DocumentReference {
        val userId = auth.uid.toString()
        val calendar = Calendar.getInstance()
        val timestamp = calendar.timeInMillis.toString()

        val imageInfo = ImageModel(
            file,
            timestamp,
            categoryName
        )


        val documentReference: DocumentReference = firestore.collection("UsersDetails").document(userId)
            documentReference.collection("Categories").document(categoryName)
            .collection("Category images").document(timestamp)
            .set(imageInfo)

        return documentReference
    }

    fun deleteImage(imageUrl: String, category:String, timestamp: String):Boolean{
        firestore = FirebaseFirestore.getInstance()
        val uId = auth.uid.toString()
        var v = true


        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl)
        storageReference.delete().addOnSuccessListener {
            Log.d("Deletion", "delete success")
        }
            .addOnFailureListener {
                Log.d("FAILED", "${it.message}")
            }

        firestore.collection("UsersDetails").document(uId)
            .collection("Categories").document(category)
            .collection("Category images").document(timestamp).delete()
            .addOnCompleteListener{
                //Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show()
                Log.d("Firebasemodel","Deleted from db")
                v = true

            }.addOnFailureListener{
                Log.e("error","$it.exception")
                v = false
            }


        return v

    }
    fun getUserDetails():Task<DocumentSnapshot>{
        firestore = FirebaseFirestore.getInstance()
        val documentID = auth.uid.toString()
        Log.e("USERID",documentID)
        return firestore.collection("UsersDetails").document(documentID).get()
    }

    fun logout(){
        auth.signOut()
    }

    fun updateUserImage(uri: Uri): MutableLiveData<Result<Boolean>>{
        val result = MutableLiveData<Result<Boolean>>()
        val filename = UUID.randomUUID().toString()
        storageRef = FirebaseStorage.getInstance().getReference("/images/$filename")
        storageRef.putFile(uri).addOnSuccessListener {
            // Toast.makeText(context, "Image uploaded successfully!", Toast.LENGTH_SHORT).show()
            storageRef.downloadUrl.addOnSuccessListener {
                Log.d("Location", "$it")
                firestore = FirebaseFirestore.getInstance()
                val docID = auth.uid.toString()
                firestore.collection("UsersDetails").document(docID)
                    .update("imageID",it.toString())
                .addOnSuccessListener {
                    result.value = Result.success(true)
                }.addOnFailureListener{
                        result.value = Result.failure(it)
                    }
            }
        }.addOnFailureListener{
            Log.e("Unable to upload","$it")
        }
        return result
    }

    fun getTimeline(): StorageReference {
        val userID = auth.uid
        storageRef = FirebaseStorage.getInstance().getReference("CategoryImages/$userID")
        return storageRef

    }



}

