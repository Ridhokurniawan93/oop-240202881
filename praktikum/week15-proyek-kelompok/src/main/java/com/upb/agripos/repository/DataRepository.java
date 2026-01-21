package com.upb.agripos.repository;

import java.time.LocalDate;

import com.upb.agripos.model.Product;
import com.upb.agripos.model.Promo;
import com.upb.agripos.model.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Repository terpusat untuk menyimpan data Produk, Promo, dan Transaksi
 * Digunakan oleh AdminView dan KasirView agar data sinkron
 */
public class DataRepository {
    private static DataRepository instance;
    
    private final ObservableList<Product> products;
    private final ObservableList<Promo> promos;
    private final ObservableList<Transaction> transactions;
    
    private DataRepository() {
        // Initialize dengan sample data
        this.products = FXCollections.observableArrayList(
            new Product("P001", "Beras 10kg", 12000, 50),
            new Product("P002", "Gula 1kg", 15000, 30),
            new Product("P003", "Minyak Goreng 2L", 25000, 25),
            new Product("P004", "Telur 1Kg", 18000, 40),
            new Product("P005", "Tepung Terigu 1kg", 8000, 20)
        );
        
        this.promos = FXCollections.observableArrayList(
            new Promo("PROMO10", "Diskon 10%", 0.10, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 2, 28)),
            new Promo("PROMO20", "Diskon 20%", 0.20, LocalDate.of(2026, 1, 15), LocalDate.of(2026, 3, 15))
        );
        
        this.transactions = FXCollections.observableArrayList();
    }
    
    /**
     * Mendapatkan instance singleton dari DataRepository
     */
    public static synchronized DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }
    
    /**
     * Mendapatkan ObservableList produk (shared)
     */
    public ObservableList<Product> getProducts() {
        return products;
    }
    
    /**
     * Mendapatkan ObservableList promo (shared)
     */
    public ObservableList<Promo> getPromos() {
        return promos;
    }
    
    /**
     * Mendapatkan ObservableList transaksi (shared)
     */
    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }
    
    /**
     * Menambahkan produk baru
     */
    public void addProduct(Product product) {
        products.add(product);
    }
    
    /**
     * Menghapus produk
     */
    public void removeProduct(Product product) {
        products.remove(product);
    }
    
    /**
     * Menambahkan promo baru
     */
    public void addPromo(Promo promo) {
        promos.add(promo);
    }
    
    /**
     * Menghapus promo
     */
    public void removePromo(Promo promo) {
        promos.remove(promo);
    }
    
    /**
     * Menambahkan transaksi baru
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    
    /**
     * Update produk
     */
    public void updateProduct(int index, Product product) {
        if (index >= 0 && index < products.size()) {
            products.set(index, product);
        }
    }
    
    /**
     * Update promo
     */
    public void updatePromo(int index, Promo promo) {
        if (index >= 0 && index < promos.size()) {
            promos.set(index, promo);
        }
    }
}
