package com.thoughtworks.todo_list.ui.todolist;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.todo_list.R;
import com.thoughtworks.todo_list.ui.todolist.model.Todo;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;
import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.todoList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new TodolistAdapter(getMockTodo()));

        addToolBar();
        addDivider();
    }

    private List<Todo> getMockTodo() {
        Todo todo1 = new Todo(true, "todo1", "azaa", Calendar.getInstance().getTime());
        Todo todo2 = new Todo(true, "todo2", "BBBB", Calendar.getInstance().getTime());
        Todo todo3 = new Todo(true, "todo3", "BBBB", Calendar.getInstance().getTime());
        Todo todo4 = new Todo(true, "todo4", "BBBB", Calendar.getInstance().getTime());
        Todo todo5 = new Todo(true, "todo5", "BBBB", Calendar.getInstance().getTime());
        return Arrays.asList(todo1,todo2,todo3,todo4,todo5);
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
}