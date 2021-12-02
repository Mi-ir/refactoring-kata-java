package com.sipios.refactoring.model;

public  class Cart {

    private Item[] items;
    private CustomerType customerType;

    public Cart(Item[] items, CustomerType customerType) {
        this.items = items;
        this.customerType = customerType;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
