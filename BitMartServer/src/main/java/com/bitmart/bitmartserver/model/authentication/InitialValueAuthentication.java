package com.bitmart.bitmartserver.model.authentication;

public class InitialValueAuthentication {
    private boolean isHasInitialValue;
    private int initValue;

    public int getInitValue() {
        return initValue;
    }

    public void setInitValue(int initValue) {
        this.initValue = initValue;
    }

    public boolean isHasInitialValue() {
        return isHasInitialValue;
    }

    public void setHasInitialValue(boolean hasInitialValue) {
        isHasInitialValue = hasInitialValue;
    }
}
