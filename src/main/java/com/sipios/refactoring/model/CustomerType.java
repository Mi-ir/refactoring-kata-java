package com.sipios.refactoring.model;

public enum CustomerType {
    STANDARD_CUSTOMER(200D, "standard"),
    PREMIUM_CUSTOMER(800D, "premium"),
    PLATINUM_CUSTOMER(2000D, "platinum");

    private final double maxPrice;
    private final String value;

    CustomerType(double maxPrice, String value) {
        this.maxPrice = maxPrice;
        this.value = value;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public String getValue() {
        return value;
    }
}
