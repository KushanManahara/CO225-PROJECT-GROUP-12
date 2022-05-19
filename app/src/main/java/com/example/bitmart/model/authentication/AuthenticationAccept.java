package com.example.bitmart.model.authentication;

public class AuthenticationAccept {
    private int userID;
    private boolean authorized;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}