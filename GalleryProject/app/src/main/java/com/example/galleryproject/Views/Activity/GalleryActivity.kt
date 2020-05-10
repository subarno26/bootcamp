package com.example.galleryproject.Views.Activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.galleryproject.R
import com.example.galleryproject.Views.Fragments.*
import kotlinx.android.synthetic.main.activity_gallery.*

class   GalleryActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val categoryFragment =
            Categories()
        supportFragmentManager.
            beginTransaction().
            replace(R.id.container,categoryFragment)
            .commit()
        navigationView.setOnNavigationItemSelectedListener {
            item ->
            when(item.itemId){

                R.id.profile_item -> {
                    val profileFragment =
                        Profile()
                    supportFragmentManager.
                        beginTransaction().
                        replace(R.id.container,profileFragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.gallery_item -> {
                    val categoriesFragment =
                        Categories()
                    supportFragmentManager.
                        beginTransaction().
                        replace(R.id.container,categoriesFragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.add_item -> {
                    val addFragment =
                        AddCategory()
                    supportFragmentManager.
                        beginTransaction().
                        replace(R.id.container,addFragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.timeline_item -> {
                    val timelineFragment =
                        Timeline()
                    supportFragmentManager.
                        beginTransaction().
                        replace(R.id.container,timelineFragment)
                        .addToBackStack(null)
                        .commit()
                }

            }
            true
        }

    }

    override fun onBackPressed() {
        Log.i("LOG","BACK PRESS")
        val manager = supportFragmentManager
        Log.i("BACKSTACK COUNT",manager.backStackEntryCount.toString())

        if (manager.backStackEntryCount > 0){
            super.onBackPressed()
            val currentFragment = manager.findFragmentById(R.id.container)
            Log.i("CURRENT FRAGMENT",currentFragment.toString())
            when (currentFragment) {
                is Categories -> {
                    navigationView.menu.getItem(0).isChecked = true
                    Log.i("Timeline","Selected")
                }
                is CategoryImages -> {
                    navigationView.menu.getItem(0).isChecked = true
                }
                is ExpandedImage -> {
                    navigationView.menu.getItem(0).isChecked = true
                }
                is Timeline -> {
                    navigationView.menu.getItem(1).isChecked = true
                }
                is AddCategory -> {
                    navigationView.menu.getItem(2).isChecked = true
                }
                is Profile -> {
                    navigationView.menu.getItem(3).isChecked = true
                }
            }


        }

    }







}
