package com.example.remote;

import com.example.data.entity.DistrictLocationEntity;
import com.example.data.repository.DistrictLocationDataRemote;
import com.example.remote.mapper.DistrictLocationModelMapper;
import com.example.remote.model.DistrictLocationModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

import javax.inject.Inject;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 4/19/2020
 */
public class DistrictLocationDataRemoteImpl implements DistrictLocationDataRemote {
    DatabaseReference firebaseReference;
    DistrictLocationModelMapper districtLocationModelMapper;

    @Inject
    public DistrictLocationDataRemoteImpl(DatabaseReference databaseReference) {
        this.firebaseReference = databaseReference;
        districtLocationModelMapper = new DistrictLocationModelMapper();
    }

    @Override
    public Maybe<List<DistrictLocationEntity>> getDistrictLocationList() {
        Query query = firebaseReference.child(Constants.KEY_DISTRICT_LOCATION);
        return RxFirebaseDatabase.observeSingleValueEvent(query, DataSnapshotMapper.listOf(DistrictLocationModel.class))
                .map(new Function<List<DistrictLocationModel>, List<DistrictLocationEntity>>() {
                    @Override
                    public List<DistrictLocationEntity> apply(List<DistrictLocationModel> districtLocationModelList) throws Exception {
                        return districtLocationModelMapper.mapFromModels(districtLocationModelList);
                    }
                });
    }
}
