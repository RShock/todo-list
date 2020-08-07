package com.thoughtworks.todo_list.ui.todolist;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.kelin.mvvmlight.messenger.Messenger;
import com.thoughtworks.todo_list.common.Constant;
import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.todo.entity.TodoList;

import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TodoListViewModel extends ViewModel {
    private MutableLiveData<TodoList> todoList = new MutableLiveData<>();

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private TodoRepository todoRepository;

    void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    void observeTodoList(LifecycleOwner lifecycleOwner, Observer<TodoList> observer) {
        todoList.observe(lifecycleOwner, observer);
    }

    void addTodo(Todo todo) {
        Disposable d = todoRepository.save(todo).subscribeOn(Schedulers.io()).subscribe(this::updateTodoList);
        compositeDisposable.add(d);
    }

    public void updateTodoList() {
        Disposable d = todoRepository.queryTodoList()
                .subscribeOn(Schedulers.io())
                .subscribe(list -> {
                    todoList.postValue(new TodoList(list));
                    },
                    throwable -> Log.e("TodoListViewModel", Objects.requireNonNull(throwable.getMessage())));
        compositeDisposable.add(d);

    }

    public void init() {
        Messenger.getDefault().register(this, Constant.MessengerToken.UPDATE_TODO_LIST,
                this::updateTodoList);
    }

    public void onDestroy() {
        compositeDisposable.dispose();
    }
}
