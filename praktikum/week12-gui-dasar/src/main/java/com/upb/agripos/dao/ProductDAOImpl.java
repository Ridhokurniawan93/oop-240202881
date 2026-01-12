package com.upb.agripos.dao;

import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class ProductDAOImpl implements ProductDAO {

    private List<Product> products = new ArrayList<>();

    public ProductDAOImpl() {
        products.add(new Product("P01", "Beras", 10000, 10));
        products.add(new Product("P02", "Pupuk", 20000, 20));
        products.add(new Product("P03", "Insektisida", 15000, 7));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }
}
