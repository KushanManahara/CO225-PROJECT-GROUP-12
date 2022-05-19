package com.example.bitmart.model.user;

import java.sql.Timestamp;

public class User{

    private int userId;
    private String fullName;
    private String username;
    private String password;
    private int bidCount;
    private int accBalance;

    //Getters
    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBidCount() {
        return bidCount;
    }

    public int getAccBalance() {
        return accBalance;
    }

    //Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBidCount(int bidCount) {
        this.bidCount = bidCount;
    }

    public void setAccBalance(int accBalance) {
        this.accBalance = accBalance;
    }
}
