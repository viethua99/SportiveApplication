package com.example.sportive.presentation.login;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 04/27/2020.
 */

@Module
public abstract class LoginModule {
    @Binds
    public abstract LoginContract.Presenter bindLoginPresenter(LoginPresenterImpl loginPresenter);
}
