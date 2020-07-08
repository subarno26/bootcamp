package com.example.roomexample.Repository

import androidx.lifecycle.LiveData
import com.example.roomexample.Room.Employee
import com.example.roomexample.Room.EmployeeDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Repository(private val employeeDao: EmployeeDao) {
    val employeeList:LiveData<List<Employee>> = employeeDao.listAll()
    fun insert(employee: Employee) = employeeDao.insertAll(employee)

    fun findByName(firstName:String): Deferred<Employee> {
        return GlobalScope.async { employeeDao.getByName(firstName)}
    }
    //fun findByName(firstName:String) :Employee = employeeDao.getByName(firstName)

    fun deleteEntry(employee: Employee) = employeeDao.delete(employee)
}