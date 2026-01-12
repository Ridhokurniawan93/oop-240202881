package com.upb.agripos;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;
import com.upb.agripos.util.DBConnection;

public class MainApp {

    public static void main(String[] args) {

        System.out.println("Menghubungkan ke database...");
        DBConnection.getConnection();
        System.out.println("Koneksi berhasil...\n");

        ProductDAO dao = new ProductDAOImpl();

        System.out.println("INSERT PRODUCT");
        Product p = new Product("P01", "Pupuk Organik Premium", 30000, 10);
        dao.insert(p);
        System.out.println("Produk berhasil ditambahkan\n");

        System.out.println("Updating Product...");
        p.setStock(8);
        dao.update(p);
        System.out.println("Produk berhasil diperbarui...\n");

        System.out.println("FIND PRODUCT BY CODE");
        Product result = dao.findByCode("P01");

        if (result != null) {
            System.out.println("Data produk:");
            System.out.println("Kode : " + result.getCode());
            System.out.println("Nama : " + result.getName());
            System.out.println("Harga : " + result.getPrice());
            System.out.println("Stok : " + result.getStock());
        }

        System.out.println("\nDELETE PRODUCT");
        dao.delete("P01");
        System.out.println("Produk berhasil dihapus");

        DBConnection.closeConnection();
        System.out.println("Koneksi database ditutup...");
        System.out.println("credit by Ridho Kurniawan - 240202881");
    }
}
