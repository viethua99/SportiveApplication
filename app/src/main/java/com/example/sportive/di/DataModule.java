package com.example.sportive.di;

import com.example.data.FieldBookingRepositoryImpl;
import com.example.data.SportFieldRepositoryImpl;
import com.example.domain.repository.FieldBookingRepository;
import com.example.domain.repository.SportFieldRepository;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 04/11/2020.
 */

@Module
public abstract class DataModule {
    @Binds
    public abstract SportFieldRepository bindSportFieldRepository(SportFieldRepositoryImpl impl);

    @Binds
    public abstract FieldBookingRepository bindFieldBookingRepository(FieldBookingRepositoryImpl impl);
}
