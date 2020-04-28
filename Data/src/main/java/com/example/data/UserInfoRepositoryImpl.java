package com.example.data;

import com.example.data.entity.UserInfoEntity;
import com.example.data.mapper.UserInfoEntityMapper;
import com.example.data.repository.UserInfoDataRemote;
import com.example.domain.model.UserInfo;
import com.example.domain.repository.UserInfoRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 04/28/2020.
 */
public class UserInfoRepositoryImpl implements UserInfoRepository {
    UserInfoDataRemote userInfoDataRemote;
    UserInfoEntityMapper userInfoEntityMapper;

    @Inject
    public UserInfoRepositoryImpl(UserInfoDataRemote userInfoDataRemote) {
        this.userInfoDataRemote = userInfoDataRemote;
        userInfoEntityMapper = new UserInfoEntityMapper();
    }

    @Override
    public Maybe<String> saveUserInfo(UserInfo userInfo) {
        return userInfoDataRemote.saveUserInfo(userInfoEntityMapper.mapToEntity(userInfo));
    }

    @Override
    public Maybe<UserInfo> getUserInfoById(String userId) {
        return userInfoDataRemote.getUserInfoById(userId)
                .map(new Function<UserInfoEntity, UserInfo>() {
                    @Override
                    public UserInfo apply(UserInfoEntity userInfoEntity) throws Exception {
                        return userInfoEntityMapper.mapFromEntity(userInfoEntity);
                    }
                });
    }
}
