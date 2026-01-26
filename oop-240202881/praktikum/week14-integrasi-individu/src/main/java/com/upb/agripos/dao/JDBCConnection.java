package com.upb.agripos.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/agripos";
    private static final String USER = "postgres";
    private static final String PASSWORD = "339521";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Gagal koneksi DB", e);
        }
    }
}
