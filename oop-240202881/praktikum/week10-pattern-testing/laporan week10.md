# Laporan Praktikum Minggu 10

Topik: **Design Pattern (Singleton, MVC) dan Unit Testing menggunakan JUnit**

---

## Identitas

- Nama  : Ridho Kurniawan  
- NIM   : 240202881  
- Kelas : 3IKRB  

---

## Tujuan
   Praktikum ini bertujuan untuk:
   
- Memahami konsep dan fungsi design pattern dalam pengembangan perangkat lunak.
- Menerapkan Singleton Pattern untuk mengelola instance objek secara terkontrol.
- Mengimplementasikan arsitektur Model–View–Controller (MVC) pada aplikasi sederhana.
- Menyusun dan menjalankan pengujian unit menggunakan JUnit.
- Mengevaluasi dampak design pattern dan unit testing terhadap kualitas serta keterawatan kode.

---

## Landasan Teori

1. **Design Pattern** merupakan pola solusi yang dapat digunakan kembali untuk menyelesaikan permasalahan desain yang sering muncul dalam pengembangan aplikasi.
   
2. **Singleton Pattern** membatasi pembuatan objek sehingga hanya satu instance yang tersedia dan dapat diakses secara global.
   
3. **MVC (Model–View–Controller)** adalah pola arsitektur yang memisahkan pengelolaan data, tampilan, dan logika kontrol agar pengembangan lebih terstruktur.
   
4. **Unit Testing** adalah proses pengujian bagian terkecil dari program untuk memastikan fungsionalitas berjalan sesuai spesifikasi.
---

## Langkah Praktikum

1. Membuat project Java dengan nama week10-pattern-testing..
2. Mengembangkan class `DatabaseConnection` menggunakan pola **Singleton**.
3. Merancang fitur Product dengan pendekatan MVC.
4. Mengimplementasikan class Model, View, dan Controller.
5. Menghubungkan seluruh komponen MVC pada class utama `AppMVC`.
6. Menyusun skenario pengujian menggunakan framework **JUnit**.
7. Menjalankan aplikasi dan melakukan pengujian unit.
8. Melakukan commit dan push repository dengan pesan:
   
   `week10-pattern-testing: implement singleton, mvc, dan unit testing`

---

## Kode Program

### 1. DatabaseConnection.java (Singleton)

```java
package com.upb.agripos.config;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```

### 2. Product.java (Model)

```java
package com.upb.agripos.model;

public class Product {
    private final String code;
    private final String name;

    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { 
        return code; 
    }
    
    public String getName() { 
        return name; 
    }
}
```

### 3. ConsoleView.java (View)

```java
package com.upb.agripos.view;

public class ConsoleView {
    public void showMessage(String message) {
        System.out.println(message);
    }
}
```

### 4. ProductController.java (Controller)

```java
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class ProductController {
    private final Product model;
    private final ConsoleView view;

    public ProductController(Product model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void showProduct() {
        view.showMessage("Produk: " + model.getCode() + " - " + model.getName());
    }
}
```

### 5. AppMVC.java (Main Program)

```java
package com.upb.agripos;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Hello, I am Ridho Kurniawan-240202881 (Week10)");
        Product product = new Product("P01", "Pupuk Organik");
        ConsoleView view = new ConsoleView();
        ProductController controller = new ProductController(product, view);
        controller.showProduct();
    }
}
```

### 6. ProductTest.java (Unit Test JUnit)

```java
package com.upb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;

public class ProductTest {

    @Test
    public void testProductName() {
        Product p = new Product("P01", "Benih Jagung");
        assertEquals("Benih Jagung", p.getName());
    }
}
```

---

## Hasil Pengujian

- Aplikasi berhasil menampilkan informasi produk melalui pola MVC.
- Pengujian unit dengan JUnit berjalan sukses tanpa kegagalan.
- Hasil pengujian terdokumentasi dalam bentuk tangkapan layar pada folder
  
  `Screenshots`

---

## Pembahasan

Penggunaan Singleton Pattern membantu mengontrol akses terhadap resource bersama seperti koneksi database. Arsitektur MVC membuat struktur kode lebih modular dan mudah dikembangkan. Sementara itu, unit testing memberikan jaminan bahwa setiap komponen bekerja sesuai fungsinya dan mempermudah proses debugging.

---

## Kesimpulan

Berdasarkan praktikum yang telah dilakukan, dapat disimpulkan bahwa:

- Design pattern memberikan solusi terstruktur dalam perancangan aplikasi.
- Singleton efektif digunakan untuk pengelolaan objek global.
- MVC meningkatkan keterbacaan dan pemeliharaan kode.
- Unit testing berperan penting dalam menjaga kualitas perangkat lunak.
---

## Quiz

### 1. Mengapa constructor pada Singleton harus bersifat private?

  **Jawaban:**  
    Untuk mencegah pembuatan objek secara langsung dari luar class.

### 2. Jelaskan manfaat pemisahan Model, View, dan Controller.

**Jawaban:**  
Pemisahan Model, View, dan Controller membuat kode lebih terstruktur, mudah dipelihara, dan setiap bagian memiliki tugas yang jelas sehingga pengembangan dan pengujian aplikasi menjadi lebih mudah.

### 3. Apa peran unit testing dalam menjaga kualitas perangkat lunak?

**Jawaban:**  
Unit testing berperan untuk memastikan setiap bagian kecil kode berjalan sesuai fungsi yang diharapkan serta membantu mendeteksi kesalahan sejak dini sehingga kualitas perangkat lunak tetap terjaga.

### 4. Apa risiko jika Singleton tidak diimplementasikan dengan benar?

**Jawaban:**  
dapat terbentuk lebih dari satu instance objek sehingga menyebabkan inkonsistensi data, konflik resource, dan perilaku aplikasi yang tidak terduga.
