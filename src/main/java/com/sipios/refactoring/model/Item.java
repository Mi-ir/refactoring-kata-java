package com.sipios.refactoring.model;

public class Item {
    private final ItemType type;
    private final int nb;

    public Item(ItemType type, int quantity) {
        this.type = type;
        this.nb = quantity;
    }

    public ItemType getType() {
        return type;
    }

    public int getNb() {
        return nb;
    }
}
