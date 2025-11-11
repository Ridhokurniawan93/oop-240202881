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
Mendefinisikan kerangka dasar pembayaran dengan method abstrak biaya() dan prosesPembayaran(), serta method konkret totalBayar().

2.  Subclass Konkret:
•	Cash: Tidak memiliki biaya tambahan (biaya = 0), dan pembayaran berhasil jika tunai cukup.
•	EWallet: Memiliki biaya 1,5% dan membutuhkan validasi (implementasi Validatable).
•	TransferBank: Biaya tetap Rp3.500, dan dianggap selalu berhasil.

3.  Interface:
•	Validatable: Digunakan untuk memvalidasi pembayaran digital (contohnya OTP).
•	Receiptable: Digunakan untuk mencetak struk pembayaran.

4.  Multiple Inheritance:
•	EWallet mengimplementasikan dua interface (Validatable dan Receiptable).
•	Cash dan TransferBank hanya mengimplementasikan Receiptable.

5.  Kelas MainAbstraction:
•	Membuat tiga objek pembayaran (Cash, EWallet, TransferBank).
•	Memanggil method cetakStruk() melalui referensi polymorphic (Pembayaran).
•	Menampilkan hasil transaksi dan identitas pembuat dengan CreditBy.print().



- **Perbedaan dengan Minggu Sebelumnya:**  
 | Aspek | Minggu 4 (Polymorphism) | Minggu 5 (Abstraction & Interface) |
|:--|:--|:--|
| **Fokus Konsep** | Overriding dan Overloading untuk menunjukkan perilaku berbeda pada subclass | Pemisahan antara kontrak perilaku dan implementasinya |
| **Tujuan** | Menunjukkan bahwa objek berbeda dapat memiliki method yang sama tetapi hasil berbeda | Menunjukkan bagaimana merancang struktur kelas yang fleksibel dan dapat diperluas |
| **Struktur** | Semua kelas konkret, tidak ada abstract class atau interface | Menggunakan kombinasi abstract class + interface |
| **Kelebihan Konsep** | Mempermudah penggunaan method yang sama untuk berbagai objek | Mempermudah desain sistem besar dengan peran dan tanggung jawab yang jelas |




- **Kendala dan Solusi:**  
  Kendala utama adalah memastikan setiap subclass mengimplementasikan semua method abstrak dan kontrak interface dengan konsisten. Selain itu, diperlukan penyesuaian agar output `cetakStruk()` memiliki format seragam. Kendala tersebut diatasi dengan mendefinisikan template format yang sama di setiap subclass serta melakukan uji eksekusi untuk memverifikasi hasil.
---

## Kesimpulan
Dengan menggunakan **abstract class dan interface**, kode menjadi lebih fleksibel, terstruktur, dan mudah dikembangkan. Penambahan fitur baru seperti `TransferBank` dapat dilakukan tanpa memodifikasi kelas dasar.

---

## Quiz
1. Jelaskan perbedaan konsep dan penggunaan **abstract class** dan **interface**.  
   **Jawaban:** Abstract class digunakan untuk mendefinisikan perilaku dasar dengan kemungkinan memiliki implementasi, sedangkan interface hanya mendefinisikan kontrak perilaku tanpa implementasi konkret.

2. Mengapa **multiple inheritance** lebih aman dilakukan dengan interface pada Java?  
   **Jawaban:** Karena interface tidak membawa state, sehingga tidak menimbulkan konflik pewarisan ganda seperti pada class.

3. Pada contoh Agri-POS, bagian mana yang **paling tepat** menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.  
   **Jawaban:** `Pembayaran` menjadi abstract class karena memiliki state dan perilaku dasar bersama, sedangkan `Validatable` dan `Receiptable` menjadi interface karena hanya mendefinisikan kontrak fungsi tambahan lintas tipe pembayaran.
