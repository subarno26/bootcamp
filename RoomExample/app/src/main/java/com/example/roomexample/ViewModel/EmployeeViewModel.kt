package com.example.roomexample.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomexample.Repository.Repository
import com.example.roomexample.room.Employee
import com.example.roomexample.room.EmployeeDatabase
import kotlinx.coroutines.Dispatchers
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

    //Getting deferred result from repo and conversion
    suspend fun getByName(firstName:String): Employee {
    return repository.findByName(firstName).await()
    }

    //Code still giving the main thread exception
//    suspend fun getByName(firstName:String): Employee {
//        return viewModelScope.async { repository.findByName(firstName) }.await()
//    }

   fun deleteEntry(employee: Employee) = viewModelScope.launch(Dispatchers.IO) {
       repository.deleteEntry(employee)
   }

    fun deleteAllEntries() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }



}