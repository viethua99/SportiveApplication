package com.example.remote.model;

/**
 * Created by Viet Hua on 04/22/2020.
 */
public class SportFieldAddressModel {
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
        return "SportFieldAddressModel{" +
                "street='" + street + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
