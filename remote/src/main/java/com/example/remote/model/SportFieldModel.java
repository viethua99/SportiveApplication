package com.example.remote.model;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public class SportFieldModel {
    private String fieldId;
    private String name;
    private String imgPath;
    private SportFieldAddressModel sportFieldAddressModel;
    private int rating;
    private int price;
    private float latitude;
    private float longitude;


    public SportFieldModel() {
        sportFieldAddressModel = new SportFieldAddressModel();
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

    public SportFieldAddressModel getSportFieldAddressModel() {
        return sportFieldAddressModel;
    }

    public void setSportFieldAddressModel(SportFieldAddressModel sportFieldAddressModel) {
        this.sportFieldAddressModel = sportFieldAddressModel;
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
        return "SportFieldModel{" +
                "fieldId='" + fieldId + '\'' +
                ", name='" + name + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", sportFieldAddressModel=" + sportFieldAddressModel +
                ", rating=" + rating +
                ", price=" + price +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

}
