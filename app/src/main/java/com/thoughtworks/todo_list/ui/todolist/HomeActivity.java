package com.thoughtworks.todo_list.ui.todolist;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thoughtworks.todo_list.MainApplication;
import com.thoughtworks.todo_list.R;
import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.todo.entity.TodoList;
import com.thoughtworks.todo_list.ui.login.LoginViewModel;
import com.thoughtworks.todo_list.ui.login.UserRepository;
import com.thoughtworks.todo_list.ui.todoDetail.TodoActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    TextView todoCount;

    private TodoListViewModel todoListViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.todoList);
        floatingActionButton = findViewById(R.id.addTodo);
        todoCount = findViewById(R.id.todoCount);

        todoListViewModel = obtainViewModel();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        TodolistAdapter todolistAdapter = new TodolistAdapter(new TodoList());
        recyclerView.setAdapter(todolistAdapter);
        todolistAdapter.setOnItemClick((v, pos, id) -> {
            Intent intent = new Intent(HomeActivity.this, TodoActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

        addToolBar();
        addDivider();

        todoListViewModel.observeTodoList(this, todoList -> {
            if (todoList == null) {
                return;
            }
            todolistAdapter.setList(todoList);
            // 任何变动都应该刷新待办列表
            todolistAdapter.notifyDataSetChanged();
            //TODO: I18n
            todoCount.setText(String.format("%s%s",todoList.size(),"个任务"));
        });

        todoListViewModel.initTodoList();

        floatingActionButton.setOnClickListener(view -> {
            todoListViewModel.addTodo(new Todo(false,"1","title",Calendar.getInstance().getTime(),true));
            todoListViewModel.addTodo(new Todo(false,"2","title2",Calendar.getInstance().getTime(),true));
            // Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
        });
    }

    private void addToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    private void addDivider() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public TodoListViewModel obtainViewModel() {
        TodoRepository todoRepository = (((MainApplication) getApplicationContext())).todoRepository();
        todoListViewModel = new ViewModelProvider(this).get(TodoListViewModel.class);
        todoListViewModel.setTodoRepository(todoRepository);
        return todoListViewModel;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        todoListViewModel.onDestroy();
    }
}