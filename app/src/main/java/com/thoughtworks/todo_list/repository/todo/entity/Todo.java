package com.thoughtworks.todo_list.repository.todo.entity;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Locale;

@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private Boolean completed;
    private String title;
    private String desc;
    private Boolean notice;
    public Date deadline;

    public Todo(Boolean completed, String title, String desc, Date deadline, Boolean notice) {
        this.completed = completed;
        this.title = title;
        this.desc = desc;
        this.deadline = deadline;
        this.notice = notice;
    }

    public Todo() {
    }

    public String getDeadline() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm aa", Locale.CHINA);
        return dateFormat.format(deadline);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean getNotice() {
        return notice;
    }

    public void setNotice(Boolean notice) {
        this.notice = notice;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDeadline(Date deadline) { return deadline; }
}
