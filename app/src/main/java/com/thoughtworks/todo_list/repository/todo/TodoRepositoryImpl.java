package com.thoughtworks.todo_list.repository.todo;

import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.todo.entity.TodoList;
import com.thoughtworks.todo_list.repository.user.UserDataSource;
import com.thoughtworks.todo_list.ui.todolist.TodoRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public class TodoRepositoryImpl implements TodoRepository {
    TodoDataSource todoDataSource;

    public TodoRepositoryImpl(TodoDataSource dataSource) {
        this.todoDataSource = dataSource;
    }

    @Override
    public Maybe<List<Todo>> queryTodoList() {
        return todoDataSource.queryTodoList();
    }

    @Override
    public Maybe<Todo> queryTodoById(int id) {
        return todoDataSource.queryTodoById(id);
    }

    @Override
    public Completable save(Todo todo) {
        return todoDataSource.save(todo);
    }
}
