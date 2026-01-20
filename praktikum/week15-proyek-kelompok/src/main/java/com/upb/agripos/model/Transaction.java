package com.upb.agripos.model;

import java.util.List;

public class Transaction {
    private List<CartItem> items;
    private double total;

    public Transaction(List<CartItem> items, double total) {
        this.items = items;
        this.total = total;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }
}
