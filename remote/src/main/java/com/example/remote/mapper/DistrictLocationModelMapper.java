package com.example.remote.mapper;

import com.example.data.entity.DistrictLocationEntity;
import com.example.remote.model.DistrictLocationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viet Hua on 4/19/2020
 */
public class DistrictLocationModelMapper implements BaseMapper<DistrictLocationEntity, DistrictLocationModel> {

    @Override
    public DistrictLocationModel mapToModel(DistrictLocationEntity districtLocationEntity) {
        DistrictLocationModel districtLocationModel = new DistrictLocationModel();
        districtLocationModel.setName(districtLocationEntity.getName());
        districtLocationModel.setLatitude(districtLocationEntity.getLatitude());
        districtLocationModel.setLongitude(districtLocationEntity.getLongitude());
        return districtLocationModel;
    }

    @Override
    public DistrictLocationEntity mapFromModel(DistrictLocationModel districtLocationModel) {
        DistrictLocationEntity districtLocationEntity = new DistrictLocationEntity();
        districtLocationEntity.setName(districtLocationModel.getName());
        districtLocationEntity.setLatitude(districtLocationModel.getLatitude());
        districtLocationEntity.setLongitude(districtLocationModel.getLongitude());
        return districtLocationEntity;
    }

    public List<DistrictLocationModel> mapToModels(List<DistrictLocationEntity> districtLocationEntityList) {
        List<DistrictLocationModel> districtLocationModelList = new ArrayList<>();
        for (DistrictLocationEntity districtLocationEntity : districtLocationEntityList) {
            districtLocationModelList.add(mapToModel(districtLocationEntity));
        }
        return districtLocationModelList;
    }

    public List<DistrictLocationEntity> mapFromModels(List<DistrictLocationModel> districtLocationModelList) {
        List<DistrictLocationEntity> districtLocationEntityList = new ArrayList<>();
        for (DistrictLocationModel districtLocationModel : districtLocationModelList) {
            districtLocationEntityList.add(mapFromModel(districtLocationModel));
        }
        return districtLocationEntityList;
    }
}
