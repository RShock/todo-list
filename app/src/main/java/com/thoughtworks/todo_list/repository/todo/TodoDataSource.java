package com.thoughtworks.todo_list.repository.todo;

import com.thoughtworks.todo_list.repository.todo.entity.Todo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface TodoDataSource {
    Maybe<List<Todo>> queryTodoList();

    Completable save(Todo todo);
}
