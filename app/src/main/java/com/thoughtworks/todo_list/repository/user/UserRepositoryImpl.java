package com.thoughtworks.todo_list.repository.user;

import com.thoughtworks.todo_list.repository.user.entity.User;
import com.thoughtworks.todo_list.ui.login.UserRepository;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

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
        User user = new User();
        user.setId(1);
        user.setName("android");
        user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        save(user).subscribeOn(Schedulers.io()).subscribe();

        //有vpn无法使用，先mock了
//        final String url = "https://twc-android-bootcamp.github.io/fake-data/data/user.json";
//        final OkHttpClient client = new OkHttpClient();
//        Observable.create((ObservableOnSubscribe<Boolean>) emitter -> emitter.onNext(true))
//                .subscribeOn(Schedulers.io())
//                .subscribe(var -> {
//                    Request request = new Request.Builder().url(url).build();
//                    Response response = client.newCall(request).execute();
//                    String json = Objects.requireNonNull(response.body()).string();
//                    User user = GSONParser.gsonParser.fromJson(json, User.class);
//                    save(user).subscribeOn(Schedulers.io()).subscribe();
//                });
    }
}