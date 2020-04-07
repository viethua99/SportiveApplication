package com.example.domain.model;

public class SportField {
    private String imgUrl;
    private String fieldName;
    private String location;
    private String time;
    private String price;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public SportField(String imgUrl, String fieldName, String location, String time, String price) {
        this.imgUrl = imgUrl;
        this.fieldName = fieldName;
        this.location = location;
        this.time = time;
        this.price = price;
    }
}
