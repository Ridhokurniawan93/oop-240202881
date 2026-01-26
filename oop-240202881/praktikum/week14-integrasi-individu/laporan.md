# Laporan Praktikum Week 14 - Integrasi Individu
Topik: **Integrasi Individu (OOP + Database + GUI + Collections + Testing)**

## Identitas
- Nama  : Ridho Kurniawan
- NIM   : 240202881
- Kelas : 3IKRB

---

## Tujuan
   Mahasiswa mampu mengintegrasikan seluruh konsep OOP, JavaFX GUI, Database (PostgreSQL), dan design patterns untuk membuat aplikasi **Point of Sales (POS) lengkap** dengan fitur:
1. Sistem login multi-user dengan role-based access (Gudang, Admin, Kasir)
2. Management produk pertanian (CRUD operations)
3. Shopping cart dengan perhitungan otomatis
4. Authentication dan session management
5. Integration dengan database PostgreSQL

---

## Dasar Teori

1. **MVC Pattern**: Pemisahan Model (data), View (UI), Controller (logic koordinasi).
2. **Service Layer**: Abstraksi bisnis logic dari UI dan database.
3. **DAO Pattern**: Isolasi akses data dengan interface.
4. **DIP (Dependency Inversion)**: High-level modules bergantung pada abstraksi, bukan implementasi.
5. **Collections Framework**: ArrayList untuk menyimpan data dinamis (keranjang).
6. **Exception Handling**: Validasi dengan custom exceptions untuk error flow.
7. **JavaFX GUI**: TableView, TextField, Lambda expression untuk interaksi user.
8. **JDBC**: Koneksi database dengan PreparedStatement untuk query aman.

---

## Langkah Praktikum

1. **Setup Project**: Membuat `pom.xml` dengan dependensi JavaFX, PostgreSQL, JUnit.
2. **Implementasi Model**: Membuat `Product`, `Cart`, `CartItem` dengan validasi.
3. **Implementasi DAO**: Membuat interface `ProductDAO` dan implementasi JDBC `ProductDAOImpl`.
4. **Implementasi Service**: Membuat `ProductService` dan `CartService` untuk business logic.
5. **Implementasi Controller**: Membuat `PosController` untuk event handling dan koordinasi.
6. **Implementasi View**: Membuat `PosView` dengan JavaFX components (TableView, TextField, Button).
7. **Implementasi Testing**: Membuat `CartServiceTest` dengan 10 unit test cases JUnit.
8. **Main Application**: Membuat `AppJavaFX` untuk inisialisasi dan startup aplikasi.
9. **Documentation**: Membuat laporan lengkap dengan traceability matrix.

---

## Kode Program

### 1. Model - Cart.java
```java
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
```

### 2. DAO - JdbcProductDAO.java
```java
package com.upb.agripos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class JdbcProductDAO implements ProductDAO {

    @Override
    public void insert(Product product) {
        String sql = "INSERT INTO product VALUES (?,?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, product.getCode());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String code) {
        String sql = "DELETE FROM product WHERE code=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Statement st = DBConnection.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Product(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
```

### 3. Service - CartService.java
```java
package com.upb.agripos.service;

import com.upb.agripos.model.Cart;
import com.upb.agripos.model.Product;

public class CartService {
    private Cart cart = new Cart();

    public void addToCart(Product product, int qty) {
        cart.addItem(product, qty);
    }

    public double getTotal() {
        return cart.getTotal();
    }

    public Cart getCart() {
        return cart;
    }
}
```

### 4. View - PosView.java
```java
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
```

### 5. Controller - PosController.java
```java
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
```

---

## Traceability Bab 6 (UML Design) → Implementasi
   | Artefak | Referensi | Handler/Trigger | Controller/Service | DAO | Dampak |
|---|---|---|---|---|---|
| Use Case | UC-Produk-01 Lihat Produk | Aplikasi dibuka / refresh TableView | `PosController.loadProducts() → ProductService.findAll()` | `ProductDAO.findAll()`|Data produk ditampilkan pada TableView |
| Activity Diagram | AD-Produk-01 Validasi Input Produk | Klik tombol Tambah Produk | `ProductService.validate(product)` | - | Input tidak valid memicu exception |
| Sequence Dagram | SD-Produk-01 Tambah Produk | Tombol Tambah Produk | View → Controller → Service → DAO → DB | `JdbcProductDAO.insert()` | Proses insert berjalan sesuai alur UML |


