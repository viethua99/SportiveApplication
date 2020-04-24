package com.example.sportive.presentation.map;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 04/19/2020.
 */

@Module
public abstract class MapModule {
    @Binds
    public abstract MapContract.Presenter presenter(MapPresenterImpl impl);
}
