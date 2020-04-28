package com.example.sportive.di;

import android.content.Context;

import com.example.domain.executor.ExecutionThread;
import com.example.sportive.myapp.MyApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created By Viet Hua on 4/7/2020
 */
@Module(subcomponents = UserComponent.class)
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

    @Provides
    @Singleton
    public DatabaseReference provideDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    @Singleton
    public FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

}
