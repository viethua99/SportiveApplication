package com.example.data;

import com.example.data.entity.DistrictLocationEntity;
import com.example.data.mapper.DistrictLocationEntityMapper;
import com.example.data.repository.DistrictLocationDataRemote;
import com.example.domain.model.DistrictLocation;
import com.example.domain.repository.DistrictLocationRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 4/19/2020
 */
public class DistrictLocationRepositoryImpl implements DistrictLocationRepository {
    DistrictLocationEntityMapper districtLocationEntityMapper;
    DistrictLocationDataRemote districtLocationDataRemote;

    @Inject
    public DistrictLocationRepositoryImpl(DistrictLocationDataRemote districtLocationDataRemote) {
        this.districtLocationDataRemote = districtLocationDataRemote;
        districtLocationEntityMapper = new DistrictLocationEntityMapper();
    }

    @Override
    public Maybe<List<DistrictLocation>> getDistrictLocationList() {
        return districtLocationDataRemote.getDistrictLocationList().map(new Function<List<DistrictLocationEntity>, List<DistrictLocation>>() {
            @Override
            public List<DistrictLocation> apply(List<DistrictLocationEntity> districtLocationEntities) throws Exception {
                return districtLocationEntityMapper.mapFromEntities(districtLocationEntities);
            }
        });
    }
}
