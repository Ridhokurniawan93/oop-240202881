package com.upb.agripos.service;

import com.upb.agripos.dao.JdbcUserDAO;
import com.upb.agripos.dao.UserDAO;
import com.upb.agripos.model.User;

public class AuthService {

    private final UserDAO userDAO = new JdbcUserDAO();

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }
}
