package com.upb.agripos.view;

import com.upb.agripos.service.ProductService;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProductFormView extends VBox {

    private ProductService productService;

    public ProductFormView(ProductService productService) {
        this.productService = productService;

        setPadding(new Insets(15));
        setSpacing(10);

        // ===== JUDUL =====
        Label title = new Label("Form Input Produk Agri-POS");
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // ===== FORM =====
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        Label lblKode = new Label("Kode Produk:");
        Label lblNama = new Label("Nama Produk:");
        Label lblHarga = new Label("Harga:");
        Label lblStok = new Label("Stok:");

        TextField txtKode = new TextField();
        TextField txtNama = new TextField();
        TextField txtHarga = new TextField();
        TextField txtStok = new TextField();

        form.add(lblKode, 0, 0);
        form.add(txtKode, 1, 0);
        form.add(lblNama, 0, 1);
        form.add(txtNama, 1, 1);
        form.add(lblHarga, 0, 2);
        form.add(txtHarga, 1, 2);
        form.add(lblStok, 0, 3);
        form.add(txtStok, 1, 3);

        // ===== BUTTON =====
        Button btnTambah = new Button("Tambah Produk");

        // ===== LIST PRODUK =====
        Label lblList = new Label("Daftar Produk:");
        ListView<String> listView = new ListView<>();
        listView.setPrefHeight(200);

        // DATA CONTOH (SESUAI GAMBAR)
        listView.getItems().addAll(
        );

        // ===== ACTION BUTTON =====
        btnTambah.setOnAction(e -> {
            String item = txtKode.getText() + " - " +
                          txtNama.getText() +
                          " (Stok: " + txtStok.getText() + ")";
            listView.getItems().add(item);

            txtKode.clear();
            txtNama.clear();
            txtHarga.clear();
            txtStok.clear();
        });

        // ===== MASUKKAN KE ROOT =====
        getChildren().addAll(
                title,
                form,
                btnTambah,
                lblList,
                listView
        );
    }
}
