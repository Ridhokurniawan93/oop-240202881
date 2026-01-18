package com.upb.agripos;

import com.upb.agripos.model.Product;

public class AppMVC {

    public static void main(String[] args) {

        // Identitas Praktikum (Bab 1)
        System.out.println("Hello World, I am Ridho-240202881");

        // Membuat object Product (PAKAI constructor kosong)
        Product product = new Product();

        // Set nilai menggunakan setter
        product.setCode("P001");
        product.setName("Beras Premium");
        product.setPrice(12000);

        // Tampilkan data ke console
        System.out.println("Kode Produk  : " + product.getCode());
        System.out.println("Nama Produk  : " + product.getName());
        System.out.println("Harga Produk : " + product.getPrice());
    }
}
