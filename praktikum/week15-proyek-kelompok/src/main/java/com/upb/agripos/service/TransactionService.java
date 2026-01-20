package com.upb.agripos.service;

import com.upb.agripos.dao.TransactionDAO;
import com.upb.agripos.model.Cart;
import com.upb.agripos.model.Transaction;

public class TransactionService {

    private TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public void checkout(Cart cart) {
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart kosong");
        }

        double total = cart.getTotal(); // ✅ FIX DI SINI
        Transaction transaction = new Transaction(cart.getItems(), total);

        transactionDAO.save(transaction);
        cart.clear();
    }
}