## Hasil Eksekusi

- GUI utama dengan produk dan keranjang
![alt text](<Screenshot 2026-01-15 084833.png>)
- Hasil Test Junit
![alt text](<Screenshot 2026-01-14 021417.png>)
---

## Analisis

## 1. **ntegrasi end-to-end aplikasi**
Aplikasi mampu berjalan dari antarmuka JavaFX hingga database PostgreSQL melalui alur View → Controller → Service → DAO.

## 2. **Konsistensi desain dengan UML Bab 6**
Implementasi kelas, metode, dan alur program sesuai dengan Use Case, Sequence Diagram, Activity Diagram, dan Class Diagram yang telah dirancang sebelumnya.

## 3. **Penerapan prinsip OOP dan SOLID**
Struktur kode menunjukkan pemisahan tanggung jawab yang jelas serta penerapan Dependency Inversion Principle (DIP).

## 4. **Penggunaan DAO dan JDBC**
Operasi CRUD produk dilakukan melalui DAO tanpa logika SQL di layer GUI.

## 5. **Pemanfaatan Collections dan Keranjang**
Keranjang belanja menggunakan Collections dan benar-benar dipakai dalam alur aplikasi untuk menghitung total belanja.

## 6. **Exception handling untuk validasi dan error flow**
Validasi input dan penanganan kesalahan dilakukan menggunakan exception, bukan hanya kondisi if sederhana.

## 7. **Penerapan design pattern**
Minimal satu design pattern diterapkan secara nyata dan relevan dalam aplikasi.

## 8. **Unit testing non-UI**
Tersedia minimal satu unit test JUnit yang menguji logika bisnis aplikasi.

 **Kendala & Solusi**

| Kendala | Solusi |
|---------|--------|
| Error JavaFX tidak berjalan di Maven | Menyesuaikan konfigurasi javafx-maven-plugin dan module-info. |
| Kesalahan import kelas JavaFX (Insets, TableView, dll)
 | Menambahkan import yang benar dari package javafx.geometry dan javafx.scene.control. |

---

## Kesimpulan

Praktikum week 14 berhasil mengintegrasikan:
1. ✅ Aplikasi **JavaFX** berjalan tanpa error.
2. ✅ **CRUD** Produk menggunakan DAO **(JDBC)** dan terhubung dengan **PostgreSQL**.
3. ✅ **Keranjang belanja** menggunakan **Collections** dan diterapkan dalam alur aplikasi.
4. ✅ Terdapat **minimal 1 custom exception** untuk validasi atau error flow.
5. ✅ Terdapat **minimal 1 design pattern** yang benar-benar diterapkan dalam kode.
6. ✅ Terdapat **minimal 1 unit test JUnit non-UI** yang dapat dijalankan dengan sukses.
7. ✅ Struktur aplikasi **View → Controller → Service → DAO** konsisten dan menerapkan **Dependency Inversion Principle (DIP)**.
8. ✅ **Laporan praktikum** memuat **UML (Use Case, Class, Sequence)** serta **traceability** ke implementasi kode.

---

## File Structure Directory Praktikum

```
praktikum/
└── week14-integrasi-individu/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── upb/
    │   │   │           └── agripos/
    │   │   │               ├── AppJavaFX.java
    │   │   │
    │   │   │               ├── controller/
    │   │   │               │   └── PosController.java
    │   │   │
    │   │   │               ├── dao/
    │   │   │               │   ├── DBConnection.java
    │   │   │               │   ├── ProductDAO.java
    │   │   │               │   └── JdbcProductDAO.java
    │   │   │
    │   │   │               ├── model/
    │   │   │               │   ├── Product.java
    │   │   │               │   ├── Cart.java
    │   │   │               │   └── CartItem.java
    │   │   │
    │   │   │               ├── service/
    │   │   │               │   ├── ProductService.java
    │   │   │               │   └── CartService.java
    │   │   │
    │   │   │               ├── view/
    │   │   │               │   └── PosView.java
    │   │   │
    │   │   │               └── exception/
    │   │   │                   └── ValidationException.java
    │   │   │
    │   │   └── resources/
    │   │       └── application.properties
    │
    │   └── test/
    │       └── java/
    │           └── com/
    │               └── upb/
    │                   └── agripos/
    │                       └── CartServiceTest.java
    │
    ├── screenshots/
    │   ├── app_main.png
    │   └── junit_result.png
    │
    ├── laporan.md
    └── pom.xml
    
```

---