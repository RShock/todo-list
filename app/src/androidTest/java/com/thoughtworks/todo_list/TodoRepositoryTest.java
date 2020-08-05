package com.thoughtworks.todo_list;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.thoughtworks.todo_list.repository.AppDatabase;
import com.thoughtworks.todo_list.repository.todo.TodoRepositoryImpl;
import com.thoughtworks.todo_list.repository.todo.entity.Todo;
import com.thoughtworks.todo_list.repository.user.UserRepositoryImpl;
import com.thoughtworks.todo_list.repository.user.entity.User;
import com.thoughtworks.todo_list.ui.login.UserRepository;
import com.thoughtworks.todo_list.ui.todolist.TodoRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.schedulers.Schedulers;


@RunWith(AndroidJUnit4.class)
public class TodoRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDatabase appDatabase;

    private TodoRepository todoRepository;

    @Before
    public void setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                AppDatabase.class).build();
        todoRepository = new TodoRepositoryImpl(appDatabase.todoDBDataSource());
    }

    @After
    public void tearDown() {
        appDatabase.close();
    }

    @Test
    public void should_find_correct_user() {
        Todo savedTodo = new Todo();
        savedTodo.setCompleted(true);
        savedTodo.setDesc("description");
        savedTodo.setId(1);
        appDatabase.todoDBDataSource().save(savedTodo).subscribeOn(Schedulers.io()).subscribe();
        todoRepository.queryTodoList().test()
                .assertValue(todoList -> todoList.size() != 0);
    }
}