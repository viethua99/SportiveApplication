package com.example.sportive.di;

import com.example.data.repository.AuthenticationDataRemote;
import com.example.data.repository.DistrictLocationDataRemote;
import com.example.data.repository.FieldBookingDataRemote;
import com.example.data.repository.MiniFieldDataRemote;
import com.example.data.repository.SportFieldDataRemote;
import com.example.data.repository.UserInfoDataRemote;
import com.example.remote.AuthenticationDataRemoteImpl;
import com.example.remote.DistrictLocationDataRemoteImpl;
import com.example.remote.FieldBookingDataRemoteImpl;
import com.example.remote.MiniFieldDataRemoteImpl;
import com.example.remote.SportFieldDataRemoteImpl;
import com.example.remote.UserInfoDataRemoteImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 04/11/2020.
 */

@Module
public abstract class RemoteModule {
    @Binds
    public abstract SportFieldDataRemote bindSportFieldDataRemote(SportFieldDataRemoteImpl impl);

    @Binds
    public abstract FieldBookingDataRemote bindFieldBookingDataRemote(FieldBookingDataRemoteImpl impl);

    @Binds
    public abstract DistrictLocationDataRemote bindDistrictLocationDataRemote(DistrictLocationDataRemoteImpl impl);

    @Binds
    public abstract AuthenticationDataRemote bindAuthenticationDataRemote(AuthenticationDataRemoteImpl impl);

    @Binds
    public abstract UserInfoDataRemote bindUserInfoDataRemote(UserInfoDataRemoteImpl impl);

    @Binds
    public abstract MiniFieldDataRemote bindMiniFieldDataRemote(MiniFieldDataRemoteImpl impl);
}
