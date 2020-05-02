package com.example.remote.mapper;

import com.example.data.entity.UserInfoEntity;
import com.example.remote.model.UserInfoModel;

/**
 * Created by Viet Hua on 04/28/2020.
 */
public class UserInfoModelMapper implements BaseMapper<UserInfoEntity, UserInfoModel> {

    @Override
    public UserInfoModel mapToModel(UserInfoEntity userInfoEntity) {
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setName(userInfoEntity.getName());
        userInfoModel.setEmail(userInfoEntity.getEmail());
        userInfoModel.setPhoneNumber(userInfoEntity.getPhoneNumber());
        return userInfoModel;
    }

    @Override
    public UserInfoEntity mapFromModel(UserInfoModel userInfoModel) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUid(userInfoModel.getUid());
        userInfoEntity.setName(userInfoModel.getName());
        userInfoEntity.setEmail(userInfoModel.getEmail());
        userInfoEntity.setPhoneNumber(userInfoModel.getPhoneNumber());
        return userInfoEntity;
    }
}
