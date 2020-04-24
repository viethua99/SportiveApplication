package com.example.data.mapper;

import com.example.data.entity.DistrictLocationEntity;
import com.example.domain.model.DistrictLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viet Hua on 4/19/2020
 */
public class DistrictLocationEntityMapper implements BaseMapper<DistrictLocationEntity, DistrictLocation> {

    @Override
    public DistrictLocationEntity mapToEntity(DistrictLocation districtLocation) {
        DistrictLocationEntity districtLocationEntity = new DistrictLocationEntity();
        districtLocationEntity.setName(districtLocation.getName());
        districtLocationEntity.setLatitude(districtLocation.getLatitude());
        districtLocationEntity.setLongitude(districtLocation.getLongitude());
        return districtLocationEntity;
    }

    @Override
    public DistrictLocation mapFromEntity(DistrictLocationEntity districtLocationEntity) {
        DistrictLocation districtLocation = new DistrictLocation();
        districtLocation.setName(districtLocationEntity.getName());
        districtLocation.setLatitude(districtLocationEntity.getLatitude());
        districtLocation.setLongitude(districtLocationEntity.getLongitude());
        return districtLocation;
    }

    public List<DistrictLocationEntity> mapToEntities(List<DistrictLocation> districtLocationList) {
        List<DistrictLocationEntity> districtLocationEntityList = new ArrayList<>();
        for (DistrictLocation districtLocation : districtLocationList) {
            districtLocationEntityList.add(mapToEntity(districtLocation));
        }
        return districtLocationEntityList;
    }

    public List<DistrictLocation> mapFromEntities(List<DistrictLocationEntity> districtLocationEntityList) {
        List<DistrictLocation> districtLocationList = new ArrayList<>();
        for (DistrictLocationEntity districtLocationEntity : districtLocationEntityList) {
            districtLocationList.add(mapFromEntity(districtLocationEntity));
        }
        return districtLocationList;
    }
}
