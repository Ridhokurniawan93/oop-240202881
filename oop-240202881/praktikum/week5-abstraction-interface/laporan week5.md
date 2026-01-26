# Laporan Praktikum Minggu 5
Topik: Abstraction (Abstract Class & Interface)

## Identitas
- Nama  : Ridho Kurniawan
- NIM   : 240202881
- Kelas : 3IKRB

---

## Tujuan
- Mahasiswa mampu **menjelaskan perbedaan abstract class dan interface**.
- Mahasiswa mampu **mendesain abstract class dengan method abstrak** sesuai kebutuhan kasus.
- Mahasiswa mampu **membuat interface dan mengimplementasikannya pada class**.
- Mahasiswa mampu **menerapkan multiple inheritance melalui interface** pada rancangan kelas.
- Mahasiswa mampu **mendokumentasikan kode** (komentar kelas/method, README singkat pada folder minggu).

---

## Dasar Teori
**Abstraksi** adalah proses menyederhanakan kompleksitas dengan menampilkan elemen penting dan menyembunyikan detail implementasi.
- **Abstract class**: tidak dapat diinstansiasi, dapat memiliki method abstrak (tanpa badan) dan non-abstrak. Dapat menyimpan state (field).
- **Interface**: kumpulan kontrak (method tanpa implementasi konkret). Sejak Java 8 mendukung default method. Mendukung **multiple inheritance** (class dapat mengimplementasikan banyak interface).
- Gunakan **abstract class** bila ada _shared state_ dan perilaku dasar; gunakan **interface** untuk mendefinisikan kemampuan/kontrak lintas hierarki.

Dalam konteks Agri-POS, **Pembayaran** dapat dimodelkan sebagai abstract class dengan method abstrak `prosesPembayaran()` dan `biaya()`. Implementasi konkritnya: `Cash`, `EWallet`, dan `Transfer Bank`. Kemudian, interface seperti `Validatable` (mis. verifikasi OTP) dan `Receiptable` (mencetak bukti) dapat diimplementasikan oleh jenis pembayaran yang relevan.

---

## Langkah Praktikum
1. **Abstract Class – Pembayaran**
  - Buat `Pembayaran` (abstract) dengan field `invoiceNo`, `total` dan method:
     - `double biaya()` (abstrak) → biaya tambahan (fee).
     - `boolean prosesPembayaran()` (abstrak) → mengembalikan status berhasil/gagal.
     - `double totalBayar()` (konkrit) → `return total + biaya();`.

2. **Subclass Konkret**
   - `Cash` → biaya = 0, proses = selalu berhasil jika `tunai >= totalBayar()`.
   - `EWallet` → biaya = 1.5% dari `total`; proses = membutuhkan validasi.
   - `TransferBank` → biaya tetap Rp3.500, proses dianggap selalu berhasil.

3. **Interface**
   - `Validatable` → `boolean validasi();` (contoh: OTP).
   - `Receiptable` → `String cetakStruk();`

4. **Multiple Inheritance via Interface**
   - `EWallet` mengimplementasikan **dua interface**: `Validatable`, `Receiptable`.
   - `Cash` dan `TransferBank` mengimplementasikan `Receiptable`.

5. **Main Class**
    - Buat `MainAbstraction.java` untuk mendemonstrasikan pemakaian `Pembayaran` (polimorfik).
    - Tampilkan hasil proses dan struk. Di akhir, panggil `CreditBy.print("[NIM]", "[Nama]")`.

6. **Commit dan Push**
   - Commit dengan pesan: `week5-abstraction-interface`.

---

## Kode Program

```java
  package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.pembayaran.*;
import main.java.com.upb.agripos.model.kontrak.*;
import main.java.com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        pembayaran cash = new Cash("INV-001", 100000, 120000);
        pembayaran ew = new EWallet("INV-002", 150000, "user@ewallet", "123456");
        pembayaran tb = new TransferBank("INV-003", 200000, "Bank WTC", "123-456-7890");

        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());
        System.out.println(((Receiptable) tb).cetakStruk());
        
        CreditBy.print("240202881", "Ridho Kurniawan");
    }
}
```
---

## Hasil Eksekusi
<img width="1502" height="207" alt="Cuplikan layar 2025-11-07 202207" src="https://github.com/user-attachments/assets/e0eb0f29-9097-42a5-813b-838a130c34b6" />

---

