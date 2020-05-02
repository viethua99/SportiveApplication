package com.example.data.entity;

/**
 * Created by Viet Hua on 04/29/2020.
 */
public class IsLoggedEntity {
    private boolean isLogged;
    private String userId;


    public IsLoggedEntity() {

    }

    public IsLoggedEntity(boolean isLogged, String userId) {
        this.isLogged = isLogged;
        this.userId = userId;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
