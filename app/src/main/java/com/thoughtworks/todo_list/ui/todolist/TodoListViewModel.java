package com.thoughtworks.todo_list.ui.todolist;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.todo.entity.TodoList;

public class TodoListViewModel extends ViewModel {
    private MutableLiveData<TodoList> todoList = new MutableLiveData<>();

    private TodoRepository todoRepository;

    void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    void observeTodoList(LifecycleOwner lifecycleOwner, Observer<TodoList> observer) {
        todoList.observe(lifecycleOwner, observer);
    }

    void addTodo(Todo todo) {
        todoRepository.save(todo);
    }
}
