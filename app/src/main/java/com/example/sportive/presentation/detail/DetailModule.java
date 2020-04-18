package com.example.sportive.presentation.detail;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 4/10/2020
 */
@Module
public abstract class DetailModule {
    @Binds
    public abstract DetailContract.Presenter detailPresenter(DetailPresenterImpl impl);
}
