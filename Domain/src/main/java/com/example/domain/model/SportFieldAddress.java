package com.example.domain.model;

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
}
