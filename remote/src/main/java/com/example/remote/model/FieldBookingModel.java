package com.example.remote.model;

/**
 * Created by Viet Hua on 4/13/2020
 */
public class FieldBookingModel {
    private String bookingId;
    private long startTime;
    private long finishTime;
    private String userId;
    private String fieldId;
    private String fieldName;
    private String fieldImg;
    private int totalPrice;
    private int duration;

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
    public String toString() {
        return "FieldBookingModel{" +
                "bookingId='" + bookingId + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", userId='" + userId + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldImg='" + fieldImg + '\'' +
                ", totalPrice=" + totalPrice +
                ", duration=" + duration +
                '}';
    }
}
