package com.upb.agripos.controller;

import com.upb.agripos.model.Cart;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import com.upb.agripos.view.PosView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;

public class PosController {

    private PosView view;
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private Cart cart = new Cart();

    public PosController(PosView view) {
        this.view = view;
        setupTable();
        setupAction();
    }

    private void setupTable() {
        TableColumn<Product, String> c1 = new TableColumn<>("Kode");
        c1.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getCode()));

        TableColumn<Product, String> c2 = new TableColumn<>("Nama");
        c2.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getName()));

        TableColumn<Product, Number> c3 = new TableColumn<>("Harga");
        c3.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(d.getValue().getPrice()));

        TableColumn<Product, Number> c4 = new TableColumn<>("Stok");
        c4.setCellValueFactory(d -> new javafx.beans.property.SimpleIntegerProperty(d.getValue().getStock()));

        view.productTable.getColumns().addAll(c1, c2, c3, c4);
        view.productTable.setItems(products);

        TableColumn<CartItem, String> k1 = new TableColumn<>("Kode");
        k1.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getCode()));

        TableColumn<CartItem, String> k2 = new TableColumn<>("Nama");
        k2.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getName()));

        TableColumn<CartItem, Number> k3 = new TableColumn<>("Qty");
        k3.setCellValueFactory(d -> new javafx.beans.property.SimpleIntegerProperty(d.getValue().getQty()));

        TableColumn<CartItem, Number> k4 = new TableColumn<>("Subtotal");
        k4.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(d.getValue().getSubtotal()));

        view.cartTable.getColumns().addAll(k1, k2, k3, k4);
        view.cartTable.setItems(cart.getItems());
    }

    private void setupAction() {

        view.btnAddProduct.setOnAction(e -> {
            Product p = new Product(
                    view.tfCode.getText(),
                    view.tfName.getText(),
                    Double.parseDouble(view.tfPrice.getText()),
                    Integer.parseInt(view.tfStock.getText())
            );
            products.add(p);
        });

        view.btnDeleteProduct.setOnAction(e -> {
            Product p = (Product) view.productTable.getSelectionModel().getSelectedItem();
            if (p != null) products.remove(p);
        });

        view.btnAddCart.setOnAction(e -> {
            Product p = (Product) view.productTable.getSelectionModel().getSelectedItem();
            int qty = Integer.parseInt(view.tfQty.getText());
            if (p != null && qty > 0) {
                cart.addItem(p, qty);
                view.lblTotal.setText("Total Belanja: Rp " + cart.getTotal());
            }
        });

        view.btnRemoveItem.setOnAction(e -> {
            CartItem item = (CartItem) view.cartTable.getSelectionModel().getSelectedItem();
            if (item != null) cart.removeItem(item);
        });

        view.btnClearCart.setOnAction(e -> {
            cart.clear();
            view.lblTotal.setText("Total Belanja: Rp 0");
        });

        view.btnCheckout.setOnAction(e ->
                new Alert(Alert.AlertType.INFORMATION, "Checkout Berhasil!\nTotal: Rp " + cart.getTotal()).show()
        );
    }
}
