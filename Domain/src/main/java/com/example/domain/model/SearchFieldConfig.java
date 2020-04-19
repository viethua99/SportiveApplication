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

    public SearchFieldConfig(long startTime, long finishTime,int duration) {
        this.startTime = startTime;
        this.finishTime = finishTime;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchFieldConfig)) return false;
        SearchFieldConfig that = (SearchFieldConfig) o;
        return duration == that.duration &&
                startTime == that.startTime &&
                finishTime == that.finishTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, startTime, finishTime);
    }

    @Override
    public String toString() {
        return "SearchFieldConfig{" +
                "duration=" + duration +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
