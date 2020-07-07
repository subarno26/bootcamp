package com.example.roomexample.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.roomexample.Room.Employee

@Dao

interface EmployeeDao{
    @Query("SELECT * FROM Employee")
    fun listAll():LiveData<List<Employee>>
    @Insert
    fun insertAll(vararg employee: Employee)
    @Delete
    fun delete(employee: Employee)

    @Query("SELECT * FROM EMPLOYEE WHERE firstName like :firstName")
    fun getByName(firstName:String): LiveData<Employee>
}