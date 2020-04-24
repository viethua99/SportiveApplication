package com.example.data.entity;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public class SportFieldEntity {
    private String fieldId;
    private String name;
    private String imgPath;
    private SportFieldAddressEntity sportFieldAddressEntity;
    private int rating;
    private int price;
    private float latitude;
    private float longitude;

    public SportFieldEntity() {
        sportFieldAddressEntity = new SportFieldAddressEntity();
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

    public SportFieldAddressEntity getSportFieldAddressEntity() {
        return sportFieldAddressEntity;
    }

    public void setSportFieldAddressEntity(SportFieldAddressEntity sportFieldAddressEntity) {
        this.sportFieldAddressEntity = sportFieldAddressEntity;
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

    @Override
    public String toString() {
        return "SportFieldEntity{" +
                "fieldId='" + fieldId + '\'' +
                ", name='" + name + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", sportFieldAddressEntity=" + sportFieldAddressEntity +
                ", rating=" + rating +
                ", price=" + price +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
