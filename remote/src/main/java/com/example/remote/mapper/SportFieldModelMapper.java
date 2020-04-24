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
        sportFieldModel.getSportFieldAddressModel().setStreet(sportFieldEntity.getSportFieldAddressEntity().getStreet());
        sportFieldModel.getSportFieldAddressModel().setDistrict(sportFieldEntity.getSportFieldAddressEntity().getDistrict());
        sportFieldModel.setPrice(sportFieldEntity.getPrice());
        sportFieldModel.setImgPath(sportFieldEntity.getImgPath());
        sportFieldModel.setRating(sportFieldEntity.getRating());
        sportFieldModel.setLatitude(sportFieldEntity.getLatitude());
        sportFieldModel.setLongitude(sportFieldEntity.getLongitude());
        return sportFieldModel;
    }

    @Override
    public SportFieldEntity mapFromModel(SportFieldModel sportFieldModel) {
        SportFieldEntity sportFieldEntity = new SportFieldEntity();
        sportFieldEntity.setFieldId(sportFieldModel.getFieldId());
        sportFieldEntity.setName(sportFieldModel.getName());
        sportFieldEntity.getSportFieldAddressEntity().setStreet(sportFieldModel.getSportFieldAddressModel().getStreet());
        sportFieldEntity.getSportFieldAddressEntity().setDistrict(sportFieldModel.getSportFieldAddressModel().getDistrict());
        sportFieldEntity.setImgPath(sportFieldModel.getImgPath());
        sportFieldEntity.setPrice(sportFieldModel.getPrice());
        sportFieldEntity.setRating(sportFieldModel.getRating());
        sportFieldEntity.setLatitude(sportFieldModel.getLatitude());
        sportFieldEntity.setLongitude(sportFieldModel.getLongitude());
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
