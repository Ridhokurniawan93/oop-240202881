package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.upb.agripos.model.Product;
import com.upb.agripos.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private Connection conn = DBConnection.getConnection();

    @Override
    public void insert(Product p) {
        String sql = "INSERT INTO product VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getCode());
            ps.setString(2, p.getName());
            ps.setInt(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product p) {
        String sql = "UPDATE product SET stock=? WHERE code=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getStock());
            ps.setString(2, p.getCode());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findByCode(String code) {
        String sql = "SELECT * FROM product WHERE code=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String code) {
        String sql = "DELETE FROM product WHERE code=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
