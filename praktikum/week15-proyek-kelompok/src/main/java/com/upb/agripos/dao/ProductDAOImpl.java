package com.upb.agripos.dao;

import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class ProductDAOImpl implements ProductDAO {

    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product("P001", "Beras", 10000, 10));
        products.add(new Product("P002", "Jagung", 8000, 5));
    }

    @Override
    public Product findById(String id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void update(Product product) {
        // in-memory, tidak perlu implementasi
    }
}
