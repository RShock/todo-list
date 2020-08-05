package com.thoughtworks.todo_list.ui.todolist;

import android.icu.util.Calendar;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.todo.entity.TodoList;

import java.util.Arrays;
import java.util.List;

public class TodoListViewModel extends ViewModel {
    private MutableLiveData<TodoList> todoList = new MutableLiveData<>();

    private TodoRepository todoRepository;

    void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    void observeTodoList(LifecycleOwner lifecycleOwner, Observer<TodoList> observer) {
        todoList.observe(lifecycleOwner, observer);
    }

    void initTodoList() {
        TodoList tmpTodoList = new TodoList(getMockTodo());
        todoList.postValue(tmpTodoList);
    }

    void addTodo(Todo todo) {
        todoRepository.save(todo);
    }

    private List<Todo> getMockTodo() {
        Todo todo1 = new Todo(true, "todo1", "azaa", Calendar.getInstance().getTime());
        Todo todo2 = new Todo(true, "todo2", "BBBB", Calendar.getInstance().getTime());
        Todo todo3 = new Todo(true, "todo3", "BBBB", Calendar.getInstance().getTime());
        Todo todo4 = new Todo(true, "todo4", "BBBB", Calendar.getInstance().getTime());
        Todo todo5 = new Todo(true, "todo5", "BBBB", Calendar.getInstance().getTime());
        return Arrays.asList(todo1,todo2,todo3,todo4,todo5);
    }
}
