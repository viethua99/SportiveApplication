package com.example.domain.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Viet Hua on 04/14/2020.
 */
public class SearchFieldConfig implements Serializable {
    private long startTime;
    private long finishTime;

    public SearchFieldConfig(long startTime, long finishTime) {
        this.startTime = startTime;
        this.finishTime = finishTime;
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
        if (!(o instanceof SearchFieldConfig)) return false;
        SearchFieldConfig that = (SearchFieldConfig) o;
        return startTime == that.startTime &&
                finishTime == that.finishTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, finishTime);
    }

    @Override
    public String toString() {
        return "SearchFieldConfig{" +
                "startTime=" + startTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
