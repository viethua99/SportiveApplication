package com.example.data.repository;

import com.example.data.entity.UserInfoEntity;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/28/2020.
 */
public interface UserInfoDataRemote {
    Maybe<String> saveUserInfo(UserInfoEntity userInfoEntity);
}
