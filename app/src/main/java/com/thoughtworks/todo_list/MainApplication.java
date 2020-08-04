package com.thoughtworks.todo_list;

import android.app.Application;

import androidx.room.Room;

import com.thoughtworks.todo_list.repository.AppDatabase;
import com.thoughtworks.todo_list.repository.todo.TodoDataSource;
import com.thoughtworks.todo_list.repository.todo.TodoRepositoryImpl;
import com.thoughtworks.todo_list.repository.user.UserDataSource;
import com.thoughtworks.todo_list.ui.login.UserRepository;
import com.thoughtworks.todo_list.repository.user.UserRepositoryImpl;
import com.thoughtworks.todo_list.ui.todolist.TodoRepository;

public class MainApplication extends Application {
    private UserRepository userRepository;
    private TodoRepository todoRepository;
    private AppDatabase appDatabase;


    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, this.getClass().getSimpleName())
                .fallbackToDestructiveMigration()//数据库更新时删除数据重新创建
                .build();
        userRepository = new UserRepositoryImpl(userDataSource());
        todoRepository = new TodoRepositoryImpl(todoDataSource());
    }

    public UserDataSource userDataSource() {
        return appDatabase.userDBDataSource();
    }

    public TodoDataSource todoDataSource() {
        return appDatabase.todoDBDataSource();
    }

    public UserRepository userRepository() {
        return userRepository;
    }

    public TodoRepository todoRepository() {
        return todoRepository;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        appDatabase.close();
    }
}
