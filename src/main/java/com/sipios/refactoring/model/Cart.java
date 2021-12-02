package com.sipios.refactoring.model;

public class Cart {

    private final Item[] items;
    private final CustomerType customerType;

    public Cart(Item[] items, CustomerType customerType) {
        this.items = items;
        this.customerType = customerType;
    }

    public Item[] getItems() {
        return items;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

}
