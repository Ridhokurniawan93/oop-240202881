# Laporan Praktikum Minggu 6
Topik: week 6 UML SOLID

## Identitas
- Nama  :  Ridho Kurniawan
- NIM   : 240202881
- Kelas : 3IKRB

---
## Tujuan Pembelajaran

Mahasiswa mampu:

1. Mahasiswa mampu mengidentifikasi kebutuhan sistem ke dalam diagram UML.
2. Mahasiswa mampu menggambar UML Class Diagram dengan relasi antar class yang tepat.
3. Mahasiswa mampu menjelaskan prinsip desain OOP (SOLID).
4. Mahasiswa mampu menerapkan minimal dua prinsip SOLID dalam kode program.

---

## Deskripsi Tugas

Buatlah **desain arsitektur sistem *Agri-POS*** menggunakan **UML** (minimal empat diagram) dan penerapan **prinsip SOLID**, untuk memenuhi **kebutuhan sistem** yang dijelaskan di bawah ini.
Anda **diperbolehkan menggunakan tools apa pun** (Graphviz, StarUML, draw.io, PlantUML, Lucidchart, dan lain-lain), asalkan hasil akhir disimpan dan dikumpulkan dalam format **`.png` atau `.pdf`**, bisa dikumpulkan beserta file sumbernya (`.dot`, `.uml`, `.drawio`, dan sebagainya).

## Diagram UML

### 1. Use Case Diagram Agripos
Menjelaskan fitur-fitur utama yang digunakan oleh Admin dan Kasir.

## Admin dapat:

- Login & Logout

- Kelola produk

    - Tambah produk

    - Hapus produk

    - Daftar produk

    - Update produk

-  Validasi data produk

-  Lihat laporan penjualan
 
## Kasir dapat:

- Buat transaksi

- Tambah produk ke keranjang

- Pilih metode pembayaran

  -  Pembayaran tunai

  -  Pembayaran E-wallet

- Hitung total pembayaran

- Cetak struk

**Kesimpulannya**: menggambarkan siapa (aktor) bisa melakukan apa (use case) di dalam sistem.
### 2. Activity Diagram (Proses Checkout Agripos)
Diagram ini menunjukkan alur kerja kasir saat melakukan transaksi.

## Alurnya:

1. Kasir input produk.

2. Sistem **cek** stok →

    -  Jika stok cukup, lanjut.

    -  Jika tidak, transaksi gagal.

3. Sistem hitung total + cek apakah ada **diskon/promo.**

4. Kasir pilih metode bayar:

    -  **Tunai** → kasir input uang → sistem hitung kembalian.

    -  **E-wallet/Kartu** → payment gateway cek saldo.

           -  Jika saldo cukup → berhasil.

           -  Jika saldo kurang → gagal.

5. Sistem mencetak struk → transaksi selesai.

**Kesimpulannya**: diagram ini menunjukkan proses checkout lengkap dari input barang sampai pembayaran dan cetak struk.

### 3. Class Diagram (Struktur OOP Agripos)
Diagram ini menjelaskan **komponen kelas dalam sistem**, hubungan antar kelas, dan penerapan prinsip OOP + SOLID.

**Inti strukturnya:**

-  **Product, CartItem, Transaction** → data utama.

-  **Service layer:**

      -  `ProductService`  = mengelola produk.

      -  `CheckoutService` = proses checkout.

      -  `PaymentService`  = mengatur pembayaran.

 -  **Repository layer** (akses database):

      -  `ProductRepository`

      -  `TransactionRepository`

 -  **PaymentMethod** (Interface):

      -  `CashPayment`

      -  `EWalletPayment`

    **Receipt** untuk cetak struk.

**Kesimpulannya**: diagram ini menunjukkan bagaimana sistem dibangun secara OOP dan memisahkan logika (service), data (entity), dan database (repository).

### 4. Sequence Diagram (Pembayaran Via E-Wallet)
Menjelaskan **urutan pesan (interaksi)** antar komponen waktu kasir memilih e-wallet.

**Alurnya:**

1. Kasir pilih **E-wallet.**

2. OrderService ambil total belanja.

3. PaymentService membuat permintaan pembayaran.

4. E-wallet Gateway mengecek saldo.

5. Jika saldo cukup:

    -  Order difinalisasi

    -  Sistem update stok

    -  Berhasil → cetak struk

