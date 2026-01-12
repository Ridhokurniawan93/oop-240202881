# Laporan Praktikum Minggu9

Topik: Exception Handling, Custom Exception, dan Design Pattern Sederhana

## Identitas
- Nama  : Ridho Kurniawan
- NIM   : 240202881
- Kelas : 3IKRB

---

## Tujuan
Mahasiswa mampu:

1. Membedakan konsep error dan exception dalam pemrograman Java.
2. Mengimplementasikan mekanisme try–catch–finally dengan benar.
3. Membuat dan menggunakan custom exception sesuai kebutuhan aplikasi.
4. Menerapkan exception handling pada studi kasus keranjang belanja (Shopping Cart).
5. Memahami konsep dasar design pattern sederhana seperti Singleton dan MVC.

---

## Dasar Teori

## 1. Error dan Exception

Error merupakan kondisi kesalahan serius yang umumnya tidak dapat ditangani oleh program, misalnya kesalahan pada memori atau sistem.
Sebaliknya, exception adalah kondisi kesalahan yang terjadi saat runtime dan masih dapat ditangani oleh program agar aplikasi tetap berjalan dengan normal.

## 2. Try–Catch–Finally

Blok `try` digunakan untuk menuliskan kode yang berpotensi menimbulkan kesalahan.

Blok `catch` berfungsi untuk menangkap dan menangani exception tertentu.

Blok `finally` akan selalu dieksekusi, baik terjadi exception maupun tidak, sehingga cocok digunakan untuk proses pembersihan resource.

## 3. Custom Exception

Custom exception adalah exception buatan sendiri yang dibuat dengan mewarisi class Exception. Tujuannya adalah agar kesalahan yang muncul lebih spesifik dan mudah dipahami sesuai konteks bisnis aplikasi.

## 4. Singleton Pattern

Singleton Pattern digunakan untuk memastikan bahwa suatu class hanya memiliki satu instance selama aplikasi berjalan, sehingga data dapat terjaga konsistensinya.

## Langkah Praktikum

1. Membuat struktur folder dan package sesuai standar Java.
2. Membuat beberapa custom exception untuk validasi logika bisnis.
3. Membuat class Product sebagai model data.
4. Mengimplementasikan class ShoppingCart untuk proses transaksi.
5. Menggunakan try–catch pada class utama untuk menangani exception.

---

## Kode Program  

`EmptyStockException.java`

```java
package main.java.com.upb.agripos;

public class EmptyStockException extends Exception {

    public EmptyStockException(String message) {
        super(message);
    }
}

```


`InsufficientStockException.java`

```java
package main.java.com.upb.agripos;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) { super(msg); }
}

```


`InvalidPriceExcepption.java`

```java
package main.java.com.upb.agripos;

public class InvalidPriceException extends Exception {

    public InvalidPriceException(String message) {
        super(message);
    }
}

```


`InvalidQuantityException.java`

```java
package main.java.com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String msg) { super(msg); }
}
```

`MainExceptionDemo.java`

```java
package main.java.com.upb.agripos;

public class MainExceptionDemo {

    public static void main(String[] args) {

        System.out.println(
            "Hello, I am Ridho Kurniawan - 240202881 (Week 9)"
        );

        ShoppingCart cart = new ShoppingCart();

        
        try {
            Product p1 = new Product(
                "P01", "Pupuk Organik", -25000, 5
            );
            cart.addProduct(p1, 2);

        } catch (InvalidPriceException e) {
            System.out.println("Error Harga: " + e.getMessage());
        } catch (InvalidQuantityException e) {
            System.out.println("Error Quantity: " + e.getMessage());
        } finally {
            System.out.println("Validasi produk selesai\n");
        }
        
        
        try {
            Product p2 = new Product(
                "P02", "Bibit Padi", 15000, 10
            );
            cart.removeProduct(p2);

        } catch (InvalidPriceException e) {
            System.out.println("Error Harga: " + e.getMessage());
        } catch (ProductNotFoundException e) {
            System.out.println("Error Keranjang: " + e.getMessage());
        }

    
        try {
            Product p3 = new Product(
                "P03", "Pestisida", 30000, 2
            );
            cart.addProduct(p3, 5);
            cart.checkout();

        } catch (InvalidPriceException e) {
            System.out.println("Error Harga: " + e.getMessage());
        } catch (InvalidQuantityException e) {
            System.out.println("Error Quantity: " + e.getMessage());
        } catch (EmptyStockException e) {
            System.out.println("Error Keranjang: " + e.getMessage());
        } catch (InsufficientStockException e) {
            System.out.println("Error Stok: " + e.getMessage());
        } finally {
            System.out.println("Proses checkout selesai");
        }
    }
}

```

