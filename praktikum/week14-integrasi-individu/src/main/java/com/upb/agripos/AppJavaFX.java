package com.upb.agripos;

import com.upb.agripos.view.PosView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        PosView view = new PosView();
        Scene scene = new Scene(view.getRoot(), 900, 700);

        stage.setTitle("Agri-POS Week 14");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
