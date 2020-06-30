package com.example.sportive.presentation.edituser;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 06/30/2020.
 */

@Module
public abstract class EditUserModule {
    @Binds
    public abstract EditUserContract.Presenter editUserPresenter(EditUserPresenterImpl impl);
}
