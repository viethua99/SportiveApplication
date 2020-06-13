package com.example.sportive.presentation.result;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 4/10/2020
 */
@Module
public abstract class ResultModule {
    @Binds
    public abstract ResultContract.Presenter resultPresenter(ResultPresenterImpl impl);
}
