package com.example.roomexample.Room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Employee(
    @PrimaryKey
    val eId: Int,
    val firstName :String?,
    val lastName : String?,
    val city : String?
)