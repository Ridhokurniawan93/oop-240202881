package com.upb.agripos.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int qty) {
        for (CartItem item : items) {
            if (item.getProduct().getCode().equals(product.getCode())) {
                item.addQty(qty);
                return;
            }
        }
        items.add(new CartItem(product, qty));
    }

    public void clear() {
        items.clear();
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    public List<CartItem> getItems() {
        return items;
    }
}
