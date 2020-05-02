package com.example.sportive.di;

import com.example.data.AuthenticationRepositoryImpl;
import com.example.data.DistrictLocationRepositoryImpl;
import com.example.data.FieldBookingRepositoryImpl;
import com.example.data.SportFieldRepositoryImpl;
import com.example.data.UserInfoRepositoryImpl;
import com.example.domain.repository.AuthenticationRepository;
import com.example.domain.repository.DistrictLocationRepository;
import com.example.domain.repository.FieldBookingRepository;
import com.example.domain.repository.SportFieldRepository;
import com.example.domain.repository.UserInfoRepository;

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

    @Binds
    public abstract DistrictLocationRepository bindDistrictLocationRepository(DistrictLocationRepositoryImpl impl);

    @Binds
    public abstract AuthenticationRepository bindAuthenticationRepository(AuthenticationRepositoryImpl impl);

    @Binds
    public abstract UserInfoRepository bindUserInfoRepository(UserInfoRepositoryImpl impl);
}
