package com.thoughtworks.todo_list.repository.todo.entity;

import com.thoughtworks.todo_list.repository.todo.entity.Todo;

import java.util.List;

public class TodoList {
    public List<Todo> todoList;

    public TodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
}
