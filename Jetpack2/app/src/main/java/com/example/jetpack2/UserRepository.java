package com.example.jetpack2;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application){
        UserDatabase userDatabase = UserDatabase.getInstance(application);
        userDao =  userDatabase.userDao();
        allUsers = userDao.getAllUsers();
    }

    public void insert(User user){
        new InsertAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>>getAllUsers(){
        return allUsers;
    }

    private static class InsertAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}
