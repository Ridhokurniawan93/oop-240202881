# Laporan Praktikum Minggu 1
Topik: "Pengenalan Paradigma dan Setup Proyek"

## Identitas
- Nama  : Risky Dimas Nugroho
- NIM   : 240202882
- Kelas : 3IKRB

---

## Tujuan
- Mahasiswa mampu mendefinisikan paradigma prosedural, OOP, dan fungsional.
- Mahasiswa mampu membandingkan kelebihan dan keterbatasan tiap paradigma.
- Mahasiswa mampu memberikan contoh program sederhana untuk masing-masing paradigma.

---

## Dasar Teori
Paradigma pemrograman adalah cara pandang dalam menyusun program:  
- **Prosedural**: program dibangun sebagai rangkaian perintah (fungsi/prosedur).  
- **OOP (Object-Oriented Programming)**: program dibangun dari objek yang memiliki data (atribut) dan perilaku (method).  
- **Fungsional**: program dipandang sebagai pemetaan fungsi matematika, lebih menekankan ekspresi dan transformasi data. 

---

## Langkah Praktikum
1. Menginstall JDK versi terbaru (25) dan cek instalasinya menggunakan perintah `java -version`  
2. Membuat file program `HelloProcedural.java`, `HelloOOP.java`, dan `HelloFunctional.java`.
3. Menulis kode sesuai paradigma yang diminta.
4. Melakukan eksekusi program
5. Mengecek hasil eksekusi di terminal.
6. Membuat commit ke repository GitHub dengan pesan commit `"week1-setup-hello-pos"`

---

## Kode Program
- Prosedural
```java
// HelloProcedural
public class HelloProcedural {
    public static void main(String[] args) {
        String nim = "240202882";
        String name = "Risky Dimas Nugroho";

        System.out.println("Hello World, I am " + name + " - " + nim);
    }
}
```

- OOP (Object-Oriented Programming)
```java
// HelloOOP
class Student {
    String nim;
    String name;
    Student(String nim, String name) {
        this.nim = nim;
        this.name = name;
    }

    void introduce() {
        System.out.println("Hello World, I am " + name + " - " + nim);
    }
}

public class HelloOOP {
    public static void main(String[] args) {
        Student s = new Student("240202882", "Risky Dimas Nugroho");
        s.introduce();
    }
}
```
- Fungsional
```java
// HelloFunctional
import java.util.function.BiConsumer;

public class HelloFunctional {
    public static void main(String[] args) {
        BiConsumer<String, String> introduce =
            (nim, name) -> System.out.println("Hello World, I am " + name + " - " + nim);

        introduce.accept("240202882","Risky Dimas Nugroho");
    }
}
```

---

## Hasil Eksekusi
- HelloProcedural
<img width="1912" height="1029" alt="HelloProcedural-240202882" src="https://github.com/user-attachments/assets/d6b0216a-bd6c-41e1-9354-4b5657ee0d78" />


- HelloOOP
<img width="1919" height="1036" alt="HelloOOP-240202882" src="https://github.com/user-attachments/assets/a09dd922-6e80-4974-a0ac-632a3cad06f3" />


- HelloFunctional
<img width="1914" height="1027" alt="HelloFunctional-240202882" src="https://github.com/user-attachments/assets/3d5782d2-d647-4c2e-b343-6b2d1b600000" />


---

## Analisis
- Cara kerja kode:
   - Pada paradigma prosedural, kode hanya berupa urutan instruksi tanpa class.
   - Pada paradigma OOP, kode dibungkus dalam class Student sehingga data (nim, name) dan perilaku (introduce()) disatukan.
   - Pada paradigma fungsional, digunakan lambda expression dan functional interface (BiConsumer) untuk mencetak pesan.
   
- Perbedaan dengan minggu sebelumnya:
   - Minggu ini mulai diperkenalkan perbedaan paradigma, tidak hanya menulis instruksi sederhana.
   - Pendekatan OOP membuat program lebih terstruktur, sedangkan functional membuat kode lebih ringkas.
- Kendala:
   - Awalnya bingung membedakan kapan harus pakai class vs lambda.
   - Solusi: membaca dokumentasi Java tentang java.util.function dan contoh penggunaan OOP sederhana. 

---

## Kesimpulan
- Prosedural fokus pada urutan instruksi (prosedur) dan sederhana untuk program kecil.
- OOP: Program terdiri dari objek (entitas yang punya data dan aksi). Pilihan terbaik untuk sistem besar dan kompleks seperti POS karena kode lebih terstruktur dan mudah diurus (maintainable).
- Fungsional: Program adalah transformasi data menggunakan fungsi. Cocok untuk memproses data banyak, karena kodenya ringkas dan aman untuk dieksekusi bersamaan (concurrent).

---

## Quiz
1. Apakah OOP selalu lebih baik dari prosedural?  
   **Jawaban:**
   Tidak selalu.OOP lebih baik untuk proyek besar dan kompleks (seperti sistem POS) karena kodenya lebih terstruktur dan mudah diubah (maintainable). Namun, untuk Prosedural lebih baik untuk program kecil atau skrip sederhana yang berfokus pada langkah-langkah berurutan (misalnya, menghitung rumus), karena lebih cepat dibuat dan dijalankan.

2. Kapan functional programming lebih cocok digunakan dibanding OOP  atau prosedural?    
   **Jawaban:**
   Functional programming lebih cocok digunakan saat:
   - Bekerja dengan operasi data yang intensif seperti filter, map, dan reduce (transformasi data koleksi).
   - Membutuhkan konkurensi (pemrosesan paralel) yang aman, karena imutabilitas data (immutable state) mengurangi race condition.
   - Diperlukan kode yang ringkas dan bug-free (karena fungsi murni lebih mudah diuji dan diverifikasi). 

3. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?  
   **Jawaban:**
   - **Prosedural:** Mudah dipahami untuk program kecil, tapi sulit di-*maintain* ketika aplikasi membesar karena logika dan data sering bercampur. Skalabilitas rendah.  
   - **OOP:** Lebih maintainable untuk sistem besar karena ada struktur class, encapsulation, inheritance, dan polymorphism. Skalabilitas tinggi karena mudah menambah fitur dengan objek baru.  
   - **Functional:** Maintainability tinggi untuk logika kompleks, karena fungsi murni lebih mudah diuji dan dirangkai ulang. Skalabilitas baik di sistem berbasis data atau yang membutuhkan *concurrency*. 

4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?  
**Jawaban:**
   Karena OOP sangat baik dalam memodelkan entitas dunia nyata yang kompleks.
   - Di POS, ada Produk, Transaksi, dan Pelanggan.
   - OOP memungkinkan Anda membuat objek untuk masing-masing entitas tersebut (misalnya, objek Produk memiliki data harga dan perilaku hitungStok()), membuat sistem lebih terorganisasi dan mudah diperluas di masa depan.

5. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (boilerplate code)?  
**Jawaban:**
  Dengan menggunakan fungsi siap pakai seperti map, filter, dan ekspresi lambda.
  - Daripada menulis loop (kode berulang) setiap kali Anda ingin memproses daftar (misalnya, menghitung total atau mencari item), Fungsional memungkinkan Anda langsung  menentukan logika pemrosesannya dalam satu baris (lambda).
  - Ini membuat kode Anda lebih ringkas, ekspresif, dan fokus pada tujuan (apa yang ingin dicapai) daripada detail (bagaimana mencapainya).