6. Jika saldo tidak cukup:

    -  Order dibatalkan

    -  Gagal → tampilkan pesan saldo kurang

**Kesimpulannya**: menggambarkan interaksi real-time antar UI → Service → Gateway saat proses pembayaran berlangsunx. 


## Contoh Penerapan Prinsip SOLID pada Sistem Agripos

| **Prinsip**                             | **Contoh Penerapan pada Sistem Agripos**                                                                                                                                  |
| --------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **S — Single Responsibility Principle** | Kelas **Transaction** hanya bertanggung jawab menghitung total belanja, sedangkan **PaymentService** hanya mengurus proses pembayaran.                                    |
| **O — Open/Closed Principle**           | Sistem dapat menambahkan metode pembayaran baru (misalnya QRIS, Debit) **tanpa mengubah kode lama**, cukup menambahkan kelas PaymentMethod baru.                          |
| **L — Liskov Substitution Principle**   | Semua kelas yang mengimplementasi **PaymentMethod** (CashPayment, EWalletPayment, dll.) dapat digunakan saling menggantikan tanpa mempengaruhi fungsi sistem.             |
| **I — Interface Segregation Principle** | Interface dipisah sesuai kebutuhan, misalnya interface khusus **CashPayment** dan interface **EWalletPayment**, sehingga kelas tidak terbebani metode yang tidak dipakai. |
| **D — Dependency Inversion Principle**  | Service seperti **PaymentService** bergantung pada **interface PaymentMethod**, bukan pada kelas konkrit, sehingga lebih fleksibel dan mudah diuji.                       |


---

## Hasil Eksekusi
<img width="802" height="925" alt="Use case Diagram drawio" src="https://github.com/user-attachments/assets/33f8caa3-c699-44dc-a8c4-5614c91c507f" />
<img width="1074" height="1093" alt="Activity Diagram drawio" src="https://github.com/user-attachments/assets/90662af3-8018-4e9b-b0be-183499b11903" />
<img width="1723" height="1562" alt="Class Diagram drawio" src="https://github.com/user-attachments/assets/47a92932-ae15-49a5-b72b-c11e73e62cfc" />
<img width="1821" height="1369" alt="Sequence Diagram drawio" src="https://github.com/user-attachments/assets/396f9dad-2230-4720-8190-d80f8cbe02d6" />


---

## Kesimpulan Analisis

Desain UML Agri-POS dapat memenuhi seluruh FR dan NFR jika empat diagram dibuat dengan alur yang jelas, konsisten antar diagram, serta menampilkan prinsip SOLID secara eksplisit, terutama pada modul pembayaran.
Bagian paling penting adalah:

        -   Sequence Diagram harus memuat alt block untuk error.

        -   Class Diagram harus menampilkan interface dan dependency injection.

        -   Activity Diagram harus lengkap dengan percabangan normal/gagal.

        -   Use Case harus mencakup semua FR dengan include/extend.

Jika semua dipenuhi, desain dianggap komprehensif, maintainable, dan extensible sesuai rubric 40%+40%.

## **Kendala umum:**
- Harus menyeragamkan notasi dan penamaan UML 
- Jangan menggambarkan alur alternatif secara ringkas


## Quiz

1. **Jelaskan perbedaan aggregation dan composition serta contoh penerapannya.**  
**Jawaban:**  
- *Aggregation* adalah hubungan **memiliki** yang longgar antara dua kelas.Objek bagian dapat tetap hidup meskipun objek induknya dihapus.

  Contoh: Universitas — Mahasiswa→ Mahasiswa tetap ada meskipun universitas tidak ada dalam model tersebut. 

- *Composition* hubungan “memiliki” yang kuat.Objek bagian tidak dapat hidup tanpa objek induknya.

  Contoh: Rumah — Kamar→ Jika rumah dihancurkan, kamarnya juga tidak ada.

2. **Bagaimana prinsip Open/Closed memastikan sistem mudah dikembangkan?**  
**Jawaban:**  
Prinsip Open/Closed membuat sistem mudah dikembangkan karena fitur baru bisa ditambahkan tanpa mengubah kode lama, sehingga sistem menjadi lebih stabil, fleksibel, dan aman dari potensi error.

3. **Mengapa Dependency Inversion Principle meningkatkan testability?**  
**Jawaban:**  
Dependency Inversion Principle membuat kode lebih mudah diuji karena ketergantungan bisa diganti-ganti tanpa memodifikasi logika utama.

