package com.upb.agripos.controller;

import com.upb.agripos.model.Cart;
import com.upb.agripos.service.TransactionService;

public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void checkout(Cart cart) {
        transactionService.checkout(cart);
    }
}
