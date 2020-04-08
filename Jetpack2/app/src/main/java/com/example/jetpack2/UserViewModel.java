package com.example.jetpack2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> allUsers;
    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public void insert(User user){
        userRepository.insert(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}
