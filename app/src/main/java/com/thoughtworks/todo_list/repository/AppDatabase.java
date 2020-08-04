package com.thoughtworks.todo_list.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.thoughtworks.todo_list.repository.todo.DBTodoDataSource;
import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.user.entity.User;
import com.thoughtworks.todo_list.repository.user.DBUserDataSource;
import com.thoughtworks.todo_list.repository.utils.Converter;

@Database(entities = {User.class, Todo.class}, version = 3)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract DBUserDataSource userDBDataSource();
    public abstract DBTodoDataSource todoDBDataSource();
}