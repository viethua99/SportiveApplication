package com.example.data.mapper;

import com.example.data.entity.UserInfoEntity;
import com.example.domain.model.UserInfo;

/**
 * Created by Viet Hua on 04/28/2020.
 */
public class UserInfoEntityMapper implements BaseMapper<UserInfoEntity, UserInfo> {

    @Override
    public UserInfoEntity mapToEntity(UserInfo userInfo) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUid(userInfo.getUid());
        userInfoEntity.setName(userInfo.getName());
        userInfoEntity.setEmail(userInfo.getEmail());
        userInfoEntity.setPhoneNumber(userInfo.getPhoneNumber());
        return userInfoEntity;
    }

    @Override
    public UserInfo mapFromEntity(UserInfoEntity userInfoEntity) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(userInfoEntity.getUid());
        userInfo.setName(userInfoEntity.getName());
        userInfo.setEmail(userInfoEntity.getEmail());
        userInfo.setPhoneNumber(userInfoEntity.getPhoneNumber());
        return userInfo;
    }
}
