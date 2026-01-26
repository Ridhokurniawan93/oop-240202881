package com.upb.agripos.view;

import com.upb.agripos.controller.PosController;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PosView {

    public TextField tfCode = new TextField();
    public TextField tfName = new TextField();
    public TextField tfPrice = new TextField();
    public TextField tfStock = new TextField();
    public TextField tfQty = new TextField();

    public Button btnAddProduct = new Button("Tambah Produk");
    public Button btnDeleteProduct = new Button("Hapus Produk");
    public Button btnAddCart = new Button("Tambah ke Keranjang");
    public Button btnRemoveItem = new Button("Hapus Item");
    public Button btnClearCart = new Button("Clear Keranjang");
    public Button btnCheckout = new Button("Checkout");

    public TableView productTable = new TableView();
    public TableView cartTable = new TableView();

    public Label lblTotal = new Label("Total Belanja: Rp 0");

    private VBox root = new VBox(15);

    public PosView() {
        root.setPadding(new Insets(15));

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Kode"), 0, 0);
        form.add(tfCode, 1, 0);
        form.add(new Label("Nama"), 0, 1);
        form.add(tfName, 1, 1);
        form.add(new Label("Harga"), 0, 2);
        form.add(tfPrice, 1, 2);
        form.add(new Label("Stok"), 0, 3);
        form.add(tfStock, 1, 3);

        HBox btnProduk = new HBox(10, btnAddProduct, btnDeleteProduct);

        VBox produkBox = new VBox(10,
                new Label("Manajemen Produk"),
                form,
                btnProduk,
                productTable
        );

        HBox cartInput = new HBox(10, new Label("Jumlah"), tfQty, btnAddCart);

        VBox cartBox = new VBox(10,
                new Label("Keranjang Belanja"),
                cartInput,
                cartTable,
                new HBox(10, btnRemoveItem, btnClearCart)
        );

        VBox checkoutBox = new VBox(10,
                new Label("Ringkasan & Checkout"),
                lblTotal,
                btnCheckout
        );

        root.getChildren().addAll(produkBox, cartBox, checkoutBox);

        new PosController(this);
    }

    public Parent getRoot() {
        return root;
    }
}
