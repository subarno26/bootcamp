package com.example.roomexample.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomexample.Repository.Repository
import com.example.roomexample.Room.Employee
import com.example.roomexample.Room.EmployeeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : Repository
    val employeeList : LiveData<List<Employee>>
    init {
        val employeeDao = EmployeeDatabase.getInstance(application).employeeDao()
        repository = Repository(employeeDao)
        employeeList = repository.employeeList
    }

    fun insert(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(employee)
        }
    }

    fun getByName(firstName:String) : LiveData<Employee>{
        return repository.findByName(firstName)
    }

//    The coroutine code which did not work, because we needed to return a value
//    fun getByName(firstName:String) = viewModelScope.launch(Dispatchers.IO){
//        repository.findByName(firstName)
//    }


}