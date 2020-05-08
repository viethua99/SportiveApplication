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
        fieldBookingEntity.setBookingId(fieldBooking.getBookingId());
        fieldBookingEntity.setUserId(fieldBooking.getUserId());
        fieldBookingEntity.setFieldId(fieldBooking.getFieldId());
        fieldBookingEntity.setFieldName(fieldBooking.getFieldName());
        fieldBookingEntity.setStartTime(fieldBooking.getStartTime());
        fieldBookingEntity.setFinishTime(fieldBooking.getFinishTime());
        fieldBookingEntity.setDuration(fieldBooking.getDuration());
        fieldBookingEntity.setTotalPrice(fieldBooking.getTotalPrice());
        fieldBookingEntity.setFieldImg(fieldBooking.getFieldImg());
        return fieldBookingEntity;
    }

    @Override
    public FieldBooking mapFromEntity(FieldBookingEntity fieldBookingEntity) {
        FieldBooking fieldBooking = new FieldBooking();
        fieldBooking.setBookingId(fieldBookingEntity.getBookingId());
        fieldBooking.setUserId(fieldBookingEntity.getUserId());
        fieldBooking.setFieldId(fieldBookingEntity.getFieldId());
        fieldBooking.setStartTime(fieldBookingEntity.getStartTime());
        fieldBooking.setFinishTime(fieldBookingEntity.getFinishTime());
        fieldBooking.setDuration(fieldBookingEntity.getDuration());
        fieldBooking.setTotalPrice(fieldBookingEntity.getTotalPrice());
        fieldBooking.setFieldName(fieldBookingEntity.getFieldName());
        fieldBooking.setFieldImg(fieldBookingEntity.getFieldImg());
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
