package com.example.remote;

import com.example.data.repository.AuthenticationDataRemote;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class AuthenticationDataRemoteImpl implements AuthenticationDataRemote {

    FirebaseAuth firebaseAuth;

    @Inject
    public AuthenticationDataRemoteImpl(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public Maybe<String> registerAccount(String email, String phoneNumber, String password) {
        return RxFirebaseAuth.createUserWithEmailAndPassword(firebaseAuth, email, password)
                .map(new Function<AuthResult, String>() {
                    @Override
                    public String apply(AuthResult authResult) throws Exception {
                        return authResult.toString();
                    }
                });
    }

    @Override
    public Maybe<String> loginWithEmailAndPassword(String email, String password) {
        return RxFirebaseAuth.signInWithEmailAndPassword(firebaseAuth, email, password)
                .map(new Function<AuthResult, String>() {
                    @Override
                    public String apply(AuthResult authResult) throws Exception {
                        return authResult.getUser().getUid();
                    }
                });
    }
}
