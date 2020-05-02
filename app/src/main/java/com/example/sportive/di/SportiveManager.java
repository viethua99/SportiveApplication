package com.example.sportive.di;

import com.example.domain.model.UserInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * Created by Viet Hua on 04/28/2020.
 */

@Singleton
public class SportiveManager implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    private UserComponent.Builder builder;
    private UserComponent appComponent;
    private volatile UserInfo userInfo;

    @Inject
    public SportiveManager(UserComponent.Builder builder) {
        this.builder = builder;
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    public void createUserSession() {
        appComponent = builder.build();
        appComponent.inject(this);
    }

    public synchronized UserInfo getUserInfo() {
        return userInfo;
    }

    public synchronized void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
