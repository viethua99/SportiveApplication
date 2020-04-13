package com.example.data.mapper;

import com.example.data.entity.FieldBookingEntity;
import com.example.domain.model.FieldBooking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viet Hua on 4/13/2020
 */
public class FieldBookingEntityMapper implements BaseMapper<FieldBookingEntity, FieldBooking> {

    @Override
    public FieldBookingEntity mapToEntity(FieldBooking fieldBooking) {
        FieldBookingEntity fieldBookingEntity = new FieldBookingEntity();
        fieldBookingEntity.setFieldBookingId(fieldBooking.getFieldBookingId());
        fieldBookingEntity.setFieldId(fieldBooking.getFieldId());
        fieldBookingEntity.setStartTime(fieldBooking.getStartTime());
        fieldBookingEntity.setFinishTime(fieldBooking.getFinishTime());
        return fieldBookingEntity;
    }

    @Override
    public FieldBooking mapFromEntity(FieldBookingEntity fieldBookingEntity) {
        FieldBooking fieldBooking = new FieldBooking();
        fieldBooking.setFieldBookingId(fieldBookingEntity.getFieldBookingId());
        fieldBooking.setFieldId(fieldBookingEntity.getFieldId());
        fieldBooking.setStartTime(fieldBookingEntity.getStartTime());
        fieldBooking.setFinishTime(fieldBookingEntity.getFinishTime());
        return fieldBooking;
    }

    public List<FieldBookingEntity> mapToEntities(List<FieldBooking> fieldBookingList) {
        List<FieldBookingEntity> fieldBookingEntityList = new ArrayList<>();
        for (FieldBooking fieldBooking : fieldBookingList) {
            fieldBookingEntityList.add(mapToEntity(fieldBooking));
        }
        return fieldBookingEntityList;
    }

    public List<FieldBooking> mapFromEntities(List<FieldBookingEntity> fieldBookingEntityList) {
        List<FieldBooking> fieldBookingList = new ArrayList<>();
        for (FieldBookingEntity fieldBookingEntity : fieldBookingEntityList) {
            fieldBookingList.add(mapFromEntity(fieldBookingEntity));
        }
        return fieldBookingList;
    }
}
