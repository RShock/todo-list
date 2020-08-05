package com.thoughtworks.todo_list.ui.todoDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import com.thoughtworks.todo_list.MainApplication;
import com.thoughtworks.todo_list.R;
import com.thoughtworks.todo_list.ui.todolist.TodoRepository;

import java.util.Objects;

public class TodoActivity extends AppCompatActivity {

    TodoViewModel todoViewModel;
    CheckBox completed;
    Switch notice;
    TextView title;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        addToolBar();
        todoViewModel= obtainViewModel();
        completed = findViewById(R.id.checkbox);
        notice = findViewById(R.id.notice);
        title = findViewById(R.id.todoTitle);
        content = findViewById(R.id.content);
        todoViewModel.observeTodo(this, todo -> {
            if (todo == null) {
                return;
            }
            completed.setChecked(todo.getCompleted());
            title.setText(todo.getTitle());
            content.setText(todo.getContent());

        });
    }

    private TodoViewModel obtainViewModel() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        TodoRepository todoRepository = (((MainApplication) getApplicationContext())).todoRepository();
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoViewModel.setTodoRepository(todoRepository);
        todoViewModel.setId(id);
        todoViewModel.initTodo();
        return todoViewModel;
    }

    private void addToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        if(upArrow != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                upArrow.setColorFilter(new BlendModeColorFilter(0, BlendMode.SRC_ATOP));
            } else {
                upArrow.setColorFilter(0, PorterDuff.Mode.SRC_ATOP);
            }
            if(getSupportActionBar() != null) {
                getSupportActionBar().setHomeAsUpIndicator(upArrow);
            }
        }
    }
}