package com.example.roomexample.ViewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/roomexample/ViewModel/EmployeeViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "employeeList", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/roomexample/Room/Employee;", "getEmployeeList", "()Landroidx/lifecycle/LiveData;", "repository", "Lcom/example/roomexample/Repository/Repository;", "getByName", "firstName", "", "insert", "", "employee", "app_debug"})
public final class EmployeeViewModel extends androidx.lifecycle.AndroidViewModel {
    private final com.example.roomexample.Repository.Repository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.roomexample.Room.Employee>> employeeList = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.roomexample.Room.Employee>> getEmployeeList() {
        return null;
    }
    
    public final void insert(@org.jetbrains.annotations.NotNull()
    com.example.roomexample.Room.Employee employee) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.roomexample.Room.Employee> getByName(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName) {
        return null;
    }
    
    public EmployeeViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
}