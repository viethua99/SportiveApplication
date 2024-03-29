package com.example.data.entity;

import java.util.Objects;

/**
 * Created by Viet Hua on 4/19/2020
 */
public class DistrictLocationEntity {
    private String name;
    private float latitude;
    private float longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof DistrictLocationEntity)) return false;
        DistrictLocationEntity that = (DistrictLocationEntity) o;
        return Float.compare(that.latitude, latitude) == 0 &&
                Float.compare(that.longitude, longitude) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude);
    }

    @Override
    public String toString() {
        return "DistrictLocationEntity{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
