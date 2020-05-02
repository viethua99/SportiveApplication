package com.example.remote;

import com.example.data.entity.UserInfoEntity;
import com.example.data.mapper.UserInfoEntityMapper;
import com.example.data.repository.UserInfoDataRemote;
import com.example.remote.mapper.UserInfoModelMapper;
import com.example.remote.model.UserInfoModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 04/28/2020.
 */
public class UserInfoDataRemoteImpl implements UserInfoDataRemote {

    DatabaseReference databaseReference;
    UserInfoModelMapper userInfoModelMapper;

    @Inject
    public UserInfoDataRemoteImpl(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
        userInfoModelMapper = new UserInfoModelMapper();
    }

    @Override
    public Maybe<String> saveUserInfo(final UserInfoEntity userInfoEntity) {
        DatabaseReference reference = databaseReference.child(Constants.KEY_USERS).child(userInfoEntity.getUid());
        return RxFirebaseDatabase.setValue(reference, userInfoModelMapper.mapToModel(userInfoEntity))
                .andThen(new Maybe<String>() {
                    @Override
                    protected void subscribeActual(MaybeObserver<? super String> observer) {
                        observer.onSuccess(userInfoEntity.getUid());
                    }
                });
    }

    @Override
    public Maybe<UserInfoEntity> getUserInfoById(final String userId) {
        Query query = databaseReference.child(Constants.KEY_USERS).child(userId);
        return RxFirebaseDatabase.observeSingleValueEvent(query, new Function<DataSnapshot, UserInfoEntity>() {
            @Override
            public UserInfoEntity apply(DataSnapshot dataSnapshot) throws Exception {
                UserInfoModel userInfoModel = dataSnapshot.getValue(UserInfoModel.class);
                userInfoModel.setUid(userId);
                return userInfoModelMapper.mapFromModel(userInfoModel);
            }
        });

    }
}
