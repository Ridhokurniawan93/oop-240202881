package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

public interface ProductDAO {
    Product findById(String id);
    List<Product> findAll();
    void update(Product product);
}
