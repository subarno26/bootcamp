package com.example.roomexample.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao

interface EmployeeDao{
    @Query("SELECT * FROM EmployeeTable")
    fun listAll():LiveData<List<Employee>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg employee: Employee)
    @Delete
    fun delete(employee: Employee)

    @Query("DELETE from EmployeeTable")
    fun deleteAll()

    @Query("SELECT * FROM EmployeeTable WHERE firstName like :firstName")
    fun getByName(firstName:String): Employee
}