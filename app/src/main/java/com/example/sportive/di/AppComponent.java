package com.example.sportive.di;

import com.example.sportive.myapp.MyApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created By Viet Hua on 4/7/2020
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppBindingModule.class
        , AppModule.class, DataModule.class, RemoteModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    void inject(MyApp myApp);

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(MyApp myApp);
    }
}