`Product.java`

```java
package main.java.com.upb.agripos;

import java.util.Objects;

public class Product {
    private final String code;
    private final String name;
    private final double price;
    private int stock;

    public Product(String code, String name, double price, int stock)
            throws InvalidPriceException {

        if (price <= 0) {
            throw new InvalidPriceException(
                "Harga produk harus lebih dari 0"
            );
        }

        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void reduceStock(int qty) {
        this.stock -= qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return code.equals(product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}


```

`ProductNotFoundException.java`

```java
package main.java.com.upb.agripos;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}

```

`ShoppingCart.java`

```java
package main.java.com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<Product, Integer> items = new HashMap<>();


    public void addProduct(Product product, int qty)
            throws InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                "Jumlah pembelian harus lebih dari 0"
            );
        }

        items.put(product, items.getOrDefault(product, 0) + qty);
    }

    
    public void removeProduct(Product product)
            throws ProductNotFoundException {

        if (!items.containsKey(product)) {
            throw new ProductNotFoundException(
                "Produk tidak ditemukan di dalam keranjang"
            );
        }

        items.remove(product);
    }

    
    public void checkout()
            throws EmptyStockException, InsufficientStockException {

        // 1. Keranjang kosong
        if (items.isEmpty()) {
            throw new EmptyStockException(
                "Keranjang belanja masih kosong"
            );
        }

        // 2. Validasi stok
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();

            if (product.getStock() < qty) {
                throw new InsufficientStockException(
                    "Stok tidak mencukupi untuk produk: "
                    + product.getName()
                );
            }
        }

        // 3. Kurangi stok jika semua valid
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }

        items.clear();
        System.out.println("Checkout berhasil ");
    }
}

```
---

## Hasil Eksekusi
  
<img width="1550" height="225" alt="Cuplikan layar 2026-01-02 110119" src="https://github.com/user-attachments/assets/883f07ac-8cfa-4b48-a93e-59f4ccdbed98" />


---

## Hasil Pengujian

Program berhasil menampilkan pesan kesalahan sesuai skenario yang diuji, seperti quantity tidak valid, produk tidak ditemukan, dan stok yang tidak mencukupi.

---
## Analisis

Penggunaan exception handling membuat alur program lebih terkontrol. Kesalahan dapat ditangani secara spesifik tanpa menyebabkan aplikasi berhenti secara tiba-tiba. Dibandingkan dengan validasi menggunakan if-else, pendekatan ini lebih terstruktur dan aman.

## Kesimpulan
Penerapan exception handling dan custom exception terbukti meningkatkan kualitas dan keandalan aplikasi. Program menjadi lebih mudah dipelihara karena kesalahan dapat diidentifikasi dan ditangani secara jelas sesuai konteks bisnis.

---

## Quiz

1. Apa perbedaan mendasar antara Checked Exception dan Unchecked Exception?

   Jawaban: 
   
   Checked exception harus ditangani pada saat kompilasi, sedangkan unchecked exception terjadi saat runtime dan tidak wajib ditangani secara eksplisit.
   
2. Kapan waktu yang tepat untuk menggunakan blok finally?
   
   Jawaban:

   Blok finally digunakan untuk menjalankan kode yang harus selalu dieksekusi, baik terjadi exception maupun tidak.
   
3. Mengapa Singleton Pattern berguna dalam aplikasi keranjang belanja?

   Jawaban:
 
   Singleton memastikan hanya ada satu objek keranjang belanja sehingga data transaksi tetap konsisten.

4. Berikan contoh kasus bisnis dalam POS yang membutuhkan custom exception.

    Jawaban:
  
    Produk Tidak Ditemukan
    
    Saat kasir mencoba menghapus atau memproses produk yang tidak ada di keranjang atau tidak terdaftar di sistem, transaksi harus dibatalkan.
    
    Kondisi ini dapat ditangani dengan `ProductNotFoundException`.
