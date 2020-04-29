package com.example.data;

import com.example.data.entity.IsLoggedEntity;
import com.example.data.mapper.IsLoggedEntityMapper;
import com.example.data.repository.AuthenticationDataRemote;
import com.example.domain.model.IsLogged;
import com.example.domain.repository.AuthenticationRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class AuthenticationRepositoryImpl implements AuthenticationRepository {
    AuthenticationDataRemote authenticationDataRemote;
    IsLoggedEntityMapper isLoggedEntityMapper;

    @Inject
    public AuthenticationRepositoryImpl(AuthenticationDataRemote authenticationDataRemote) {
        this.authenticationDataRemote = authenticationDataRemote;
        isLoggedEntityMapper = new IsLoggedEntityMapper();
    }

    @Override
    public Maybe<String> registerAccount(String email, String password) {
        return authenticationDataRemote.registerAccount(email, password);
    }

    @Override
    public Maybe<String> loginWithEmailAndPassword(String email, String password) {
        return authenticationDataRemote.loginWithEmailAndPassword(email, password);
    }

    @Override
    public Maybe<IsLogged> checkLoggedIn() {
        return authenticationDataRemote.checkLoggedIn().map(new Function<IsLoggedEntity, IsLogged>() {
            @Override
            public IsLogged apply(IsLoggedEntity isLoggedEntity) throws Exception {
                return isLoggedEntityMapper.mapFromEntity(isLoggedEntity);
            }
        });
    }
    @Override
    public Completable logout() {
        return authenticationDataRemote.logout();
    }
}
