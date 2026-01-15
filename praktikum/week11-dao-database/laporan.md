# Laporan Praktikum Minggu 11

Topik: **Data Access Object (DAO) dan CRUD Database dengan JDBC**

---

## Identitas

- Nama  : Ridho Kurnniawan
- NIM   : 240202881
- Kelas : 3IKRB

---

## Tujuan Praktikum

1. Memahami konsep dan fungsi Data Access Object (DAO) dalam pemrograman berorientasi objek.

2. Menghubungkan aplikasi Java dengan database relasional menggunakan JDBC.

3. Menerapkan operasi CRUD (Create, Read, Update, Delete) secara lengkap.

4. Mengintegrasikan DAO ke dalam aplikasi Java dengan struktur kode yang rapi dan terpisah.

---

## Dasar Teori

1. **Data Access Object (DAO)**merupakan design pattern yang berfungsi sebagai penghubung antara aplikasi dan database. DAO membantu memisahkan logika bisnis dengan proses akses data.
2. **JDBC (Java Database Connectivity)** adalah API standar Java untuk berkomunikasi dengan database seperti PostgreSQL.
3. **CRUD** adalah operasi dasar dalam pengelolaan data, meliputi penyimpanan data baru, pengambilan data, pembaruan data, dan penghapusan data.
4. **PreparedStatement**digunakan untuk meningkatkan keamanan dan performa eksekusi query SQL..
5. **Interface DAO** digunakan sebagai kontrak agar implementasi database dapat diganti tanpa mengubah kode utama aplikasi.
---

## Langkah Praktikum

1. Membuat project Java dengan nama `week11-dao-database`.
2. Membuat database PostgreSQL `agripos` beserta tabel `products`.
3. Menyusun class model `Product` sebagai representasi tabel.
4. Membuat interface `ProductDAO` yang berisi method CRUD.
5. Mengimplementasikan `ProductDAOImpl.java` dengan JDBC dan PreparedStatement.
6. Menguji seluruh operasi CRUD melalui class `MainDAOTest`.
7. Melakukan commit dan push ke repository Git dengan pesan yang sesuai.
8. Commit dan push dengan format:

   `week11-dao-database: implement DAO pattern and CRUD operations with JDBC`

---

## Kode Program

### 1. SQL Script - products.sql

```sql
CREATE TABLE IF NOT EXISTS product (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE PRECISION,
    stock INTEGER
);
```

### 2. Product.java (Model)

```java
package com.upb.agripos.model;

public class Product {

    private String code;
    private String name;
    private int price;
    private int stock;

    public Product(String code, String name, int price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

```

### 3. ProductDAO.java (Interface)

```java
package com.upb.agripos.dao;

import com.upb.agripos.model.Product;

public interface ProductDAO {

    void insert(Product p);

    void update(Product p);

    Product findByCode(String code);

    void delete(String code);
}

```

### 4. ProductDAOImpl.java (Implementasi DAO)

```java
package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.upb.agripos.model.Product;
import com.upb.agripos.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private Connection conn = DBConnection.getConnection();

    @Override
    public void insert(Product p) {
        String sql = "INSERT INTO product VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getCode());
            ps.setString(2, p.getName());
            ps.setInt(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product p) {
        String sql = "UPDATE product SET stock=? WHERE code=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getStock());
            ps.setString(2, p.getCode());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findByCode(String code) {
        String sql = "SELECT * FROM product WHERE code=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String code) {
        String sql = "DELETE FROM product WHERE code=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

### 5. MainApp.java (Main Program)

```java
package com.upb.agripos;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;
import com.upb.agripos.util.DBConnection;

public class MainApp {

    public static void main(String[] args) {

        System.out.println("Menghubungkan ke database...");
        DBConnection.getConnection();
        System.out.println("Koneksi berhasil...\n");

        ProductDAO dao = new ProductDAOImpl();

        System.out.println("INSERT PRODUCT");
        Product p = new Product("P01", "Pupuk Organik Premium", 30000, 10);
        dao.insert(p);
        System.out.println("Produk berhasil ditambahkan\n");

        System.out.println("Updating Product...");
        p.setStock(8);
        dao.update(p);
        System.out.println("Produk berhasil diperbarui...\n");

        System.out.println("FIND PRODUCT BY CODE");
        Product result = dao.findByCode("P01");

        if (result != null) {
            System.out.println("Data produk:");
            System.out.println("Kode : " + result.getCode());
            System.out.println("Nama : " + result.getName());
            System.out.println("Harga : " + result.getPrice());
            System.out.println("Stok : " + result.getStock());
        }

        System.out.println("\nDELETE PRODUCT");
        dao.delete("P01");
        System.out.println("Produk berhasil dihapus");

        DBConnection.closeConnection();
        System.out.println("Koneksi database ditutup...");
        System.out.println("credit by Ridho Kurniawan - 240202881");
    }
}
```

---

## Hasil Pengujian

   Program berhasil dijalankan dengan hasil sebagai berikut:

- Data produk berhasil disimpan ke dalam database (**INSERT**).
- Data produk dapat dicari berdasarkan kode (**SELECT by code**).
- Seluruh data produk berhasil ditampilkan (**SELECT all**).
- Data produk berhasil diperbarui (**UPDATE**).
- Data produk berhasil dihapus (**DELETE**).

Screenshot hasil eksekusi tersimpan di `screenshots`

---

## Analisis

Penerapan pola `DAO` pada praktikum ini membuat struktur kode menjadi lebih terorganisir. Class utama tidak berisi query SQL secara langsung, melainkan memanggil method DAO. Penggunaan PreparedStatement memberikan perlindungan terhadap SQL injection serta meningkatkan efisiensi eksekusi query. Dibandingkan praktikum sebelumnya yang berfokus pada konsep OOP dan design pattern, praktikum ini menitikberatkan pada integrasi aplikasi Java dengan database nyata.
---

## Kesimpulan
   Berdasarkan praktikum yang telah dilakukan, dapat disimpulkan bahwa:

- Pola DAO sangat membantu dalam memisahkan logika bisnis dan akses database.
- JDBC merupakan solusi standar dan fleksibel untuk koneksi database pada Java.
- PreparedStatement adalah metode terbaik untuk mengeksekusi query SQL secara aman.
- Operasi CRUD merupakan dasar penting dalam pengembangan aplikasi berbasis database.
- Struktur aplikasi menjadi lebih modular, mudah diuji, dan mudah dikembangkan.  
---

## Quiz

### 1. Jelaskan mengapa DAO penting dalam pengembangan aplikasi berbasis database!

**Jawaban:**  
DAO diperlukan untuk memisahkan logika aplikasi dengan proses akses database, sehingga kode lebih rapi, mudah dirawat, dan fleksibel terhadap perubahan database.

### 2. Apa keuntungan menggunakan PreparedStatement dibanding Statement biasa?

**Jawaban:**  
PreparedStatement lebih aman dari SQL injection, lebih efisien, dan mendukung pengelolaan tipe data dengan lebih baik dibanding Statement biasa.

### 3. Jelaskan fungsi masing-masing method dalam interface ProductDAO!

**Jawaban:**  
- `insert()`: menambahkan data
- `findByCode()`: mencari data berdasarkan kode
- `findAll()`: menampilkan seluruh data
- `update()`: memperbarui data
- `delete()`: Menghapus data

### 4. Mengapa SQL query tidak boleh ditulis langsung di method main()?

**Jawaban:**  
Karena akan membuat kode sulit dipelihara dan melanggar prinsip pemisahan tanggung jawab. DAO memastikan kode tetap modular dan reusable.