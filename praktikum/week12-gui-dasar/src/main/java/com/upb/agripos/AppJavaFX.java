package com.upb.agripos;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {

        // ✅ BUAT DAO DULU
        ProductDAO productDAO = new ProductDAOImpl();

        // ✅ SERVICE WAJIB DIISI DAO
        ProductService productService = new ProductService(productDAO);

        // ✅ VIEW
        ProductFormView view = new ProductFormView(productService);

        Scene scene = new Scene(view, 600, 450);
        stage.setTitle("Agri-POS - Week 12 Ridho Kurniawan 240202881 (GUI Dasar)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
