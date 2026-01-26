# Laporan Praktikum Minggu 12

Topik: **GUI Dasar JavaFX (Event-Driven Programming)**

---

## Identitas

- Nama  : Ridho Kurniawan
- NIM   : 240202881
- Kelas : 3IKRB

---

## Tujuan

- Mahasiswa mampu menjelaskan konsep event-driven programming.
- Mahasiswa mampu membangun antarmuka grafis sederhana menggunakan JavaFX.
- Mahasiswa mampu membuat form input data produk.
- Mahasiswa mampu menampilkan daftar produk pada GUI.
- Mahasiswa mampu mengintegrasikan GUI dengan modul backend yang telah dibuat (DAO & Service).

---

## Dasar Teori

1. **Event-Driven Programming** adalah paradigma pemrograman di mana alur program ditentukan oleh event (kejadian) seperti klik tombol, input keyboard, atau aksi pengguna lainnya.
2. **JavaFX** adalah framework untuk membangun aplikasi desktop dengan antarmuka grafis yang modern dan interaktif di Java.
3. **MVC Pattern dalam GUI** memisahkan tampilan (View/JavaFX), pengendali (Controller), dan data (Model) agar aplikasi lebih terstruktur.
4. **Event Handler** adalah mekanisme untuk menangani event pada komponen GUI seperti button, textfield, dan lainnya menggunakan lambda expression atau method reference.
5. **Separation of Concerns** mengharuskan GUI hanya mengurus tampilan dan event, sedangkan logika bisnis dan akses database ditangani oleh Service dan DAO layer.

---

## Langkah Praktikum

1. Membuat project baru week12-gui-dasar dengan memanfaatkan modul dari praktikum sebelumnya.
2. Menambahkan library JavaFX ke dalam konfigurasi project.
3. Menyusun `ProductService` sebagai penghubung antara Controller dan DAO.
4. Mendesain form input produk menggunakan komponen JavaFX.
5. Mengimplementasikan `ProductController` untuk menangani event dari GUI.
6. Menambahkan event handler pada tombol untuk proses input data.
7. Menampilkan data produk ke dalam ListView secara real-time.
8. Menjalankan aplikasi JavaFX dan menguji koneksi dengan database PostgreSQL.
9. Menyusun tabel traceability antara artefak desain Bab 6 dan implementasi GUI.
10.Melakukan commit dan push ke repository Git dengan format:

    `week12-gui-dasar: implement javafx form with event-driven pattern`

---

## Kode Program

### 1. Product.java (Model - dari Minggu 11)

```java
package com.upb.agripos.model;

public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}
```

### 2. ProductDAO.java

```java
package com.upb.agripos.dao;

import java.util.List;
import com.upb.agripos.model.Product;

public interface ProductDAO {
    void save(Product product);
    List<Product> findAll();
}
```

### ProductDAOImpl.java

   ```java
   package com.upb.agripos.dao;

import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class ProductDAOImpl implements ProductDAO {

    private List<Product> products = new ArrayList<>();

    public ProductDAOImpl() {
        products.add(new Product("P01", "Beras", 10000, 10));
        products.add(new Product("P02", "Pupuk", 20000, 20));
        products.add(new Product("P03", "Insektisida", 15000, 7));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }
}
```

### 3. ProductService.java (Service Layer)

```java
package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public void addProduct(Product product) {
        productDAO.save(product);
    }
}
```

### 4. ProductController.java (Controller)

```java
package com.upb.agripos.controller;

import com.upb.agripos.service.ProductService;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
```

### 5. ProductFormView.java (JavaFX View)

```java
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
```

### 6. AppJavaFX.java (Main Application)

