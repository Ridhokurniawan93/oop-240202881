<<<<<<< HEAD
package main.java.com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<Product, Integer> items = new HashMap<>();


    public void addProduct(Product product, int qty)
            throws InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                "Jumlah pembelian harus lebih dari 0"
            );
        }

        items.put(product, items.getOrDefault(product, 0) + qty);
    }

    
    public void removeProduct(Product product)
            throws ProductNotFoundException {

        if (!items.containsKey(product)) {
            throw new ProductNotFoundException(
                "Produk tidak ditemukan di dalam keranjang"
            );
        }

        items.remove(product);
    }

    
    public void checkout()
            throws EmptyStockException, InsufficientStockException {

        // 1. Keranjang kosong
        if (items.isEmpty()) {
            throw new EmptyStockException(
                "Keranjang belanja masih kosong"
            );
        }

        // 2. Validasi stok
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();

            if (product.getStock() < qty) {
                throw new InsufficientStockException(
                    "Stok tidak mencukupi untuk produk: "
                    + product.getName()
                );
            }
        }

        // 3. Kurangi stok jika semua valid
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }

        items.clear();
        System.out.println("Checkout berhasil ");
    }
}
=======
package main.java.com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<Product, Integer> items = new HashMap<>();


    public void addProduct(Product product, int qty)
            throws InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                "Jumlah pembelian harus lebih dari 0"
            );
        }

        items.put(product, items.getOrDefault(product, 0) + qty);
    }

    
    public void removeProduct(Product product)
            throws ProductNotFoundException {

        if (!items.containsKey(product)) {
            throw new ProductNotFoundException(
                "Produk tidak ditemukan di dalam keranjang"
            );
        }

        items.remove(product);
    }

    
    public void checkout()
            throws EmptyStockException, InsufficientStockException {

        // 1. Keranjang kosong
        if (items.isEmpty()) {
            throw new EmptyStockException(
                "Keranjang belanja masih kosong"
            );
        }

        // 2. Validasi stok
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();

            if (product.getStock() < qty) {
                throw new InsufficientStockException(
                    "Stok tidak mencukupi untuk produk: "
                    + product.getName()
                );
            }
        }

        // 3. Kurangi stok jika semua valid
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }

        items.clear();
        System.out.println("Checkout berhasil ");
    }
}
>>>>>>> d6de52632435290cbf8abc21dc61f833d9b048bb
