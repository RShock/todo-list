package com.thoughtworks.todo_list.ui.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.todo_list.R;
import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.todo.entity.TodoList;

import java.util.List;

public class TodolistAdapter extends RecyclerView.Adapter<TodolistAdapter.TodoItemHolder> {

    private TodoList mList;//数据源

    TodolistAdapter(TodoList list) {
        mList = list;
    }

    @Override
    public TodoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TodoItemHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoItemHolder holder, int position) {
        Todo todo = mList.get().get(position);
        holder.completed.setChecked(todo.getCompleted());
        holder.title.setText(todo.getTitle());
        holder.time.setText(todo.getDeadline());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class TodoItemHolder extends RecyclerView.ViewHolder {

        CheckBox completed;
        TextView title;
        TextView time;

        public TodoItemHolder(View itemView) {
            super(itemView);
            completed = itemView.findViewById(R.id.completed);
            title = itemView.findViewById(R.id.taskTitle);
            time = itemView.findViewById(R.id.time);
        }
    }
}