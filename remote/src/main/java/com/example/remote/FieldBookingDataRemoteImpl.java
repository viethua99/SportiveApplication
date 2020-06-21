package com.example.remote;

import android.util.Log;

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
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
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
        return RxFirebaseDatabase.observeSingleValueEvent(query, new Function<DataSnapshot, List<FieldBookingEntity>>() {
            @Override
            public List<FieldBookingEntity> apply(DataSnapshot dataSnapshot) throws Exception {
                List<FieldBookingEntity> fieldBookingEntityList = new ArrayList<>();
                for (DataSnapshot subSnapshot : dataSnapshot.getChildren()) {
                    FieldBookingModel fieldBookingModel = new FieldBookingModel();
                    fieldBookingModel.setBookingId(subSnapshot.getKey());
                    fieldBookingModel.setFieldId(subSnapshot.child("fieldId").getValue(String.class));
                    fieldBookingModel.setUserId(subSnapshot.child("userId").getValue(String.class));
                    fieldBookingModel.setMiniFieldId(subSnapshot.child("miniFieldId").getValue(String.class));
                    fieldBookingModel.setFieldName(subSnapshot.child("fieldName").getValue(String.class));
                    fieldBookingModel.setFieldImg(subSnapshot.child("fieldImg").getValue(String.class));
                    fieldBookingModel.setStartTime(subSnapshot.child("startTime").getValue(Long.class));
                    fieldBookingModel.setFinishTime(subSnapshot.child("finishTime").getValue(Long.class));
                    fieldBookingModel.setDuration(((Long) subSnapshot.child("duration").getValue()).intValue());
                    fieldBookingModel.setTotalPrice(((Long) subSnapshot.child("totalPrice").getValue()).intValue());
                    fieldBookingEntityList.add(fieldBookingModelMapper.mapFromModel(fieldBookingModel));
                }
                Log.e("TEST2","TEST " + fieldBookingEntityList);
                return fieldBookingEntityList;
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
                        FieldBookingModel fieldBookingModel = new FieldBookingModel();
                        fieldBookingModel.setBookingId(subSnapshot.getKey());
                        fieldBookingModel.setFieldId(subSnapshot.child("fieldId").getValue(String.class));
                        fieldBookingModel.setUserId(subSnapshot.child("userId").getValue(String.class));
                        fieldBookingModel.setMiniFieldId(subSnapshot.child("miniFieldId").getValue(String.class));
                        fieldBookingModel.setFieldName(subSnapshot.child("fieldName").getValue(String.class));
                        fieldBookingModel.setFieldImg(subSnapshot.child("fieldImg").getValue(String.class));
                        fieldBookingModel.setStartTime(subSnapshot.child("startTime").getValue(Long.class));
                        fieldBookingModel.setFinishTime(subSnapshot.child("finishTime").getValue(Long.class));
                        fieldBookingModel.setDuration(((Long) subSnapshot.child("duration").getValue()).intValue());
                        fieldBookingModel.setTotalPrice(((Long) subSnapshot.child("totalPrice").getValue()).intValue());
                        fieldBookingEntityList.add(fieldBookingModelMapper.mapFromModel(fieldBookingModel));
                    }
                }
                return fieldBookingEntityList;
            }
        });
    }

    @Override
    public Maybe<FieldBookingEntity> getBookingDataById(String bookingId) {
        Query query = firebaseDatabase.child(Constants.KEY_BOOKINGS).child(bookingId);
        return RxFirebaseDatabase.observeSingleValueEvent(query, new Function<DataSnapshot, FieldBookingEntity>() {
            @Override
            public FieldBookingEntity apply(DataSnapshot dataSnapshot) throws Exception {
                FieldBookingModel fieldBookingModel = new FieldBookingModel();
                fieldBookingModel.setBookingId(dataSnapshot.getKey());
                fieldBookingModel.setFieldId(dataSnapshot.child("fieldId").getValue(String.class));
                fieldBookingModel.setMiniFieldId(dataSnapshot.child("miniFieldId").getValue(String.class));
                fieldBookingModel.setUserId(dataSnapshot.child("userId").getValue(String.class));
                fieldBookingModel.setFieldName(dataSnapshot.child("fieldName").getValue(String.class));
                fieldBookingModel.setFieldImg(dataSnapshot.child("fieldImg").getValue(String.class));
                fieldBookingModel.setStartTime(dataSnapshot.child("startTime").getValue(Long.class));
                fieldBookingModel.setFinishTime(dataSnapshot.child("finishTime").getValue(Long.class));
                fieldBookingModel.setDuration(((Long) dataSnapshot.child("duration").getValue()).intValue());
                fieldBookingModel.setTotalPrice(((Long) dataSnapshot.child("totalPrice").getValue()).intValue());
                return fieldBookingModelMapper.mapFromModel(fieldBookingModel);
            }
        });
    }

    @Override
    public Completable saveFieldBooking(FieldBookingEntity fieldBookingEntity) {
        DatabaseReference reference = firebaseDatabase.child(Constants.KEY_BOOKINGS).push();
        FieldBookingModel fieldBookingModel = fieldBookingModelMapper.mapToModel(fieldBookingEntity);
        return RxFirebaseDatabase.setValue(reference, fieldBookingModel);
    }

    @Override
    public Completable deleteBookingById(final String bookingId) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                firebaseDatabase.child(Constants.KEY_BOOKINGS).child(bookingId).removeValue();
                emitter.onComplete();
            }
        });

    }
}
