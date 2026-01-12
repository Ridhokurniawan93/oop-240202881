package com.upb.agripos.dao;

import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class ProductDAOImpl {

    private List<Product> products = new ArrayList<>();

    public void save(Product product) {
        products.add(product);
    }

    public void delete(String code) {
        products.removeIf(p -> p.getCode().equals(code));
    }

    public List<Product> findAll() {
        return products;
    }
}