## Analisis
Pada praktikum minggu ke-5 ini, konsep utama yang diterapkan adalah abstraction menggunakan abstract class dan interface.
Abstraksi memungkinkan pemisahan antara definisi perilaku (kontrak) dan implementasi konkret, sehingga desain program menjadi modular dan mudah diperluas.
Kelas Pembayaran dijadikan sebagai abstract class karena semua jenis pembayaran memiliki struktur dan atribut dasar yang sama (invoiceNo, total, biaya(), prosesPembayaran()), namun implementasinya berbeda.
Sementara itu, interface seperti Validatable dan Receiptable berfungsi untuk menambahkan kemampuan spesifik seperti validasi OTP dan pencetakan struk tanpa mengganggu hierarki utama.

   - **Cara Kerja Program:**  
 1.  Abstract Class Pembayaran:
    
      Mendefinisikan kerangka dasar pembayaran dengan method abstrak biaya() dan prosesPembayaran(), serta method konkret totalbayar()

2.  Subclass Konkret:
   
    -	Cash: Tidak memiliki biaya tambahan (biaya = 0), dan pembayaran berhasil jika tunai cukup.

    -	EWallet: Memiliki biaya 1,5% dan membutuhkan validasi (implementasi Validatable).

    -	TransferBank: Biaya tetap Rp3.500, dan dianggap selalu berhasil.

3.  Interface:

    -	Validatable: Digunakan untuk memvalidasi pembayaran digital (contohnya OTP).

    -	Receiptable: Digunakan untuk mencetak struk pembayaran.

4.  Multiple Inheritance:
   
    -	EWallet mengimplementasikan dua interface (Validatable dan Receiptable).

    -	Cash dan TransferBank hanya mengimplementasikan Receiptable.

5.  Kelas MainAbstraction:
    
    -	Membuat tiga objek pembayaran (Cash, EWallet, TransferBank).

    -	Memanggil method cetakStruk() melalui referensi polymorphic (Pembayaran).

    -	Menampilkan hasil transaksi dan identitas pembuat dengan CreditBy.print().



-   ### Perbedaan dengan Minggu Sebelumnya

| Aspek | Minggu 4 (Polymorphism) | Minggu 5 (Abstraction & Interface) |
|:--|:--|:--|
| **Fokus Konsep** | Overriding dan Overloading untuk menunjukkan perilaku berbeda pada subclass | Pemisahan antara kontrak perilaku dan implementasinya |
| **Tujuan** | Menunjukkan bahwa objek berbeda dapat memiliki method yang sama tetapi hasil berbeda | Menunjukkan bagaimana merancang struktur kelas yang fleksibel dan dapat diperluas |
| **Struktur** | Semua kelas konkret, tidak ada abstract class atau interface | Menggunakan kombinasi abstract class + interface |
| **Kelebihan Konsep** | Mempermudah penggunaan method yang sama untuk berbagai objek | Mempermudah desain sistem besar dengan peran dan tanggung jawab yang jelas |





- **Kendala dan Solusi:**  
  Kendala yang dihadapi adalah beberapa subclass belum mengimplementasikan semua method abstrak dan interface, serta format cetakStruk() yang tidak seragam. Solusinya dengan melengkapi seluruh implementasi method yang wajib dan menyamakan format output menggunakan template yang konsisten. Selain itu, kesalahan kecil pada perhitungan biaya diperbaiki dengan pengujian ulang tiap subclass
---

## Kesimpulan
Penggunaan **abstract class dan interface**, memungkinkan desain program yang. 
 -	 Lebih  **terstruktur dan fleksibel**.
 -	 Mudah **diperluas** tanpa mengubah kode yang sudah ada.
 -	 Mendukung **multiple inheritance** secara aman.

      Dalam kasus Agri-POS, pendekatan ini menjadikan sistem pembayaran modular, di mana setiap metode pembayaran dapat memiliki logika sendiri, namun tetap mengikuti kontrak umum dari Pembayaran.

---

## Quiz
1. Jelaskan perbedaan konsep dan penggunaan **abstract class** dan **interface**.  
   **Jawaban:**  Abstract class digunakan ketika ada perilaku dasar yang sama dan sebagian implementasi sudah diketahui.
Interface digunakan untuk mendefinisikan kontrak atau kemampuan tambahan lintas kelas tanpa membawa state.

2. Mengapa **multiple inheritance** lebih aman dilakukan dengan interface pada Java?  
   **Jawaban:** Karena interface tidak menyimpan state atau implementasi konkret, sehingga tidak menimbulkan konflik pewarisan seperti pada class ganda.

3. Pada contoh Agri-POS, bagian mana yang **paling tepat** menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.  
   **Jawaban:** Pembayaran → **abstract class** karena memiliki atribut dan perilaku dasar bersama.
   
   Validatable dan Receiptable →**interface** karena hanya mendefinisikan kontrak tambahan (validasi dan cetak struk) yang dapat digunakan oleh berbagai jenis pembayaran.
