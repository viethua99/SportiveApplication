package com.example.domain.model;

/**
 * Created by Viet Hua on 04/29/2020.
 */
public class IsLogged {
    private boolean isLogged;
    private String userId;

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

    @Override
    public String toString() {
        return "IsLogged{" +
                "isLogged=" + isLogged +
                ", userId='" + userId + '\'' +
                '}';
    }
}
