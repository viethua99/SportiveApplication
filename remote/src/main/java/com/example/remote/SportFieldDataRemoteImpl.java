package com.example.remote;

import android.util.Log;

import com.example.data.entity.SportFieldEntity;
import com.example.data.repository.SportFieldDataRemote;
import com.example.remote.mapper.SportFieldModelMapper;
import com.example.remote.model.SportFieldModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;


/**
 * Created by Viet Hua on 04/11/2020.
 */
public class SportFieldDataRemoteImpl implements SportFieldDataRemote {
    private SportFieldModelMapper sportFieldModelMapper;
    private DatabaseReference firebaseDatabase;

    @Inject
    public SportFieldDataRemoteImpl(DatabaseReference databaseReference) {
        sportFieldModelMapper = new SportFieldModelMapper();
        this.firebaseDatabase = databaseReference;
    }

    @Override
    public Maybe<List<SportFieldEntity>> getSportFieldList() {
        Query query = firebaseDatabase.child(Constants.KEY_FIELD);
        return RxFirebaseDatabase.observeSingleValueEvent(query, new Function<DataSnapshot, List<SportFieldEntity>>() {
            @Override
            public List<SportFieldEntity> apply(DataSnapshot dataSnapshot) throws Exception {
                List<SportFieldModel> sportFieldModelList = new ArrayList<>();
                for (DataSnapshot subSnapshot : dataSnapshot.getChildren()) {
                    SportFieldModel sportFieldModel = new SportFieldModel();
                    sportFieldModel.setFieldId(subSnapshot.getKey());
                    sportFieldModel.setName(subSnapshot.child("name").getValue(String.class));
                    sportFieldModel.setImgPath(subSnapshot.child("imgPath").getValue(String.class));
                    sportFieldModel.setRating(((Long) subSnapshot.child("rating").getValue()).intValue());
                    sportFieldModel.setPrice(((Long) subSnapshot.child("price").getValue()).intValue());
                    sportFieldModel.setLatitude(subSnapshot.child("latitude").getValue(Float.class));
                    sportFieldModel.setLongitude(subSnapshot.child("longitude").getValue(Float.class));
                    sportFieldModel.getSportFieldAddressModel().setStreet(subSnapshot.child("address").child("street").getValue(String.class));
                    sportFieldModel.getSportFieldAddressModel().setDistrict(subSnapshot.child("address").child("district").getValue(String.class));
                    sportFieldModelList.add(sportFieldModel);
                }
                return sportFieldModelMapper.mapFromModels(sportFieldModelList);
            }
        });
    }

    @Override
    public Maybe<SportFieldEntity> getSportFieldById(String id) {
        Query query = firebaseDatabase.child(Constants.KEY_FIELD).child(id);
        return RxFirebaseDatabase.observeSingleValueEvent(query, new Function<DataSnapshot, SportFieldEntity>() {
            @Override
            public SportFieldEntity apply(DataSnapshot dataSnapshot) throws Exception {
                SportFieldModel sportFieldModel = new SportFieldModel();
                sportFieldModel.setFieldId(dataSnapshot.getKey());
                sportFieldModel.setName(dataSnapshot.child("name").getValue(String.class));
                sportFieldModel.setImgPath(dataSnapshot.child("imgPath").getValue(String.class));
                sportFieldModel.setRating(((Long) dataSnapshot.child("rating").getValue()).intValue());
                sportFieldModel.setPrice(((Long) dataSnapshot.child("price").getValue()).intValue());
                sportFieldModel.setLatitude(dataSnapshot.child("latitude").getValue(Float.class));
                sportFieldModel.setLongitude(dataSnapshot.child("longitude").getValue(Float.class));
                sportFieldModel.getSportFieldAddressModel().setStreet(dataSnapshot.child("address").child("street").getValue(String.class));
                sportFieldModel.getSportFieldAddressModel().setDistrict(dataSnapshot.child("address").child("district").getValue(String.class));
                return sportFieldModelMapper.mapFromModel(sportFieldModel);
            }
        });
    }

    @Override
    public Maybe<List<String>> getSportFieldIdList() {
        Query query = firebaseDatabase.child(Constants.KEY_FIELD);
        return RxFirebaseDatabase.observeSingleValueEvent(query, new Function<DataSnapshot, List<String>>() {
            @Override
            public List<String> apply(DataSnapshot dataSnapshot) throws Exception {
                List<String> sportFieldId = new ArrayList<>();
                for (DataSnapshot subSnapShot : dataSnapshot.getChildren()) {
                    sportFieldId.add(subSnapShot.getKey());
                }
                return sportFieldId;
            }
        });
    }

    @Override
    public Maybe<List<String>> getSportFieldIdListByDistrict(final String district) {
        Query query = firebaseDatabase.child(Constants.KEY_FIELD);
        return RxFirebaseDatabase.observeSingleValueEvent(query, new Function<DataSnapshot, List<String>>() {
            @Override
            public List<String> apply(DataSnapshot dataSnapshot) throws Exception {
                List<String> sportFieldIdList = new ArrayList<>();
                for (DataSnapshot subSnapshot : dataSnapshot.getChildren()) {
                    if (subSnapshot.child("address").child("district").getValue().equals(district)) {
                        sportFieldIdList.add(subSnapshot.getKey());
                    }
                }
                return sportFieldIdList;
            }
        });
    }
}
