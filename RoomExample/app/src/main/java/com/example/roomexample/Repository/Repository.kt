package com.example.roomexample.Repository

import androidx.lifecycle.LiveData
import com.example.roomexample.room.Employee
import com.example.roomexample.room.EmployeeDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Repository(private val employeeDao: EmployeeDao) {
    val employeeList:LiveData<List<Employee>> = employeeDao.listAll()
    fun insert(employee: Employee) = employeeDao.insertAll(employee)

    fun findByName(firstName:String): Deferred<Employee> {
        return GlobalScope.async { employeeDao.getByName(firstName)}
    }

    fun deleteEntry(employee: Employee) = employeeDao.delete(employee)
    fun deleteAll() = employeeDao.deleteAll()

    //fun findByName(firstName:String) :Employee = employeeDao.getByName(firstName)


}