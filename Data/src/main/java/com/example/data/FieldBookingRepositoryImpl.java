package com.example.data;

import com.example.data.entity.FieldBookingEntity;
import com.example.data.mapper.FieldBookingEntityMapper;
import com.example.data.repository.FieldBookingDataRemote;
import com.example.domain.model.FieldBooking;
import com.example.domain.repository.FieldBookingRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 4/13/2020
 */
public class FieldBookingRepositoryImpl implements FieldBookingRepository {
    FieldBookingDataRemote fieldBookingDataRemote;
    FieldBookingEntityMapper fieldBookingEntityMapper;

    @Inject
    public FieldBookingRepositoryImpl(FieldBookingDataRemote fieldBookingDataRemote) {
        this.fieldBookingDataRemote = fieldBookingDataRemote;
        fieldBookingEntityMapper = new FieldBookingEntityMapper();
    }

    @Override
    public Maybe<List<FieldBooking>> getFieldBookingList() {
        return fieldBookingDataRemote.getFieldBookingList().map(new Function<List<FieldBookingEntity>, List<FieldBooking>>() {
            @Override
            public List<FieldBooking> apply(List<FieldBookingEntity> fieldBookingEntityList) throws Exception {
                return fieldBookingEntityMapper.mapFromEntities(fieldBookingEntityList);
            }
        });
    }

    @Override
    public Maybe<List<FieldBooking>> getFieldBookingListById(String userId) {
        return fieldBookingDataRemote.getFieldBookingListById(userId)
                .map(new Function<List<FieldBookingEntity>, List<FieldBooking>>() {
                    @Override
                    public List<FieldBooking> apply(List<FieldBookingEntity> fieldBookingEntities) throws Exception {
                        return fieldBookingEntityMapper.mapFromEntities(fieldBookingEntities);
                    }
                });
    }
}
