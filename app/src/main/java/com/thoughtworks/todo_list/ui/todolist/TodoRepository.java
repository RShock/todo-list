package com.thoughtworks.todo_list.ui.todolist;

import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.todo.entity.TodoList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface TodoRepository {
    Maybe<List<Todo>> queryTodoList();

    Maybe<Todo> queryTodoById(int id);

    Completable save(Todo todo);
}
