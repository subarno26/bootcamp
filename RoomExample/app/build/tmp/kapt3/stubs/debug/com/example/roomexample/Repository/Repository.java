package com.example.roomexample.Repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0011"}, d2 = {"Lcom/example/roomexample/Repository/Repository;", "", "employeeDao", "Lcom/example/roomexample/Room/EmployeeDao;", "(Lcom/example/roomexample/Room/EmployeeDao;)V", "employeeList", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/roomexample/Room/Employee;", "getEmployeeList", "()Landroidx/lifecycle/LiveData;", "findByName", "firstName", "", "insert", "", "employee", "app_debug"})
public final class Repository {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.roomexample.Room.Employee>> employeeList = null;
    private final com.example.roomexample.Room.EmployeeDao employeeDao = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.roomexample.Room.Employee>> getEmployeeList() {
        return null;
    }
    
    public final void insert(@org.jetbrains.annotations.NotNull()
    com.example.roomexample.Room.Employee employee) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.roomexample.Room.Employee> findByName(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName) {
        return null;
    }
    
    public Repository(@org.jetbrains.annotations.NotNull()
    com.example.roomexample.Room.EmployeeDao employeeDao) {
        super();
    }
}