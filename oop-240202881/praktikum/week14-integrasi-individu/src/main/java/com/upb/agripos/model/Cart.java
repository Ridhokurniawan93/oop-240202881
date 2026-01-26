package com.upb.agripos.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {

    private ObservableList<CartItem> items = FXCollections.observableArrayList();

    public ObservableList<CartItem> getItems() {
        return items;
    }

    public void addItem(Product p, int qty) {
        for (CartItem item : items) {
            if (item.getCode().equals(p.getCode())) {
                item.addQty(qty, p.getPrice());
                return;
            }
        }
        items.add(new CartItem(
                p.getCode(),
                p.getName(),
                qty,
                qty * p.getPrice()
        ));
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public void clear() {
        items.clear();
    }

    public double getTotal() {
        return items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }
}
