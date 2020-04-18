package com.example.remote.model;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public class SportFieldModel {
    private String fieldId;
    private String name;
    private String imgPath;
    private String address;
    private int rating;
    private int price;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "SportField{" +
                "fieldId='" + fieldId + '\'' +
                ", name='" + name + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }
}
