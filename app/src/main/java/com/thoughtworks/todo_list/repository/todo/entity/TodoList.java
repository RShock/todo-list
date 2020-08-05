package com.thoughtworks.todo_list.repository.todo.entity;

import com.thoughtworks.todo_list.repository.todo.entity.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoList {
    public List<Todo> todoList;

    public TodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public TodoList() {
    }

    public int size() {
        if (todoList == null) return 0;
        return todoList.size();
    }

    public List<Todo> get(){
        if( todoList == null) return new ArrayList<>();
        return todoList;
    }
}
