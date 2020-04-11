package com.example.sportive.di;

import android.content.Context;

import com.example.domain.executor.ExecutionThread;
import com.example.sportive.myapp.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created By Viet Hua on 4/7/2020
 */
@Module
public class AppModule {
    @Provides
    @Singleton
    public Context provideContext(MyApp myApp) {
        return myApp;
    }


    @Provides
    @Singleton
    public ExecutionThread provideExecutionThread() {
        return new ExecutionThreadImpl();
    }
}
