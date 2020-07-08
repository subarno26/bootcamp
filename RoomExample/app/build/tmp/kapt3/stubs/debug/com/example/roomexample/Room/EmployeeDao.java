package com.example.roomexample.Room;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\'J!\u0010\t\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\n\"\u00020\u0005H\'\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\rH\'\u00a8\u0006\u000f"}, d2 = {"Lcom/example/roomexample/Room/EmployeeDao;", "", "delete", "", "employee", "Lcom/example/roomexample/Room/Employee;", "getByName", "firstName", "", "insertAll", "", "([Lcom/example/roomexample/Room/Employee;)V", "listAll", "Landroidx/lifecycle/LiveData;", "", "app_debug"})
public abstract interface EmployeeDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Employee")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.roomexample.Room.Employee>> listAll();
    
    @androidx.room.Insert()
    public abstract void insertAll(@org.jetbrains.annotations.NotNull()
    com.example.roomexample.Room.Employee... employee);
    
    @androidx.room.Delete()
    public abstract void delete(@org.jetbrains.annotations.NotNull()
    com.example.roomexample.Room.Employee employee);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM EMPLOYEE WHERE firstName like :firstName")
    public abstract com.example.roomexample.Room.Employee getByName(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName);
}