package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/agripos",
                    "postgres",
                    "postgres"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
