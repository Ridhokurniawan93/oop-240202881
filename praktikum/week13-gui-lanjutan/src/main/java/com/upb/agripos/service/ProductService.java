package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class ProductService {

    private ProductDAOImpl dao;

    public ProductService(ProductDAOImpl dao) {
        this.dao = dao;
    }

    public void save(Product product) {
        dao.save(product);
    }

    public void delete(String code) {
        dao.delete(code);
    }
}
