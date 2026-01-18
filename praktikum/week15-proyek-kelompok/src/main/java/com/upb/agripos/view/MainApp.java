package com.upb.agripos.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        LoginView view = new LoginView(stage);
        Scene scene = new Scene(view.getView(), 500, 700);
        stage.setScene(scene);
        stage.setTitle("AGRIPOS - Login");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
