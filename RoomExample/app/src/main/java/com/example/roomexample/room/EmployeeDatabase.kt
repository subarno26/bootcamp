package com.example.roomexample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Employee::class),version = 1)
abstract class EmployeeDatabase: RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    companion object {

        // For Singleton instantiation
        @Volatile private var instance: EmployeeDatabase? = null

        fun getInstance(context: Context): EmployeeDatabase {
            return instance
                ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): EmployeeDatabase {
            return Room.databaseBuilder(context, EmployeeDatabase::class.java, "EmployeeDatabase")
                .build()
        }
    }
}