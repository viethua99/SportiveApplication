package com.example.domain.model;

import java.util.Objects;

/**
 * Created by Viet Hua on 4/13/2020
 */
public class FieldBooking {
    private String bookingId;
    private long startTime;
    private long finishTime;
    private int duration;
    private String userId;
    private String fieldId;
    private String fieldName;
    private String fieldImg;
    private int totalPrice;

    public FieldBooking() {

    }

    public FieldBooking(long startTime, long finishTime, int duration, String userId, String fieldId, String fieldName, String fieldImg, int totalPrice) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.userId = userId;
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldImg = fieldImg;
        this.totalPrice = totalPrice;
        this.duration = duration;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldImg() {
        return fieldImg;
    }

    public void setFieldImg(String fieldImg) {
        this.fieldImg = fieldImg;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldBooking)) return false;
        FieldBooking that = (FieldBooking) o;
        return startTime == that.startTime &&
                finishTime == that.finishTime &&
                totalPrice == that.totalPrice &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(fieldId, that.fieldId) &&
                Objects.equals(fieldName, that.fieldName) &&
                Objects.equals(fieldImg, that.fieldImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, finishTime, userId, fieldId, fieldName, fieldImg, totalPrice);
    }

    @Override
    public String toString() {
        return "FieldBooking{" +
                "bookingId='" + bookingId + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", duration=" + duration +
                ", userId='" + userId + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldImg='" + fieldImg + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
