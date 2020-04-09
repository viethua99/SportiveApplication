package com.example.domain.model;

public class TestModel {
    private String district;
    private String city;
    private String number;

    public TestModel(String district, String city, String number) {
        this.district = district;
        this.city = city;
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
