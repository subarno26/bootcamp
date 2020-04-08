package com.example.jetpack2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public void insert(User user);

    @Query("SELECT * from user_table")
    LiveData<List<User>>getAllUsers();
}
