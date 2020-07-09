package com.example.roomexample.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexample.Adapter.DatabaseAdapter
import com.example.roomexample.Util.DeleteEntry
import com.example.roomexample.R
import com.example.roomexample.ViewModel.EmployeeViewModel
import com.example.roomexample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: EmployeeViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        binding.recycler.layoutManager = LinearLayoutManager(this)

        //SETTING OBSERVERS FOR LISTENING TO CHANGES
        setObservers()

        binding.fabAddNew.setOnClickListener {
            addNewEntry()
        }



    }

    private fun addNewEntry() {
        val addEntryFragment = AddEntryFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container,addEntryFragment).addToBackStack(null).commit()
    }

    private fun setObservers() {
        viewModel.employeeList.observe(this, Observer {
            val fetchedList = it
            Log.i("FETCHED LIST",fetchedList.toString())
            val adapter = DatabaseAdapter(
                fetchedList,
                this
            )
            binding.recycler.adapter = adapter

            val itemTouchHelper = ItemTouchHelper(
                DeleteEntry(
                    viewModel,
                    adapter
                )
            )
            itemTouchHelper.attachToRecyclerView(recycler)

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAllEntries -> viewModel.deleteAllEntries()
        }
        return true

    }

//    private fun getDetails() {
//        val getSp = getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
//        et_id.setText(getSp.getInt("empID",1).toString())
//        et_firstName.setText(getSp.getString("firstName",""))
//        et_lastName.setText(getSp.getString("lastName",""))
//        et_city.setText(getSp.getString("city",""))
//
//    }

//    private fun findByName() {
//        val fName = et_search.text.toString()
//        if(TextUtils.isEmpty(fName)){
//            Toast.makeText(this,"Search field cannot be left empty",Toast.LENGTH_SHORT).show()
//        }
//        else {
//            //working code with coroutines
//            val fetched = runBlocking {viewModel.getByName(fName)}
//            if (fetched == null){
//               Toast.makeText(this,"Entry does not exist",Toast.LENGTH_SHORT).show()
//            }
//            else {
//                Log.i("Fetched", fetched.toString())
//            }
//        }
//    }




//    private fun setSharedPreferences(id:Int,fName:String,lName:String,city:String) {
//        val sp = getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
//        val editor = sp.edit()
//        editor.putInt("empID",id)
//        editor.putString("firstName",fName)
//        editor.putString("lastName",lName)
//        editor.putString("city",city)
//        editor.commit()
//
//    }


}
