package com.example.data;

import com.example.data.mapper.UserInfoEntityMapper;
import com.example.data.repository.UserInfoDataRemote;
import com.example.domain.model.UserInfo;
import com.example.domain.repository.UserInfoRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;

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
}
