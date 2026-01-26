package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

public class ProductService {

    private final ProductDAO dao;

    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public void addProduct(Product product) {
        dao.save(product); // ✅ BUKAN insert()
    }

    public List<Product> getAllProducts() {
        return dao.findAll();
    }

    public void deleteProduct(String code) {
        dao.delete(code);
    }
}
