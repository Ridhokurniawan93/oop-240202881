# Laporan Praktikum Minggu 1
Topik: "Pengenalan Paradigma dan Setup Proyek"

## Identitas
- Nama  : Ridho Kurniawan
- NIM   : 240202881
- Kelas : 3IKRB

---

## Tujuan
- Mahasiswa mampu mendefinisikan paradigma prosedural, OOP, dan fungsional.
- Mahasiswa mampu membandingkan kelebihan dan keterbatasan tiap paradigma.
- Mahasiswa mampu memberikan contoh program sederhana untuk masing-masing paradigma.
- Mahasiswa aktif dalam diskusi kelas (bertanya, menjawab, memberi opini).

---

## Dasar Teori
Paradigma pemrograman adalah cara pandang dalam menyusun program:  
- **Prosedural**: program dibangun sebagai rangkaian perintah (fungsi/prosedur).  
- **OOP (Object-Oriented Programming)**: program dibangun dari objek yang memiliki data (atribut) dan perilaku (method).  
- **Fungsional**: program dipandang sebagai pemetaan fungsi matematika, lebih menekankan ekspresi dan transformasi data. 

---

## Langkah Praktikum
1. Menginstall JDK versi terbaru dan cek instalasinya menggunakan perintah `java -version`  
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
        String nim = "240202881";
        String name = "Ridho Kurniawan";

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
        Student s = new Student("240202881", "Ridho Kurniawan");
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

        introduce.accept("240202881","Ridho Kurniawan");
    }
}
```

---

## Hasil Eksekusi
- HelloProcedural
<img width="1426" height="117" alt="Cuplikan layar 2025-10-10 225124" src="https://github.com/user-attachments/assets/e8fc347f-906a-45ac-b7d8-5cae1a7a54b5" />

- HelloOOP
<img width="1419" height="97" alt="Cuplikan layar 2025-10-10 224934" src="https://github.com/user-attachments/assets/f1a526c2-2afa-48f0-ae92-0309da832d91" />

- HelloFunctional
<img width="1426" height="111" alt="Cuplikan layar 2025-10-10 222502" src="https://github.com/user-attachments/assets/251d6e20-4b32-467f-bac4-d2693375ff0c" />

---

## Analisis
1. Cara Kerja Kode
•	Prosedural: Menjalankan instruksi secara berurutan dalam satu fungsi utama tanpa class.
•	OOP: Menggunakan class Student untuk menggabungkan data dan perilaku dalam objek.
•	Fungsional: Memakai lambda expression dan BiConsumer untuk menjalankan fungsi tanpa class tambahan.

2. Perbedaan dengan Minggu Sebelumnya
•	Minggu ini fokus pada tiga paradigma (Prosedural, OOP, Fungsional), bukan sekadar menulis Hello World.
•	Program lebih terstruktur dan memperkenalkan konsep objek serta fungsi murni.

3. Kendala
•	Awalnya sulit membedakan penggunaan OOP dan fungsional.
•	Solusinya dengan mempelajari dokumentasi java.util.function dan mencoba contoh implementasi sederhana.


---

## Kesimpulan
   - Paradigma prosedural cocok untuk program kecil dengan alur sederhana karena mudah dipahami dan cepat dibuat.
   - Paradigma OOP lebih terstruktur dan mudah dikembangkan berkat konsep class dan objek.
   - Paradigma Fungsional menghasilkan kode ringkas, mudah diuji, dan efisien untuk transformasi data.
 Dengan memahami ketiga paradigma, mahasiswa dapat memilih pendekatan yang tepat sesuai kebutuhan proyek dan kompleksitas apliksi

---

## Quiz
1. Apakah OOP selalu lebih baik dari prosedural?  
   **Jawaban:**
   Tidak. OOP dan prosedural memiliki kelebihan dan kelemahannya masing-masing. Prosedural lebih sederhana untuk program kecil, sedangkan OOP lebih baik untuk proyek besar yang membutuhkan struktur dan skalabilitas tinggi.

2. Kapan functional programming lebih cocok digunakan dibanding OOP  atau prosedural?    
   **Jawaban:**
   Paradigma fungsional lebih cocok untuk proyek yang berfokus pada transformasi data, membutuhkan parallel processing, atau memerlukan hasil yang konsisten dan dapat diuji tanpa efek samping.
   
3. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?  
   **Jawaban:**
   • Prosedural: Mudah untuk aplikasi kecil, tapi sulit dikembangkan karena struktur tidak modular.
   • OOP: Paling maintainable dan scalable karena kode terorganisir melalui class dan objek.
   • Fungsional: Maintainable tinggi karena fungsi murni mudah diuji dan digabungkan kembali tanpa mengubah state.

4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?  
**Jawaban:**
    Karena sistem POS terdiri dari banyak entitas (produk, pelanggan, transaksi, kasir) yang saling berinteraksi. OOP memudahkan representasi dan hubungan antar entitas tersebut melalui konsep objek dan relasinya, sehingga lebih mudah dikembangkan dan dikelola.

5. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (boilerplate code)?  
**Jawaban:**
   paradigma fungsional mengurangi kode berulang dengan:
•	Higher-order functions seperti map, filter, dan reduce.
•	Immutability dan pure function yang mencegah dependensi antar bagian kode.
•	Function composition yang memungkinkan penggabungan fungsi kecil menjadi operasi kompleks tanpa duplikasi logika.



