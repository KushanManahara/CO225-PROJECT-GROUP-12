package com.example.bitmart.model.authentication;

public class AuthenticationAccept {
    private int USERID;
    private boolean authorized;

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}