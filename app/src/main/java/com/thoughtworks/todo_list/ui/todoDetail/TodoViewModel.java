package com.thoughtworks.todo_list.ui.todoDetail;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.todo.entity.TodoList;
import com.thoughtworks.todo_list.ui.todolist.TodoRepository;

import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TodoViewModel extends ViewModel {
    private MutableLiveData<Todo> todo = new MutableLiveData<>();

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private TodoRepository todoRepository;
    private String id;

    void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    void observeTodo(LifecycleOwner lifecycleOwner, Observer<Todo> observer) {
        todo.observe(lifecycleOwner, observer);
    }

    public void initTodo() {
        Disposable d = todoRepository.queryTodoById(Integer.parseInt(id))
                .subscribeOn(Schedulers.io())
                .subscribe(t -> todo.postValue(t),
                    throwable -> Log.e("TodoListViewModel", Objects.requireNonNull(throwable.getMessage())));
        compositeDisposable.add(d);

    }

    public void onDestroy() {
        compositeDisposable.dispose();
    }

    public void setId(String id) {
        this.id = id;
    }
}
