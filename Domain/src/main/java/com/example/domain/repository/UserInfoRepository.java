package com.example.domain.repository;

import com.example.domain.model.UserInfo;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/28/2020.
 */
public interface UserInfoRepository {
    Maybe<String> saveUserInfo(UserInfo userInfo);
}
