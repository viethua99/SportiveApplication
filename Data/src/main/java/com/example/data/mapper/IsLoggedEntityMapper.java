package com.example.data.mapper;

import com.example.data.entity.IsLoggedEntity;
import com.example.domain.model.IsLogged;

/**
 * Created by Viet Hua on 04/29/2020.
 */
public class IsLoggedEntityMapper implements BaseMapper<IsLoggedEntity, IsLogged> {

    @Override
    public IsLoggedEntity mapToEntity(IsLogged isLogged) {
        IsLoggedEntity isLoggedEntity = new IsLoggedEntity();
        isLoggedEntity.setLogged(isLogged.isLogged());
        isLoggedEntity.setUserId(isLogged.getUserId());
        return isLoggedEntity;
    }

    @Override
    public IsLogged mapFromEntity(IsLoggedEntity isLoggedEntity) {
        IsLogged isLogged = new IsLogged();
        isLogged.setLogged(isLoggedEntity.isLogged());
        isLogged.setUserId(isLoggedEntity.getUserId());
        return isLogged;
    }
}
