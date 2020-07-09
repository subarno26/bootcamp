package com.example.roomexample.View

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.R
import com.example.roomexample.ViewModel.EmployeeViewModel
import com.example.roomexample.databinding.FragAddEntryBinding
import com.example.roomexample.room.Employee

class AddEntryFragment : Fragment() {
    private lateinit var viewModel:EmployeeViewModel
    private lateinit var binding: FragAddEntryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.frag_add_entry,container,false)
        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        binding.btnSave.setOnClickListener {
            saveEntries()
        }
        return binding.root
    }


    private fun saveEntries() {

        val eID = binding.etId.text.toString().toInt()
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val city = binding.etCity.text.toString()



        if (TextUtils.isEmpty(eID.toString()) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(city)){
            Toast.makeText(context,"Please fill in all the fields.", Toast.LENGTH_SHORT).show()
        }
        else {
//            if (checkBox.isChecked){
//                setSharedPreferences(eID,firstName,lastName,city)
//            }

            val employeeDetails = Employee(
                eID,
                firstName,
                lastName,
                city
            )
            viewModel.insert(employeeDetails)
            Toast.makeText(context,"Data save successful.", Toast.LENGTH_SHORT).show()
            activity!!.supportFragmentManager.popBackStack()
        }
    }
}