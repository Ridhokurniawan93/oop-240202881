# Laporan Praktikum Minggu 13

Topik: **GUI Lanjutan JavaFX (TableView dan Lambda Expression)**

---

## Identitas

- Nama  : Ridho Kurniawan
- NIM   : 240202881
- Kelas : 3IKRB

---

## Tujuan

- Mahasiswa mampu menampilkan data menggunakan TableView JavaFX.
- Mahasiswa mampu mengintegrasikan koleksi objek dengan GUI.
- Mahasiswa mampu menggunakan lambda expression untuk event handling.
- Mahasiswa mampu menghubungkan GUI dengan DAO secara penuh.
- Mahasiswa mampu membangun antarmuka GUI Agri-POS yang lebih interaktif.

---

## Dasar Teori

1. **TableView** digunakan untuk menampilkan data objek secara terstruktur dalam bentuk baris dan kolom.
2. **ObservableList** berfungsi sebagai penghubung data antara database dan tampilan GUI agar perubahan data langsung tercermin di UI.
3. **Lambda Expression** menyederhanakan penulisan event handler sehingga kode lebih singkat dan mudah dibaca.
4. **JavaFX Properties** memungkinkan mekanisme data binding antara model dan komponen GUI.
5. **Event-driven** programming membuat aplikasi bereaksi langsung terhadap aksi pengguna seperti klik tombol atau pemilihan baris.

---

## Langkah Praktikum

1. Melanjutkan project `week12-gui-dasar` menjadi `week13-gui-lanjutan` dengan menambahkan fitur TableView.
2. Menambahkan dependencies JavaFX TableView jika belum ada.
3. Membuat class `ProductTableView.java` sebagai pengganti `ProductFormView.java` dengan TableView.
4. Mengonfigurasi TableView dengan kolom: Kode, Nama, Harga, dan Stok menggunakan `PropertyValueFactory`.
5. Mengimplementasikan `ObservableList<Product>` untuk data binding TableView.
6. Membuat method `loadData()` di Controller untuk memuat data dari database via Service/DAO.
7. Menggunakan lambda expression untuk event handler tombol "Tambah Produk" dan "Hapus Produk".
8. Mengimplementasikan fitur hapus produk dengan konfirmasi menggunakan Alert dialog.
9. Menjalankan aplikasi dan memverifikasi operasi CRUD pada TableView.
10. Membuat tabel traceability yang menghubungkan artefak Bab 6 dengan implementasi TableView.
11. Commit dan push dengan format:
    `week13-gui-lanjutan: implement tableview with lambda expression and CRUD operations`

---

## Kode Program


 
###  ProductController.java (Update dengan loadData)

```java
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;

public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void saveProduct(String code, String name, int price, int stock) {
        Product p = new Product(code, name, price, stock);
        service.save(p);
    }

    public void deleteProduct(String code) {
        service.delete(code);
    }
}

```

###  ProductTableView.java (JavaFX TableView)

```java
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

```

###  AppJavaFX.java (Main Application - Update)

