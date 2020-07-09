package com.example.roomexample.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "EmployeeTable")
data class Employee(
    @PrimaryKey
    val eId: Int,
    val firstName :String?,
    val lastName : String?,
    val city : String?
)