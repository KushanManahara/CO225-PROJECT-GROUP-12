package com.example.bitmart.model.bid;

public class Bid {

    private int bidID;
    private int userID;
    private int currencyID;
    private int amount;
    private String biddingTime;

    @Override
    public String toString() {
        return "BId{" +
                "bidID=" + bidID +
                ", userID=" + userID +
                ", currencyID=" + currencyID +
                ", amount=" + amount +
                ", biddingTime='" + biddingTime + '\'' +
                '}';
    }
}