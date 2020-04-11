package com.example.remote.mapper;

import com.example.data.entity.SportFieldEntity;
import com.example.remote.model.SportFieldModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public class SportFieldModelMapper implements BaseMapper<SportFieldEntity, SportFieldModel> {

    @Override
    public SportFieldModel mapToModel(SportFieldEntity sportFieldEntity) {
        SportFieldModel sportFieldModel = new SportFieldModel();
        sportFieldModel.setFieldId(sportFieldEntity.getFieldId());
        sportFieldModel.setName(sportFieldEntity.getName());
        sportFieldModel.setAddress(sportFieldEntity.getAddress());
        sportFieldModel.setPrice(sportFieldEntity.getPrice());
        sportFieldModel.setImgPath(sportFieldEntity.getImgPath());
        sportFieldModel.setRating(sportFieldEntity.getRating());
        return sportFieldModel;
    }

    @Override
    public SportFieldEntity mapFromModel(SportFieldModel sportFieldModel) {
        SportFieldEntity sportFieldEntity = new SportFieldEntity();
        sportFieldEntity.setFieldId(sportFieldModel.getFieldId());
        sportFieldEntity.setName(sportFieldModel.getName());
        sportFieldEntity.setAddress(sportFieldModel.getAddress());
        sportFieldEntity.setImgPath(sportFieldModel.getImgPath());
        sportFieldEntity.setPrice(sportFieldModel.getPrice());
        sportFieldEntity.setRating(sportFieldModel.getRating());
        return sportFieldEntity;
    }

    public List<SportFieldModel> mapToModels(List<SportFieldEntity> sportFieldEntityList) {
        List<SportFieldModel> sportFieldModelList = new ArrayList<>();
        for (SportFieldEntity sportFieldEntity : sportFieldEntityList) {
            sportFieldModelList.add(mapToModel(sportFieldEntity));
        }
        return sportFieldModelList;
    }

    public List<SportFieldEntity> mapFromModels(List<SportFieldModel> sportFieldModelList) {
        List<SportFieldEntity> sportFieldEntityList = new ArrayList<>();
        for (SportFieldModel sportFieldModel : sportFieldModelList) {
            sportFieldEntityList.add(mapFromModel(sportFieldModel));
        }
        return sportFieldEntityList;
    }
}