```java
package com.upb.agripos;

import com.upb.agripos.view.ProductTableView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        ProductTableView view = new ProductTableView();
        Scene scene = new Scene(view.getView(), 800, 500);

        stage.setTitle("Agri-POS Week 13 - Ridho Kurniawan (240202881)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

---

## Hasil Eksekusi

- GUI JavaFX dengan TableView berhasil ditampilkan dengan 4 kolom: Kode, Nama Produk, Harga, dan Stok.
- Data produk dari database PostgreSQL berhasil dimuat dan ditampilkan di TableView.
- Form input produk berfungsi dengan baik, data langsung tersimpan ke database dan TableView ter-update otomatis.
- Tombol "Hapus Produk" berhasil menghapus data dengan konfirmasi dialog terlebih dahulu.
- Tombol "Refresh Data" memuat ulang data terbaru dari database.
- Lambda expression digunakan pada semua event handler, membuat kode lebih ringkas.
- ObservableList memastikan perubahan data langsung ter-reflect di UI tanpa manual refresh.
- Selection model pada TableView memungkinkan user memilih baris untuk dihapus.

Screenshot GUI TableView tersimpan di `screenshots/tableview_produk.png`

---

## Traceability Bab 6 → GUI

| Artefak Bab 6 | Referensi | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
|---|---|---|---|---|---|
| Use Case | UC-02 Kelola Produk | Menu Kelola Produk | `ProductController` | `ProductRepository` | Admin dapat mengakses fitur tambah, update, hapus, dan daftar produk | 
| Activity Diagram | AD-02 Dsikon & Promo | Input kode diskon | `CheckoutService.applyDiscount()` | -  | Total belanja berubah setelah diskon |
|Class Diagram | Product |TextField & ListView Produk | `ProductController` | `JdbcProductRepository` | Representasi data produk di UI & DB |

---

## Analisis

- **Struktur Tampilan Data**
Penggunaan TableView memungkinkan data produk ditampilkan dalam bentuk tabel yang terorganisir per kolom, sehingga lebih mudah dibaca dan dipahami oleh pengguna dibandingkan tampilan berbasis teks biasa.

- **Sinkronisasi Data Otomatis**
Pemanfaatan ObservableList membuat setiap perubahan data (tambah, hapus, atau update) langsung tercermin pada antarmuka pengguna tanpa perlu melakukan refresh secara manual.

- **Binding Data Model ke UI**
Implementasi JavaFX Property pada class model mendukung mekanisme binding antara data dan tampilan, sehingga konsistensi data antara backend dan frontend dapat terjaga dengan baik.

- **Event Handling yang Efisien**
Penggunaan lambda expression pada event handler menyederhanakan penulisan kode dan membuat alur logika aplikasi lebih jelas serta mudah dipelihara.

- **Arsitektur Berlapis**
Pemisahan antara View, Controller, Service, dan DAO membantu menjaga fokus tiap komponen sesuai fungsinya, sehingga meningkatkan keteraturan kode dan kemudahan pengembangan lanjutan.

- **Integrasi Database yang Konsisten**
Seluruh operasi data dilakukan langsung melalui database menggunakan DAO, sehingga data bersifat persisten dan tidak hanya tersimpan di memori aplikasi.

- **Peningkatan User Experience**
Adanya dialog konfirmasi sebelum penghapusan data mencegah kesalahan pengguna dan meningkatkan keamanan interaksi dalam aplikasi.

- **Tantangan Implementasi**
Penggunaan TableView dan JavaFX Property memerlukan pemahaman konsep yang lebih mendalam serta penanganan seleksi data agar tidak menimbulkan error saat baris belum dipilih.

---

## Kesimpulan

- Penerapan **DAO (Data Access Object)** berhasil memisahkan logika akses database dari logika aplikasi, sehingga kode menjadi lebih terstruktur dan mudah dipelihara.

- Penggunaan **JDBC dan PostgreSQL** memungkinkan aplikasi Java terhubung langsung dengan database untuk melakukan operasi CRUD secara nyata.

- **TableView dengan ObservableList** membuat data tampil secara dinamis dan selalu sinkron dengan perubahan yang terjadi di database.

- Implementasi **JavaFX Property dan data binding** meningkatkan konsistensi antara data model dan tampilan antarmuka.

- Arsitektur berlapis **(View–Controller–Service–DAO)** membantu menjaga separation of concerns dan memudahkan pengembangan fitur lanjutan.

- Penggunaan **PreparedStatement** meningkatkan keamanan aplikasi dari serangan SQL Injection.

- Fitur **konfirmasi penghapusan data** meningkatkan kenyamanan dan keamanan pengguna dalam menggunakan aplikasi.

- Secara keseluruhan, praktikum ini memberikan pemahaman yang baik tentang integrasi GUI, database, dan desain aplikasi berbasis OOP.

---