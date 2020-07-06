package com.example.roomexample.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomexample.Repository.Repository
import com.example.roomexample.Room.Employee
import com.example.roomexample.Room.EmployeeDatabase

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : Repository
    val employeeList : LiveData<List<Employee>>
    init {
        val employeeDao = EmployeeDatabase.getInstance(application).employeeDao()
        repository = Repository(employeeDao)
        employeeList = repository.employeeList
    }

    fun insert(employee: Employee){
        return repository.insert(employee)
    }
    fun getByName(firstName:String): Employee {
        return repository.findByName(firstName)
    }
}