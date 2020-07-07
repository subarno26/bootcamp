package com.example.roomexample.View

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.R
import com.example.roomexample.Room.Employee
import com.example.roomexample.ViewModel.EmployeeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: EmployeeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        //SETTING OBSERVERS FOR LISTENING TO CHANGES
        setObservers()

        getDetails()

        btn_save.setOnClickListener {
            saveEntries()
        }

        btn_fetch.setOnClickListener {
            //fetchDetails()
        }

        btn_search.setOnClickListener {
            findByName()
        }
TODO("Not yet implemented")


    }

    private fun setObservers() {
        viewModel.employeeList.observe(this, Observer {
            val fetchedList = it
            Log.i("FETCHED LIST",fetchedList.toString())
        })

    }

    private fun getDetails() {
        val getSp = getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        et_id.setText(getSp.getInt("empID",1).toString())
        et_firstName.setText(getSp.getString("firstName",""))
        et_lastName.setText(getSp.getString("lastName",""))
        et_city.setText(getSp.getString("city",""))
    }

    private fun findByName() {
        val fName = et_search.text.toString()
        if(TextUtils.isEmpty(fName)){
            Toast.makeText(this,"Search field cannot be left empty",Toast.LENGTH_SHORT).show()
        }
        else {
                val fetched = viewModel.getByName(fName).observe(this, Observer {
                  Log.i("TAG",it.toString())
                })
                Log.i("Fetched", fetched.toString())
        }
    }

//    private fun fetchDetails() {
//        viewModel.employeeList.observe(this, Observer {
//            Log.i("LIST",it.toString())
//            val eList = it
//            Log.i("ELIST",eList.toString())
//        })
//    }

    private fun saveEntries() {

        val eID = et_id.text.toString().toInt()
        val firstName = et_firstName.text.toString()
        val lastName = et_lastName.text.toString()
        val city = et_city.text.toString()



        if (TextUtils.isEmpty(eID.toString()) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(city)){
            Toast.makeText(this,"Please fill in all the fields.",Toast.LENGTH_SHORT).show()
        }
        else {
            if (checkBox.isChecked){
                setSharedPreferences(eID,firstName,lastName,city)
            }

                val employeeDetails = Employee(
                    eID,
                    firstName,
                    lastName,
                    city
                )
                viewModel.insert(employeeDetails)
            Toast.makeText(this,"Data save successful.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSharedPreferences(id:Int,fName:String,lName:String,city:String) {
        val sp = getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putInt("empID",id)
        editor.putString("firstName",fName)
        editor.putString("lastName",lName)
        editor.putString("city",city)
        editor.commit()

    }


}
