package com.thoughtworks.todo_list;

import android.app.Application;

import androidx.room.Room;

import com.thoughtworks.todo_list.repository.AppDatabase;
import com.thoughtworks.todo_list.repository.user.UserDataSource;
import com.thoughtworks.todo_list.ui.login.UserRepository;
import com.thoughtworks.todo_list.repository.user.UserRepositoryImpl;

public class MainApplication extends Application {
    private UserRepository userRepository;
    private AppDatabase appDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        userRepository = new UserRepositoryImpl(userDataSource());
    }

    public UserDataSource userDataSource() {
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, this.getClass().getSimpleName()).build();
        return appDatabase.userDBDataSource();
    }

    public UserRepository userRepository() {
        return userRepository;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        appDatabase.close();
    }
}
