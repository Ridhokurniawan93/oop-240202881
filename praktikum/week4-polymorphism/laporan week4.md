# Laporan Praktikum Minggu 4
Topik: Polymorphism (Info Produk)

## Identitas
- Nama  : Ridho Kurniawan
- NIM   : 240202881
- Kelas : 3IKRB

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep polymorphism** dalam OOP.  
- Mahasiswa mampu **membedakan method overloading dan overriding**.  
- Mahasiswa mampu **mengimplementasikan polymorphism (overriding, overloading, dynamic binding)** dalam program.  
- Mahasiswa mampu **menganalisis contoh kasus polymorphism** pada sistem nyata (Agri-POS).  

---

## Dasar Teori
Polymorphism berarti “banyak bentuk” dan memungkinkan objek yang berbeda merespons panggilan method yang sama dengan cara yang berbeda.  
1. **Overloading** → mendefinisikan method dengan nama sama tetapi parameter berbeda.  
2. **Overriding** → subclass mengganti implementasi method dari superclass.  
3. **Dynamic Binding** → pemanggilan method ditentukan saat runtime, bukan compile time.  

Dalam konteks Agri-POS, misalnya:  
- Method `getInfo()` pada `Produk` dioverride oleh `Benih`, `Pupuk`, `AlatPertanian` untuk menampilkan detail spesifik.  
- Method `tambahStok()` bisa dibuat overload dengan parameter berbeda (int, double).  

---

## Langkah Praktikum
1. **Overloading**  
   - Tambahkan method `tambahStok(int jumlah)` dan `tambahStok(double jumlah)` pada class `Produk`.  

2. **Overriding**  
   - Tambahkan method `getInfo()` pada superclass `Produk`.  
   - Override method `getInfo()` pada subclass `Benih`, `Pupuk`, dan `AlatPertanian`.  

3. **Dynamic Binding**  
   - Buat array `Produk[] daftarProduk` yang berisi objek `Benih`, `Pupuk`, dan `AlatPertanian`.  
   - Loop array tersebut dan panggil `getInfo()`. Perhatikan bagaimana Java memanggil method sesuai jenis objek aktual.  

4. **Main Class**  
   - Buat `MainPolymorphism.java` untuk mendemonstrasikan overloading, overriding, dan dynamic binding.  

5. **CreditBy**  
   - Tetap panggil `CreditBy.print("<NIM>", "<Nama>")`.  

6. **Commit dan Push**  
   - Commit dengan pesan: `week4-polymorphism`.  

---

## Kode Program

```java
  package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.AlatPertanian;
import main.java.com.upb.agripos.model.Benih;
import main.java.com.upb.agripos.model.ObatTanaman;
import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.model.Pupuk;
import main.java.com.upb.agripos.util.CreditBy; 

public class MainPolymorphism {
    public static void main(String[] args) {
        
        System.out.println("=== WEEK4 POLYMORPHISM ===");
        
        System.out.println("\n--- Overloading (tambahStok) ---");
        Produk produkUmum = new Produk("PRD001", "Jagung", 300000, 150);

        System.out.print("Panggilan INT: ");
        produkUmum.tambahStok(50); 
        
        System.out.print("Panggilan DOUBLE: ");
        produkUmum.tambahStok(14.8); 

        
        System.out.println("\n--- Overriding (getInfo) & Dynamic Binding ---");
        
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 150, "IR64");
        

        Pupuk p_urea = new Pupuk("PPK-101", "Pupuk Urea", 350000,20, "Anorganik");
        Pupuk p_granul = new Pupuk("PPK-102", "Pupuk Granul", 550000, 50, "Organik"); 

    
        AlatPertanian a_baja = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 25, "Baja");
        AlatPertanian a_kayu = new AlatPertanian("ALT-502", "Garu", 20000, 25, "Kayu");
        AlatPertanian a_plastik = new AlatPertanian("ALT-503", "Mulsa", 750000, 20, "Plastik");

        ObatTanaman o_tanaman = new ObatTanaman("OBT1", "Gramaxon", 45000, 25, "Cair"); 
        
        Produk[] daftarProduk = {
            b, 
            p_urea, 
            p_granul,
            a_baja,
            a_kayu,
            a_plastik, 
            o_tanaman, 
            produkUmum 
        };
        
        System.out.println("\nHasil getInfo() melalui array Produk[]:");
        for (Produk p : daftarProduk) {
            
            p.getInfo(); 
        }

        
        CreditBy.print("240202881", "Ridho Kurniawan"); 
    }
}
```
---

