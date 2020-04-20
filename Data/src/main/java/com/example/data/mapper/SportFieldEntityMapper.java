package com.example.data.mapper;

import com.example.data.entity.SportFieldEntity;
import com.example.domain.model.SportField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public class SportFieldEntityMapper implements BaseMapper<SportFieldEntity, SportField> {

    @Override
    public SportFieldEntity mapToEntity(SportField sportField) {
        SportFieldEntity sportFieldEntity = new SportFieldEntity();
        sportFieldEntity.setFieldId(sportField.getFieldId());
        sportFieldEntity.setName(sportField.getName());
        sportFieldEntity.setAddress(sportField.getAddress());
        sportFieldEntity.setPrice(sportField.getPrice());
        sportFieldEntity.setImgPath(sportField.getImgPath());
        sportFieldEntity.setRating(sportField.getRating());
        sportFieldEntity.setLatitude(sportField.getLatitude());
        sportFieldEntity.setLongitude(sportField.getLongitude());
        return sportFieldEntity;
    }

    @Override
    public SportField mapFromEntity(SportFieldEntity sportFieldEntity) {
        SportField sportField = new SportField();
        sportField.setFieldId(sportFieldEntity.getFieldId());
        sportField.setName(sportFieldEntity.getName());
        sportField.setAddress(sportFieldEntity.getAddress());
        sportField.setPrice(sportFieldEntity.getPrice());
        sportField.setImgPath(sportFieldEntity.getImgPath());
        sportField.setRating(sportFieldEntity.getRating());
        sportField.setLatitude(sportFieldEntity.getLatitude());
        sportField.setLongitude(sportFieldEntity.getLongitude());
        return sportField;

    }

    public List<SportFieldEntity> mapToEntities(List<SportField> sportFieldList) {
        List<SportFieldEntity> sportFieldEntityList = new ArrayList<>();
        for (SportField sportField : sportFieldList) {
            sportFieldEntityList.add(mapToEntity(sportField));
        }
        return sportFieldEntityList;
    }

    public List<SportField> mapFromEntities(List<SportFieldEntity> sportFieldEntityList) {
        List<SportField> sportFieldList = new ArrayList<>();
        for (SportFieldEntity sportFieldEntity : sportFieldEntityList) {
            sportFieldList.add(mapFromEntity(sportFieldEntity));
        }
        return sportFieldList;
    }

}
