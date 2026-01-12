package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

public interface ProductDAO {
    List<Product> findAll();
    void save(Product product);
    void delete(String code);
}
