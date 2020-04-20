package com.example.domain.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Viet Hua on 04/14/2020.
 */
public class SearchFieldConfig implements Serializable {
    private int duration;
    private long startTime;
    private long finishTime;
    private float latitude;
    private float longitude;

    public SearchFieldConfig(int duration, long startTime, long finishTime, float latitude, float longitude) {
        this.duration = duration;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchFieldConfig)) return false;
        SearchFieldConfig that = (SearchFieldConfig) o;
        return duration == that.duration &&
                startTime == that.startTime &&
                finishTime == that.finishTime &&
                Float.compare(that.latitude, latitude) == 0 &&
                Float.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, startTime, finishTime, latitude, longitude);
    }

    @Override
    public String toString() {
        return "SearchFieldConfig{" +
                "duration=" + duration +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
