package com.upb.agripos.dao;

import com.upb.agripos.model.Product;

public interface ProductDAO {

    void insert(Product p);

    void update(Product p);

    Product findByCode(String code);

    void delete(String code);
}
