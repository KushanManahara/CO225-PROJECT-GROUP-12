package com.bitmart.bitmartserver.model.initialvalue;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class InitialValue {
    @Id
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
