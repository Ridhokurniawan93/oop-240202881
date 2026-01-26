package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;

public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void saveProduct(String code, String name, int price, int stock) {
        Product p = new Product(code, name, price, stock);
        service.save(p);
    }

    public void deleteProduct(String code) {
        service.delete(code);
    }
}
