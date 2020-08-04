package com.thoughtworks.todo_list.ui.todolist.model;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

public class Todo {
    public Boolean completed;
    public String title;
    public String desc;
    public Date deadline;

    public Todo(Boolean completed, String title, String desc, Date deadline) {
        this.completed = completed;
        this.title = title;
        this.desc = desc;
        this.deadline = deadline;
    }

    public String getDeadline() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm aa", Locale.CHINA);
        return dateFormat.format(deadline);
    }
}
