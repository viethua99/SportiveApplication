package com.example.domain.model;


import java.util.Objects;

public class SportField {
    private String fieldId;
    private String name;
    private String imgPath;
    private int empty;
    private SportFieldAddress sportFieldAddress;
    private int rating;
    private int price;
    private float latitude;
    private float longitude;

    public SportField() {
        sportFieldAddress = new SportFieldAddress();
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public SportFieldAddress getSportFieldAddress() {
        return sportFieldAddress;
    }

    public void setSportFieldAddress(SportFieldAddress sportFieldAddress) {
        this.sportFieldAddress = sportFieldAddress;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getEmpty() {
        return empty;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportField)) return false;
        SportField that = (SportField) o;
        return rating == that.rating &&
                price == that.price &&
                Float.compare(that.latitude, latitude) == 0 &&
                Float.compare(that.longitude, longitude) == 0 &&
                Objects.equals(fieldId, that.fieldId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(imgPath, that.imgPath) &&
                Objects.equals(sportFieldAddress, that.sportFieldAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldId, name, imgPath, sportFieldAddress, rating, price, latitude, longitude);
    }

    @Override
    public String toString() {
        return "SportField{" +
                "fieldId='" + fieldId + '\'' +
                ", name='" + name + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", empty=" + empty +
                ", sportFieldAddress=" + sportFieldAddress +
                ", rating=" + rating +
                ", price=" + price +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}