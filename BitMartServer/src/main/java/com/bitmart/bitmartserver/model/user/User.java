package com.bitmart.bitmartserver.model.user;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String fullName;
    private String email;
    private String password;
    private int bidCount;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int accBalance;

    //Getters
    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
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
