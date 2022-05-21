package com.bitmart.bitmartserver.model.authentication;

public class AuthenticationAccept {
    private int userID = -1;
    private boolean authorized = false;

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

