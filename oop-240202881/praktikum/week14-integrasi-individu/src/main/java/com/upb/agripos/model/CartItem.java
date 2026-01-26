package com.upb.agripos.model;

public class CartItem {
    private String code;
    private String name;
    private int qty;
    private double subtotal;

    public CartItem(String code, String name, int qty, double subtotal) {
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.subtotal = subtotal;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getQty() { return qty; }
    public double getSubtotal() { return subtotal; }

    public void addQty(int q, double price) {
        qty += q;
        subtotal = qty * price;
    }
}
