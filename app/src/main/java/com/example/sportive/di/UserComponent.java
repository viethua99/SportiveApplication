package com.example.sportive.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Viet Hua on 04/28/2020.
 */
@Subcomponent(modules = AndroidInjectionModule.class)
public interface UserComponent extends AndroidInjector<DaggerApplication> {
    void inject(SportiveManager sportiveManager);

    @Subcomponent.Builder
    interface Builder {
        UserComponent build();
    }
}
