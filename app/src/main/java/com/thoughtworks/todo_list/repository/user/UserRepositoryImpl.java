package com.thoughtworks.todo_list.repository.user;

import android.util.Log;

import com.thoughtworks.todo_list.repository.UserConstant;
import com.thoughtworks.todo_list.repository.user.entity.User;
import com.thoughtworks.todo_list.repository.utils.GSONParser;
import com.thoughtworks.todo_list.ui.login.LoginViewModel;
import com.thoughtworks.todo_list.ui.login.UserRepository;

import java.io.IOException;
import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserRepositoryImpl implements UserRepository {
    private UserDataSource dataSource;

    public UserRepositoryImpl(UserDataSource dataSource) {
        this.dataSource = dataSource;
        initUserInfoFromNetwork();
    }

    public Maybe<User> findByName(String name) {
        return dataSource.findByName(name);
    }

    @Override
    public Completable save(User user) {
        return dataSource.save(user);
    }

    @Override
    public void initUserInfoFromNetwork() {
        final String url = "https://twc-android-bootcamp.github.io/fake-data/data/user.json";
        //final String url = "http://www.baidu.com";
        final OkHttpClient client = new OkHttpClient();
        Observable.create((ObservableOnSubscribe<Boolean>) emitter -> emitter.onNext(true))
                .subscribeOn(Schedulers.io())
                .subscribe(t -> {
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String json = Objects.requireNonNull(response.body()).string();
                    User user = GSONParser.gsonParser.fromJson(json, User.class);
                    save(user).subscribeOn(Schedulers.io()).subscribe();
                });
    }
}