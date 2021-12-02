package com.sipios.refactoring.model;

public enum ItemType {
    TSHIRT(30, 1),
    DRESS(50, 0.8),
    JACKET(100, 0.9);

    private final double price;
    private final double additionnalDiscountRatio;

    ItemType(double price, double additionnalDiscountRatio) {
        this.price = price;
        this.additionnalDiscountRatio = additionnalDiscountRatio;
    }

    public double getPrice() {
        return price;
    }

    public double getAdditionnalDiscountRatio() {
        return additionnalDiscountRatio;
    }
}
