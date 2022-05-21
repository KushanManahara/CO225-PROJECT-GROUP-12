package com.bitmart.bitmartserver.model.result;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resultId;
    private int auctioNumber;
    private int winID;
    private int winAmount;
    private int initialPrice;
}
