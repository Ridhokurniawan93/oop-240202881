<<<<<<< HEAD
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
=======
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
>>>>>>> d6de52632435290cbf8abc21dc61f833d9b048bb