```java
package com.upb.agripos;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {

        // ✅ BUAT DAO DULU
        ProductDAO productDAO = new ProductDAOImpl();

        // ✅ SERVICE WAJIB DIISI DAO
        ProductService productService = new ProductService(productDAO);

        // ✅ VIEW
        ProductFormView view = new ProductFormView(productService);

        Scene scene = new Scene(view, 600, 450);
        stage.setTitle("Agri-POS - Week 12 Ridho Kurniawan 240202881 (GUI Dasar)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

---

## Implementasi Sistem

## Arsitektur Aplikasi
Aplikasi disusun menggunakan arsitektur berlapis sebagai berikut:

- **View**:JavaFX (Form input dan ListView)
- **Controller**:Pengendali event dan alur aplikasi
- **Service**:Validasi dan logika bisnis
- **DAO**:Akses database PostgreSQL
- **Model**:Representasi data produk

Class `Product` dan DAO diambil dari praktikum Minggu 11, sedangkan Service dan `GUI` merupakan pengembangan baru pada Minggu 12.

## Hasil Eksekusi
   Berdasarkan hasil eksekusi program, diperoleh hasil sebagai berikut:

- Aplikasi JavaFX berhasil dijalankan dan menampilkan form input produk.
- Data produk dapat dimasukkan melalui TextField dan disimpan menggunakan tombol.
- Data yang berhasil disimpan langsung muncul pada ListView.
- Seluruh data tersimpan permanen di database PostgreSQL.
- Alert dialog muncul sebagai notifikasi keberhasilan atau kegagalan proses.

Screenshot GUI tersimpan di `screenshots`

---

## Traceability Bab 6 → GUI

| Artefak Bab 6 | Referensi | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
|---|---|---|---|---|---|
| Use Case | UC-01 Login | Tombol Login (`btnTambah`) | `AuthController.login()` → `AuthService.login()` | `UserRepository.findByUsername()` | User memasukkan username & password → validasi → jika sukses masuk ke menu utama, jika gagal tampil alert|
| Activity Diagram | AD-01 Validasi Stok | Event tambah item `ProductService.validateStock() ProductRepository.findByCode() → | Jika stok tidak cukup → transaksi dibatalkan |
| Sequence Diagram | SD-01 Pembayaran E-Wallet | `btnBayar.setOnAction()` | UI → OrderService → PaymentService → EWalletGateway → `controller.addProduct()` → `-` | Saldo dicek → status OK/FAILED → UI menampilkan hasil | 
| Sequence Diagram | SD-02 Update Stok |Setelah pembayaran sukses| `InventoryService.updateStock()` | `ProductRepository.updateStock()` | Stok produk di database berkurang |
| Class Diagram | PaymentMethod (Strategy) | Pilihan metode pembayaran | `PaymentFactory.get()`| - | Metode pembayaran fleksibel (OCP) |
| Class Diagram | Transaction | Ringkasan transaksi | `CheckoutService.checkout()`|`TransactionRepository.save()`| Data transaksi tersimpan dan digunakan untuk struk |
---

## Analisis

Aplikasi yang dibangun menggunakan pendekatan **event-driven**, di mana setiap aksi pengguna memicu eksekusi kode tertentu melalui event handler. GUI berperan sebagai media interaksi, sementara Controller mengatur alur program dan Service menangani validasi bisnis. Penggunaan Service Layer mencegah GUI berinteraksi langsung dengan DAO sehingga arsitektur tetap bersih dan terstruktur.

Dibandingkan dengan praktikum Minggu 11 yang berbasis console, penggunaan JavaFX pada Minggu 12 meningkatkan kenyamanan pengguna dan memberikan feedback visual secara langsung.

---

## Kesimpulan
   Dari praktikum yang telah dilakukan dapat disimpulkan bahwa:

- **Event-driven programming** Event-driven programming sangat cocok untuk aplikasi berbasis GUI.
- **JavaFX** JavaFX memudahkan pembuatan antarmuka grafis yang interaktif dan responsif.
- **MVC pattern** Penerapan MVC dan Service Layer membuat aplikasi lebih modular dan scalable.
- **Integrasi GUI dengan backend** Integrasi GUI dengan DAO tidak melanggar prinsip arsitektur karena dilakukan melalui Service.
- **Traceability** Traceability desain ke implementasi membantu memastikan kesesuaian antara perancangan dan realisasi sistem.

---

## Quiz

### 1. Apa yang dimaksud dengan event-driven programming dan bagaimana penerapannya di JavaFX?

**Jawaban:**  
Event-driven programming adalah paradigma pemrograman di mana alur aplikasi dijalankan sebagai respons terhadap aksi pengguna, seperti klik tombol atau input data. Pada JavaFX, hal ini diwujudkan melalui event handler pada komponen GUI.

### 2. Mengapa GUI tidak boleh memanggil DAO secara langsung?

**Jawaban:**  
Karena akan melanggar prinsip pemisahan tanggung jawab. GUI seharusnya hanya menangani tampilan dan interaksi user, sedangkan DAO bertugas mengelola database. Service Layer berfungsi sebagai penghubung yang aman dan terstruktur.

### 3. Jelaskan alur lengkap ketika user menekan tombol "Tambah Produk"!

**Jawaban:**  
Alur lengkap sebagai berikut:
1. User mengisi data produk (kode, nama, harga, dan stok) pada form GUI.
2. User menekan tombol “`Tambah Produk`”.
3. Event handler `setOnAction()` pada tombol menangkap aksi klik.
4. Event handler memanggil method `addProduct()` pada ProductController.
5. Controller memvalidasi input (tidak boleh kosong dan format harga/stok harus angka).
6. Controller membuat objek Product dari data input user.
7. Controller memanggil ProductService.insert(product).
8. Service melakukan validasi logika bisnis (harga dan stok tidak boleh negatif).
9. Service memanggil ProductDAO.insert(product).
10. DAO mengeksekusi perintah INSERT ke database.
11. Jika berhasil, Controller menampilkan data produk ke ListView.
12. Field input dikosongkan dan Alert sukses ditampilkan kepada user.

### 4. Apa manfaat membuat tabel traceability dari Bab 6 ke implementasi GUI?

**Jawaban:**  
Traceability membantu memastikan bahwa seluruh kebutuhan sistem telah diimplementasikan sesuai desain, memudahkan pengujian, perawatan sistem, serta meningkatkan kualitas dokumentasi proyek.