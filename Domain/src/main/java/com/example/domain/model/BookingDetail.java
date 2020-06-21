package com.example.domain.model;

/**
 * Created by Viet Hua on 06/21/2020.
 */

public class BookingDetail {
    private long startTime;
    private long finishTime;
    private int totalPrice;
    private int duration;
    private String fieldImagePath;
    private String fieldName;
    private SportFieldAddress sportFieldAddress;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFieldImagePath() {
        return fieldImagePath;
    }

    public void setFieldImagePath(String fieldImagePath) {
        this.fieldImagePath = fieldImagePath;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public SportFieldAddress getSportFieldAddress() {
        return sportFieldAddress;
    }

    public void setSportFieldAddress(SportFieldAddress sportFieldAddress) {
        this.sportFieldAddress = sportFieldAddress;
    }

    @Override
    public String toString() {
        return "BookingDetail{" +
                "startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", totalPrice=" + totalPrice +
                ", duration=" + duration +
                ", fieldImagePath='" + fieldImagePath + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", sportFieldAddress=" + sportFieldAddress +
                '}';
    }
}
