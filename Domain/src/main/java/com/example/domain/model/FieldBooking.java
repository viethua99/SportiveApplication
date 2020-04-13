package com.example.domain.model;

import java.util.Objects;

/**
 * Created by Viet Hua on 4/13/2020
 */
public class FieldBooking {
    private String fieldBookingId;
    private String fieldId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldBooking)) return false;
        FieldBooking that = (FieldBooking) o;
        return startTime == that.startTime &&
                finishTime == that.finishTime &&
                Objects.equals(fieldBookingId, that.fieldBookingId) &&
                Objects.equals(fieldId, that.fieldId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldBookingId, fieldId, startTime, finishTime);
    }

    @Override
    public String toString() {
        return "FieldBooking{" +
                "fieldBookingId='" + fieldBookingId + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
