package com.upb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;

public class ProductTest {

    @Test
    void testProductGetterSetter() {

        Product product = new Product();

        product.setCode("P001");
        product.setName("Beras");
        product.setPrice(12000);

        assertEquals("P001", product.getCode());
        assertEquals("Beras", product.getName());
        assertEquals(12000, product.getPrice());
    }
}
