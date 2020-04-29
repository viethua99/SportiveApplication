package com.example.data.repository;


import com.example.data.entity.IsLoggedEntity;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public interface AuthenticationDataRemote {
    Maybe<String> registerAccount(String email, String password);
    Maybe<String> loginWithEmailAndPassword(String email,String password);
    Maybe<IsLoggedEntity> checkLoggedIn();
    Completable logout();
}
