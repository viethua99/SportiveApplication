package com.example.sportive.presentation.resetpassword;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 05/04/2020.
 */

@Module
public abstract class ResetPasswordModule {
    @Binds
    public abstract ResetPasswordContract.Presenter bindResetPasswordPresenter(ResetPasswordPresenterImpl impl);
}
