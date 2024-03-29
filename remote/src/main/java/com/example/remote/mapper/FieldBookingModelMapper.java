package com.example.remote.mapper;

import com.example.data.entity.FieldBookingEntity;
import com.example.domain.model.FieldBooking;
import com.example.remote.model.FieldBookingModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viet Hua on 4/13/2020
 */
public class FieldBookingModelMapper implements BaseMapper<FieldBookingEntity, FieldBookingModel> {
    @Override
    public FieldBookingModel mapToModel(FieldBookingEntity fieldBookingEntity) {
        FieldBookingModel fieldBookingModel = new FieldBookingModel();
        fieldBookingModel.setBookingId(fieldBookingEntity.getBookingId());
        fieldBookingModel.setUserId(fieldBookingEntity.getUserId());
        fieldBookingModel.setFieldId(fieldBookingEntity.getFieldId());
        fieldBookingModel.setStartTime(fieldBookingEntity.getStartTime());
        fieldBookingModel.setFinishTime(fieldBookingEntity.getFinishTime());
        fieldBookingModel.setDuration(fieldBookingEntity.getDuration());
        fieldBookingModel.setFieldName(fieldBookingEntity.getFieldName());
        fieldBookingModel.setFieldImg(fieldBookingEntity.getFieldImg());
        fieldBookingModel.setTotalPrice(fieldBookingEntity.getTotalPrice());
        return fieldBookingModel;
    }

    @Override
    public FieldBookingEntity mapFromModel(FieldBookingModel fieldBookingModel) {
        FieldBookingEntity fieldBookingEntity = new FieldBookingEntity();
        fieldBookingEntity.setBookingId(fieldBookingModel.getBookingId());
        fieldBookingEntity.setUserId(fieldBookingModel.getUserId());
        fieldBookingEntity.setFieldId(fieldBookingModel.getFieldId());
        fieldBookingEntity.setStartTime(fieldBookingModel.getStartTime());
        fieldBookingEntity.setFinishTime(fieldBookingModel.getFinishTime());
        fieldBookingEntity.setDuration(fieldBookingModel.getDuration());
        fieldBookingEntity.setFieldName(fieldBookingModel.getFieldName());
        fieldBookingEntity.setFieldImg(fieldBookingModel.getFieldImg());
        fieldBookingEntity.setTotalPrice(fieldBookingModel.getTotalPrice());
        return fieldBookingEntity;
    }

    public List<FieldBookingModel> mapToModels(List<FieldBookingEntity> fieldBookingEntityList) {
        List<FieldBookingModel> fieldBookingModelList = new ArrayList<>();
        for (FieldBookingEntity fieldBookingEntity : fieldBookingEntityList) {
            fieldBookingModelList.add(mapToModel(fieldBookingEntity));
        }
        return fieldBookingModelList;
    }

    public List<FieldBookingEntity> mapFromModels(List<FieldBookingModel> fieldBookingModelList) {
        List<FieldBookingEntity> fieldBookingEntityList = new ArrayList<>();
        for (FieldBookingModel fieldBookingModel : fieldBookingModelList) {
            fieldBookingEntityList.add(mapFromModel(fieldBookingModel));
        }
        return fieldBookingEntityList;
    }
}
