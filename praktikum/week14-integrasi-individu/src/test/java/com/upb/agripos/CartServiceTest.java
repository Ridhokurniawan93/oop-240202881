package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;

public class CartServiceTest {

    @Test
    void testTotalCart() {
        CartService cartService = new CartService();

        Product p1 = new Product("P01", "Beras", 10000, 10);
        Product p2 = new Product("P02", "Jagung", 5000, 10);

        cartService.addToCart(p1, 2); // 20000
        cartService.addToCart(p2, 3); // 15000

        assertEquals(35000, cartService.getTotal());
    }
}
