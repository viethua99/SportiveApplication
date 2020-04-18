package com.example.sportive.presentation.location;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 4/10/2020
 */
@Module
public abstract class LocationModule {
    @Binds
    public abstract LocationContract.Presenter locationPresenter(LocationPresenterImpl impl);

}
