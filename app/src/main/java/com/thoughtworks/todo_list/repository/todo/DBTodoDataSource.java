package com.thoughtworks.todo_list.repository.todo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.todo.entity.TodoList;
import com.thoughtworks.todo_list.repository.user.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DBTodoDataSource extends TodoDataSource{

    @Query("SELECT * FROM todo")
    Maybe<List<Todo>> queryTodoList();

    @Insert(onConflict = REPLACE)
    Completable save(Todo todo);

}
