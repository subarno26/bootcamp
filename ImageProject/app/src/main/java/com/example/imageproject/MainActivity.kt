package com.example.imageproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val REQUEST = 1
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDirImages()

        btn_capture.setOnClickListener {
            captureImage()
        }
    }

    private fun getDirImages() {
        val iLIst = arrayListOf<Bitmap>()
        val directory = createFolder(this)
        Log.i("PATH : ", directory!!.absolutePath)
        for(i in directory.listFiles()){
//            Log.i("file list: ",i.absolutePath)
            val path = i.absolutePath
            val dBitmap = BitmapFactory.decodeFile(path)
            iLIst.add(dBitmap)
        }
        Log.i("LIST" , iLIst.toString())
        recycler.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        val adapter : Adapter = Adapter(iLIst,this)
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    private fun captureImage() {
        val intent = Intent()
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        startActivityForResult(intent,REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST  && resultCode == Activity.RESULT_OK){
            if (data != null) {
                val imageBitmap = data.extras!!.get("data") as Bitmap
                Log.i(TAG,imageBitmap.toString())

                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = getFile(this,timeStamp)
                Log.i(TAG,"File location: ${getFile(this,timeStamp)}")

                //Writing the image to the file.
                val output = FileOutputStream(file)
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
                output.flush()
                output.close()
                Toast.makeText(this,"File created successfully!",Toast.LENGTH_SHORT).show()

                displayImage(file)
            }
        }

    }

    private fun displayImage(file: File?) {
        val decodedImage = BitmapFactory.decodeFile(file?.absolutePath)
        img_display.setImageBitmap(decodedImage)
        getDirImages()
    }

    private fun createFolder(context: Context) : File?{
        val folder = File(context.cacheDir,"/files/")
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                Log.e(TAG, "unable to create folder")
                return null
            }
        }
        return folder
    }

    private fun getFile(context: Context, fileName:String) : File?{
        val folder: File? = createFolder(context) ?: return null
        val file = File(folder,"$fileName.jpeg")
        if (!file.exists()) {
            if (!file.createNewFile()) {
                Log.e(TAG, "Cannot create file")
                return null
            }
        }
        return file
    }
}