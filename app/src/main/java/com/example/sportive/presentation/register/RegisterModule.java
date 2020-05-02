package com.example.sportive.presentation.register;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 04/27/2020.
 */

@Module
public abstract class RegisterModule {
    @Binds
    public abstract RegisterContract.Presenter bindRegisterPresenter(RegisterPresenterImpl registerPresenter);
}
