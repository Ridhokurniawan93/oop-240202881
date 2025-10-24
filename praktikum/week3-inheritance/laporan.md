# Laporan Praktikum Minggu 3
Topik: Inheritance (Kategori Produk)

## Identitas
- Nama  : Ridho Kurniawan
- NIM   : 240202881
- Kelas : 3IKRB

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep inheritance (pewarisan class)** dalam OOP.  
- Mahasiswa mampu **membuat superclass dan subclass** untuk produk pertanian.  
- Mahasiswa mampu **mendemonstrasikan hierarki class** melalui contoh kode.  
- Mahasiswa mampu **menggunakan `super` untuk memanggil konstruktor dan method parent class**.  
- Mahasiswa mampu **membuat laporan praktikum** yang menjelaskan perbedaan penggunaan inheritance dibanding class tunggal.  

---

## Dasar Teori
Inheritance adalah mekanisme dalam OOP yang memungkinkan suatu class mewarisi atribut dan method dari class lain.  
- **Superclass**: class induk yang mendefinisikan atribut umum.  
- **Subclass**: class turunan yang mewarisi atribut/method superclass, dan dapat menambahkan atribut/method baru.  
- `super` digunakan untuk memanggil konstruktor atau method superclass.  

---

## Langkah Praktikum
1. **Membuat Superclass Produk**  
   - Gunakan class `Produk` dari Bab 2 sebagai superclass.  

2. **Membuat Subclass**  
   - `Benih.java` → atribut tambahan: varietas.  
   - `Pupuk.java` → atribut tambahan: jenis pupuk (Urea, NPK, dll).  
   - `AlatPertanian.java` → atribut tambahan: material (baja, kayu, plastik).  

3. **Membuat Main Class**  
   - Instansiasi minimal satu objek dari tiap subclass.  
   - Tampilkan data produk dengan memanfaatkan inheritance.  

4. **Menambahkan CreditBy**  
   - Panggil class `CreditBy` untuk menampilkan identitas mahasiswa.  

5. **Commit dan Push**  
   - Commit dengan pesan: `week3-inheritance`.  

---

## Kode Program
```java
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 30, "IR64");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 65, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 20, "Baja");

        System.out.println("------------------------------------------");
        System.out.println(b.deskripsi());

        System.out.println("------------------------------------------");
        System.out.println(p.deskripsi());
        
        System.out.println("------------------------------------------");
        System.out.println(a.deskripsi());

        CreditBy.print("240202881", "Ridho Kurniawan");
```
---

## Hasil Eksekusi
<img width="1501" height="332" alt="Cuplikan layar 2025-10-24 211813" src="https://github.com/user-attachments/assets/8775c2a0-9495-4b92-8227-dac00e6e7105" />

---

## Analisis
 - Setiap subclass (Benih, Pupuk, dan AlatPertanian) mewarisi atribut dan method dari superclass Produk.
 - Subclass menambahkan atribut unik yang sesuai dengan jenis produk masing-masing.
 - Method deskripsi() memanfaatkan pewarisan untuk menampilkan data secara lengkap tanpa duplikasi kode.
 - Dengan inheritance, struktur program menjadi lebih modular dan reusable, dibandingkan dengan pendekatan class tunggal pada minggu sebelumnya.
 - Perubahan kecil pada superclass secara otomatis berpengaruh ke semua subclass, sehingga memudahkan pemeliharaan kode.

---

## Kesimpulan
-  Inheritance membuat kode lebih terorganisir, ringkas, dan mudah dikembangkan.
-  Subclass dapat menambahkan perilaku khusus tanpa perlu menulis ulang atribut dasar.
-  Konsep ini memperlihatkan keunggulan reusability (penggunaan ulang) dan extensibility (kemudahan pengembangan) dalam OOP.
-  Praktikum ini berhasil menunjukkan bagaimana hubungan hierarki antarclass bekerja secara efisien.


---

## Quiz
1. **Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?**  
   **Jawaban:** Keuntungan utamanya adalah efisiensi dan pemeliharaan kode.
     Atribut dan method umum cukup didefinisikan satu kali di superclass,
     sehingga menghindari duplikasi kode dan mempermudah pengembangan sistem. 

2. **Bagaimana cara subclass memanggil konstruktor superclass?**  
   **Jawaban:** Subclass dapat memanggil konstruktor superclass dengan menggunakan keyword super(parameter)

3. **Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.**  

   **Jawaban:** Subclass `Pestisida` dengan atribut tambahan seperti `bahanAktif` dan `dosisPenggunaan`.  Subclass dapat                    memanggil konstruktor superclass dengan menggunakan keyword super(parameter)
