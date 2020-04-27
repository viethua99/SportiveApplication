package com.example.data;

import com.example.data.repository.AuthenticationDataRemote;
import com.example.domain.repository.AuthenticationRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class AuthenticationRepositoryImpl implements AuthenticationRepository {
    AuthenticationDataRemote authenticationDataRemote;

    @Inject
    public AuthenticationRepositoryImpl(AuthenticationDataRemote authenticationDataRemote) {
        this.authenticationDataRemote = authenticationDataRemote;
    }

    @Override
    public Maybe<String> registerAccount(String email, String phoneNumber, String password) {
        return authenticationDataRemote.registerAccount(email, phoneNumber, password);
    }
}
