package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.dao.JdbcProductDAO;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

public class ProductService {
    private ProductDAO dao = new JdbcProductDAO();

    public void addProduct(Product product) {
        dao.insert(product);
    }

    public void deleteProduct(String code) {
        dao.delete(code);
    }

    public List<Product> getProducts() {
        return dao.findAll();
    }
}
