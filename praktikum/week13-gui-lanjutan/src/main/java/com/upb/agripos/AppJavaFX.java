package com.upb.agripos;

import com.upb.agripos.view.ProductFormView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        ProductFormView view = new ProductFormView();
        Scene scene = new Scene(view.getView(), 800, 500);

        stage.setTitle("Agri-POS Week 13 - Ridho Kurniawan (240202881)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
