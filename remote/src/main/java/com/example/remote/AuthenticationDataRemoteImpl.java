package com.example.remote;

import android.util.Log;

import com.example.data.entity.IsLoggedEntity;
import com.example.data.repository.AuthenticationDataRemote;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
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
    public Maybe<String> registerAccount(String email, String password) {
        return RxFirebaseAuth.createUserWithEmailAndPassword(firebaseAuth, email, password)
                .map(new Function<AuthResult, String>() {
                    @Override
                    public String apply(AuthResult authResult) throws Exception {
                        return authResult.getUser().getUid();
                    }
                });
    }

    @Override
    public Maybe<String> loginWithEmailAndPassword(final String email, String password) {
        return RxFirebaseAuth.signInWithEmailAndPassword(firebaseAuth, email, password)
                .map(new Function<AuthResult, String>() {
                    @Override
                    public String apply(AuthResult authResult) throws Exception {
                        Log.e("test",email);
                        LatestEmailPrefs.setLatestEmail(email);
                        return authResult.getUser().getUid();
                    }
                });
    }

    @Override
    public Maybe<IsLoggedEntity> checkLoggedIn() {
        return Maybe.create(new MaybeOnSubscribe<IsLoggedEntity>() {
            @Override
            public void subscribe(MaybeEmitter<IsLoggedEntity> emitter) throws Exception {
                boolean result = firebaseAuth != null && firebaseAuth.getCurrentUser() != null;
                String userId = result ? firebaseAuth.getCurrentUser().getUid() : "";
                IsLoggedEntity isLoggedEntity = new IsLoggedEntity(result, userId);
                emitter.onSuccess(isLoggedEntity);
            }
        });
    }

    @Override
    public Completable logout() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                firebaseAuth.signOut();
                emitter.onComplete();
            }
        });
    }

    @Override
    public Completable resetPasswordFromEmail(final String email) {
        return RxFirebaseAuth.sendPasswordResetEmail(firebaseAuth, email);
    }
}
