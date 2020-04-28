package com.example.domain.repository;

import com.example.domain.model.EmptyParam;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public interface AuthenticationRepository {
    Maybe<String> registerAccount(String email, String password);
    Maybe<String> loginWithEmailAndPassword(String email,String password);
}
