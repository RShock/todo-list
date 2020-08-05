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
    private String content;
    private Boolean notice;
    public Date deadline;

    public Todo(Boolean completed, String title, String content, Date deadline, Boolean notice) {
        this.completed = completed;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public Boolean getNotice() {
        return notice;
    }

    public void setNotice(Boolean notice) {
        this.notice = notice;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDeadline(Date deadline) { return deadline; }
}
