package com.upb.agripos.controller;

import com.upb.agripos.service.ProductService;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
