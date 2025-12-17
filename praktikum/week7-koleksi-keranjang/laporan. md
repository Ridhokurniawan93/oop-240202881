# Laporan Praktikum Minggu 7

Topik: **Collections dan Implementasi Keranjang Belanja**

## Identitas

* Nama  : Ridho Kurniawan
* NIM   : 240202881
* Kelas : 3IKRB

---

## Tujuan

* Mahasiswa mampu memahami konsep dasar Java Collections (List, Map, Set).
* Mahasiswa mampu mengimplementasikan ArrayList untuk keranjang belanja.
* Mahasiswa mampu menggunakan Map untuk mengelola quantity produk.
* Mahasiswa mampu menampilkan isi keranjang dan menghitung total transaksi.


## Studi Kasus: Keranjang Belanja Agri-POS

Keranjang belanja harus dapat:

- Menambahkan produk
- Menghapus produk
- Menampilkan isi keranjang
- Menghitung total nilai transaksi
- Menangani jumlah (quantity) menggunakan Map

Kasus ini mencerminkan penggunaan struktur data dalam aplikasi nyata seperti POS.

---

## Dasar Teori

1. **List** menyimpan data terurut dan dapat duplikat (contoh: ArrayList).
2. **Map** menyimpan data dalam bentuk pasangan *key–value*.
3. **Set** menyimpan elemen unik (tanpa duplikat).
4. ArrayList cocok untuk daftar item yang membutuhkan urutan.
5. HashMap cocok untuk data produk dengan quantity.

---

## Langkah Praktikum

1. Membuat class `Product` dengan atribut code, name, dan price.
2. Membuat class `ShoppingCart` menggunakan ArrayList.
3. Membuat class `ShoppingCartMap` menggunakan HashMap untuk quantity.
4. Membuat class `MainCart` untuk menjalankan program.
5. Melakukan compile dan run.
6. Melakukan commit dan push dengan format:

   `week7-collections: implementasi shopping cart`

---

## Kode Program

```java
package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {

        System.out.println("Hello, I am Ridho Kurniawan-240202881 (Week7)");
        System.out.println("==============================================");

        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);

        ShoppingCartMap cart = new ShoppingCartMap();

        // --- TAMBAH PRODUK ---
        System.out.println("\n--- MENAMBAHKAN PRODUK KE KERANJANG ---");
        cart.addProduct(p1);
        System.out.println("Produk ditambahkan: " + p1.getName() + " (Qty 1)");

        cart.addProduct(p2);
        System.out.println("Produk ditambahkan: " + p2.getName() + " (Qty 1)");

        cart.addProduct(p2);
        System.out.println("Produk ditambahkan: " + p2.getName() + " (Qty 2)");

        System.out.println("\nISI KERANJANG SAAT INI:");
        cart.printCart();

        // --- HAPUS PRODUK ---
        System.out.println("\n--- MENGHAPUS PRODUK ---");
        cart.removeProduct(p2);
        System.out.println("1 qty Pupuk dihapus");

        System.out.println("\nISI KERANJANG SETELAH HAPUS:");
        cart.printCart();
    }
}

```

---

## Hasil Eksekusi

<img width="340" height="409" alt="Cuplikan layar 2025-12-12 220626" src="https://github.com/user-attachments/assets/a9424248-4d91-4046-978a-7ab010c4ba20" />

---

## Analisis

* Java Collections Framework mempermudah pengelolaan data keranjang belanja secara dinamis dan terstruktur.

* ArrayList efektif digunakan untuk keranjang belanja sederhana karena mendukung penambahan dan penghapusan data dengan mudah.

* ArrayList memiliki keterbatasan dalam mengelola quantity produk karena memungkinkan duplikasi data.

* HashMap lebih efisien untuk mengelola produk beserta quantity karena menggunakan konsep key–value.
  
* **Kendala** : Masih terdapat kesulitan dalam memahami konsep **key–value** pada struktur data Map dibandingkan List.
---

## Kesimpulan

Java Collections Framework efektif untuk mengelola data dinamis seperti keranjang belanja. **ArrayList** cocok untuk kasus sederhana tanpa quantity, sedangkan **HashMap** lebih tepat untuk pengelolaan data berbasis **key–value**. Pemilihan struktur data yang tepat meningkatkan efisiensi dan kejelasan logika program serta menjadi bekal penting dalam pengembangan aplikasi yang lebih kompleks.

## Quiz

1. **Jelaskan perbedaan antara List, Map, dan Set.**

   **Jawaban:** **List** menyimpan elemen secara berurutan dan memperbolehkan data duplikat, **Map** menyimpan data dalam pasangan key–value, sedangkan **Set** hanya menyimpan elemen unik tanpa duplikasi.
   
3. **Mengapa ArrayList cocok digunakan untuk keranjang belanja sederhana?**

   **Jawaban:** ArrayList cocok digunakan untuk keranjang belanja sederhana karena menyimpan data secara berurutan, mendukung penambahan dan penghapusan item dengan mudah
   
5. **Bagaimana struktur Set mencegah duplikasi data?**

   **Jawaban:** Struktur Set mencegah duplikasi data dengan tidak mengizinkan elemen yang sama disimpan lebih dari satu kali

4. **Kapan sebaiknya menggunakan **Map** dibandingkan List? Berikan contoh.**
      
   **Jawaban:** Map sebaiknya digunakan ketika data memiliki hubungan key–value dan setiap key harus unik, serta ketika diperlukan akses data yang cepat berdasarkan key.

   **Contoh:**
   Pada keranjang belanja yang membutuhkan quantity produk, Map digunakan untuk menyimpan pasangan produk sebagai key dan jumlah sebagai value. Dengan Map, produk yang sama tidak akan terduplikasi, melainkan                  quantity-nya yang bertambah.
