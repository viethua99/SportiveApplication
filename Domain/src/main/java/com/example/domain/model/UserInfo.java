package com.example.domain.model;

import java.util.Objects;

/**
 * Created by Viet Hua on 04/28/2020.
 */
public class UserInfo {
    private String uid;
    private String name;
    private String phoneNumber;
    private String email;

    public UserInfo() {

    }

    public UserInfo(String uid, String phoneNumber, String email) {
        this.uid = uid;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(uid, userInfo.uid) &&
                Objects.equals(name, userInfo.name) &&
                Objects.equals(phoneNumber, userInfo.phoneNumber) &&
                Objects.equals(email, userInfo.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, name, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
