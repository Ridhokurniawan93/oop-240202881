package com.upb.agripos.view;

import com.upb.agripos.model.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProductTableView extends VBox {

    private TableView<Product> table;
    private ObservableList<Product> productList;

    public ProductTableView() {
        setPadding(new Insets(10));
        setSpacing(10);

        Text title = new Text("Manajemen Produk Agri-POS");

        // ===== TABLE =====
        table = new TableView<>();

        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCode()));

        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Product, Number> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getPrice()));

        TableColumn<Product, Number> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getStock()));

        table.getColumns().addAll(colCode, colName, colPrice, colStock);

        // ===== DATA AWAL =====
        productList = FXCollections.observableArrayList(
                new Product("P01", "Beras", 50000, 10),
                new Product("P02", "Pupuk", 30000, 20),
                new Product("P03", "Insektisida", 150000, 7),
                new Product("P04", "Benih Padi", 20000, 11)
        );

        table.setItems(productList);

        // ===== FORM INPUT =====
        TextField tfCode = new TextField();
        tfCode.setPromptText("Kode");

        TextField tfName = new TextField();
        tfName.setPromptText("Nama Produk");

        TextField tfPrice = new TextField();
        tfPrice.setPromptText("Harga");

        TextField tfStock = new TextField();
        tfStock.setPromptText("Stok");

        Button btnAdd = new Button("Tambah Produk");
        btnAdd.setOnAction(e -> {
            productList.add(new Product(
                    tfCode.getText(),
                    tfName.getText(),
                    Double.parseDouble(tfPrice.getText()),
                    Integer.parseInt(tfStock.getText())
            ));
        });

        HBox form = new HBox(10, tfCode, tfName, tfPrice, tfStock, btnAdd);

        Button btnDelete = new Button("Hapus Produk");
        btnDelete.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                productList.remove(selected);
            }
        });

        getChildren().addAll(title, form, table, btnDelete);
    }

    public VBox getView() {
        return this;
    }
}
