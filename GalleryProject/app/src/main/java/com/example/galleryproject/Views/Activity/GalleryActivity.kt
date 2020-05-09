package com.example.galleryproject.Views.Activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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







}
