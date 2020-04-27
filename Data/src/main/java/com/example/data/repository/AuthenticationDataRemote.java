package com.example.data.repository;


import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public interface AuthenticationDataRemote {
    Maybe<String> registerAccount(String email, String phoneNumber, String password);
}
