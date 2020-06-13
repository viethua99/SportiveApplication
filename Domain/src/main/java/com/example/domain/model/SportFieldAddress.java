package com.example.domain.model;

import java.util.Objects;

/**
 * Created by Viet Hua on 04/22/2020.
 */
public class SportFieldAddress {
    private String street;
    private String district;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "SportFieldAddress{" +
                "street='" + street + '\'' +
                ", district='" + district + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportFieldAddress)) return false;
        SportFieldAddress that = (SportFieldAddress) o;
        return Objects.equals(street, that.street) &&
                Objects.equals(district, that.district);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, district);
    }
}
