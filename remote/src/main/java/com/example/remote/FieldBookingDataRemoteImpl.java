package com.example.remote;

import com.example.data.entity.FieldBookingEntity;
import com.example.data.repository.FieldBookingDataRemote;
import com.example.remote.mapper.FieldBookingModelMapper;
import com.example.remote.model.FieldBookingModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 4/13/2020
 */
public class FieldBookingDataRemoteImpl implements FieldBookingDataRemote {
    DatabaseReference firebaseDatabase;
    FieldBookingModelMapper fieldBookingModelMapper;

    @Inject
    public FieldBookingDataRemoteImpl(DatabaseReference databaseReference) {
        this.firebaseDatabase = databaseReference;
        fieldBookingModelMapper = new FieldBookingModelMapper();

    }

    @Override
    public Maybe<List<FieldBookingEntity>> getFieldBookingList() {
        Query query = firebaseDatabase.child(Constants.KEY_BOOKINGS);
        return RxFirebaseDatabase.observeSingleValueEvent(query, DataSnapshotMapper.listOf(FieldBookingModel.class))
                .map(new Function<List<FieldBookingModel>, List<FieldBookingEntity>>() {
                    @Override
                    public List<FieldBookingEntity> apply(List<FieldBookingModel> fieldBookingModelList) throws Exception {
                        return fieldBookingModelMapper.mapFromModels(fieldBookingModelList);
                    }
                });
    }

    @Override
    public Maybe<List<FieldBookingEntity>> getFieldBookingListById(final String userId) {
        Query query = firebaseDatabase.child(Constants.KEY_BOOKINGS);
        return RxFirebaseDatabase.observeSingleValueEvent(query, new Function<DataSnapshot, List<FieldBookingEntity>>() {
            @Override
            public List<FieldBookingEntity> apply(DataSnapshot dataSnapshot) throws Exception {
                List<FieldBookingEntity> fieldBookingEntityList = new ArrayList<>();
                for (DataSnapshot subSnapshot : dataSnapshot.getChildren()) {
                    if (subSnapshot.child("userId").getValue().equals(userId)) {
                        FieldBookingModel fieldBookingModel = subSnapshot.getValue(FieldBookingModel.class);
                        fieldBookingEntityList.add(fieldBookingModelMapper.mapFromModel(fieldBookingModel));
                    }
                }
                return fieldBookingEntityList;
            }
        });
    }

    @Override
    public Completable saveFieldBooking(FieldBookingEntity fieldBookingEntity) {
        DatabaseReference reference = firebaseDatabase.child(Constants.KEY_BOOKINGS).push();
        FieldBookingModel fieldBookingModel = fieldBookingModelMapper.mapToModel(fieldBookingEntity);
        return RxFirebaseDatabase.setValue(reference, fieldBookingModel);
    }
}
