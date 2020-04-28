package com.example.remote;

import com.example.data.entity.UserInfoEntity;
import com.example.data.mapper.UserInfoEntityMapper;
import com.example.data.repository.UserInfoDataRemote;
import com.example.remote.mapper.UserInfoModelMapper;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;

/**
 * Created by Viet Hua on 04/28/2020.
 */
public class UserInfoDataRemoteImpl implements UserInfoDataRemote {

    DatabaseReference databaseReference;

    @Inject
    public UserInfoDataRemoteImpl(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public Maybe<String> saveUserInfo(final UserInfoEntity userInfoEntity) {
        DatabaseReference reference = databaseReference.child(Constants.KEY_USERS).child(userInfoEntity.getUid());
        UserInfoModelMapper userInfoModelMapper = new UserInfoModelMapper();
        return RxFirebaseDatabase.setValue(reference, userInfoModelMapper.mapToModel(userInfoEntity))
                .andThen(new Maybe<String>() {
                    @Override
                    protected void subscribeActual(MaybeObserver<? super String> observer) {
                        observer.onSuccess(userInfoEntity.getUid());
                    }
                });
    }
}
