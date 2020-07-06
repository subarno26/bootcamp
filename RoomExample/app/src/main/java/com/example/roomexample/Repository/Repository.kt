package com.example.roomexample.Repository

import androidx.lifecycle.LiveData
import com.example.roomexample.Room.Employee
import com.example.roomexample.Room.EmployeeDao

class Repository(private val employeeDao: EmployeeDao) {
    val employeeList:LiveData<List<Employee>> = employeeDao.listAll()
    fun insert(employee: Employee) = employeeDao.insertAll(employee)
    fun findByName(firstName:String) = employeeDao.getByName(firstName)
}