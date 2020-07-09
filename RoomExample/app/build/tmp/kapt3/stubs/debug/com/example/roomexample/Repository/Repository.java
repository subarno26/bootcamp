package com.example.roomexample.Repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\bJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/example/roomexample/Repository/Repository;", "", "employeeDao", "Lcom/example/roomexample/room/EmployeeDao;", "(Lcom/example/roomexample/room/EmployeeDao;)V", "employeeList", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/roomexample/room/Employee;", "getEmployeeList", "()Landroidx/lifecycle/LiveData;", "deleteAll", "", "deleteEntry", "employee", "findByName", "Lkotlinx/coroutines/Deferred;", "firstName", "", "insert", "app_debug"})
public final class Repository {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.roomexample.room.Employee>> employeeList = null;
    private final com.example.roomexample.room.EmployeeDao employeeDao = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.roomexample.room.Employee>> getEmployeeList() {
        return null;
    }
    
    public final void insert(@org.jetbrains.annotations.NotNull()
    com.example.roomexample.room.Employee employee) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Deferred<com.example.roomexample.room.Employee> findByName(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName) {
        return null;
    }
    
    public final void deleteEntry(@org.jetbrains.annotations.NotNull()
    com.example.roomexample.room.Employee employee) {
    }
    
    public final void deleteAll() {
    }
    
    public Repository(@org.jetbrains.annotations.NotNull()
    com.example.roomexample.room.EmployeeDao employeeDao) {
        super();
    }
}