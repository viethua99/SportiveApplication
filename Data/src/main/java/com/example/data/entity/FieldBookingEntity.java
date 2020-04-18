package com.example.data.entity;

/**
 * Created by Viet Hua on 4/13/2020
 */
public class FieldBookingEntity {
    private String fieldBookingId;
    private String fieldId;
    private String miniFieldId;
    private long startTime;
    private long finishTime;

    public String getFieldBookingId() {
        return fieldBookingId;
    }

    public void setFieldBookingId(String fieldBookingId) {
        this.fieldBookingId = fieldBookingId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public String getMiniFieldId() {
        return miniFieldId;
    }

    public void setMiniFieldId(String miniFieldId) {
        this.miniFieldId = miniFieldId;
    }

    @Override
    public String toString() {
        return "FieldBookingEntity{" +
                "fieldBookingId='" + fieldBookingId + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", miniFieldId='" + miniFieldId + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
