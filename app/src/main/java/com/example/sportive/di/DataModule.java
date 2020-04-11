package com.example.sportive.di;

import com.example.data.SportFieldRepositoryImpl;
import com.example.domain.repository.SportFieldRepository;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 04/11/2020.
 */

@Module
public abstract class DataModule {
    @Binds
    public abstract SportFieldRepository bindSportFieldRepository(SportFieldRepositoryImpl sportFieldRepository);
}
