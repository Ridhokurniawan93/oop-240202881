package com.upb.agripos.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/agripos",
                        "postgres",
                        "339521"
                );

                System.out.println("Menghubungkan ke database...");
                System.out.println("Koneksi berhasil...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Koneksi database ditutup...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
