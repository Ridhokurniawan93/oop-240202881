package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.exception.OutOfStockException;
import com.upb.agripos.exception.ProductNotFoundException;
import com.upb.agripos.model.Product;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO = new ProductDAOImpl();

    public void addProduct(Product product) {
        productDAO.addProduct(product); // ⬅ BUKAN save()
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public Product getProductById(String id) throws ProductNotFoundException {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("Produk tidak ditemukan");
        }
        return product;
    }

    public void updateStock(String productId, int qty)
            throws ProductNotFoundException, OutOfStockException {

        Product product = getProductById(productId);

        if (product.getStock() < qty) {
            throw new OutOfStockException("Stok tidak mencukupi");
        }

        product.setStock(product.getStock() - qty);
        productDAO.update(product);
    }
}