## Hasil Eksekusi
<img width="475" height="917" alt="Cuplikan layar 2025-10-29 171323" src="https://github.com/user-attachments/assets/fe9b79c3-ee2e-40b3-9cfd-291f0949d169" />

---

## Analisis
 - Cara Kerja Program: Ketika method getInfo() dipanggil dalam perulangan untuk setiap elemen di array Produk[], Java memanfaatkan dynamic binding (atau late binding). Ini berarti, meskipun pemanggilan dilakukan melalui      referensi tipe Produk, Java akan mengeksekusi implementasi getInfo() yang sesuai dengan tipe objek aktual (misalnya Benih, Pupuk) pada saat runtime, bukan berdasarkan tipe referensi yang terlihat (Produk).
- Perbedaan dengan Minggu Sebelumnya (Inheritance): Minggu sebelumnya fokus pada Inheritance (Pewarisan), di mana subclass  mewarisi atribut dan method dari superclass. Pada minggu ini, fokus beralih ke Polymorphism (Bentuk Banyak), di mana objek tidak hanya mewarisi, tetapi juga berperilaku berbeda (melalui Method Overriding) meskipun diakses melalui antarmuka superclass yang sama.
- Kendala:  saya terjadi kebingungan karena saat objek subclass dibuat, constructor superclass harus dipanggil terlebih dahulu secara implisit (atau eksplisit melalui super()). Namun, logika constructor subclass tidak dapat menggantikan logika constructor superclass.


---

## Kesimpulan
Praktikum ini menegaskan bahwa Polymorphism adalah konsep sentral dalam OOP yang sangat penting untuk mencapai desain perangkat lunak yang fleksibel, efisien, dan mudah dikembangkan

---

## Quiz
1. Apa perbedaan overloading dan overriding?
   **Jawaban:** Overloading (Static Polymorphism) terjadi di dalam satu kelas ketika terdapat dua atau lebih method dengan nama yang sama tetapi memiliki daftar parameter yang berbeda (jumlah, tipe, atau urutan), dan method yang akan dieksekusi ditentukan pada saat compile-time (Static Binding). Sedangkan Overriding (Dynamic Polymorphism) terjadi antar superclass dan subclass, di mana subclass mengganti implementasi method yang diwarisi dengan signature yang sama persis, dan method yang dijalankan ditentukan pada saat runtime (Dynamic Binding), berdasarkan tipe objek aktual yang dirujuk.
  
2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?
   **Jawaban:** Java menentukan method yang dipanggil berdasarkan tipe objek aktual (objek yang dibuat dengan new) yang dirujuk oleh variabel, bukan berdasarkan tipe referensi variabelnya.

3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.
   **Jawaban:**
   Sistem Pembayaran Digital:
Dalam sistem pembayaran, class MetodePembayaran dapat menjadi superclass. Subclass-nya dapat mencakup KartuKredit, TransferBank, dan DompetDigital.
•	Masing-masing subclass dapat meng-override method standar seperti prosesPembayaran(jumlah).
•	KartuKredit.prosesPembayaran() akan mencakup validasi batas kredit.
•	TransferBank.prosesPembayaran() akan melibatkan pengecekan kode virtual account dan konfirmasi bank.
•	DompetDigital.prosesPembayaran() akan melakukan verifikasi PIN atau sidik jari.
Ketika sistem memanggil fungsi prosesPembayaran(totalHarga) melalui referensi MetodePembayaran, Java akan secara dinamis menjalankan prosedur yang spesifik sesuai dengan jenis metode pembayaran yang dipilih oleh pengguna.



