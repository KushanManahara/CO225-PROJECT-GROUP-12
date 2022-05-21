package com.example.bitmart.model.initialvalue;

import java.sql.Timestamp;

public class InitialValue {
    private String symbol;
    private double initialValue;
    private boolean hasInitial;

    public boolean isHasInitial() {
        return hasInitial;
    }

    public void setHasInitial(boolean hasInitial) {
        this.hasInitial = hasInitial;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }
}
