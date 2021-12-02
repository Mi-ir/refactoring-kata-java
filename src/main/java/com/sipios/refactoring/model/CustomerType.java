package com.sipios.refactoring.model;

public enum CustomerType {
    STANDARD_CUSTOMER("standard"),
    PREMIUM_CUSTOMER("premium"),
    PLATINUM_CUSTOMER("platinum");

    private final String value;

    CustomerType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
