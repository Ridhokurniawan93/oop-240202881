package com.upb.agripos.controller;

import com.upb.agripos.view.AdminView;
import com.upb.agripos.view.KasirView;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LoginController {

    private final Stage stage;

    public LoginController(Stage stage) {
        this.stage = stage;
    }

    public void login(String username, String password, String selectedRole) {

        if (selectedRole.equals("ADMIN")) {
            // Admin login validation
            if (username.equals("admin") && password.equals("admin123")) {
                stage.setScene(new Scene(new AdminView("ADMIN").getView(), 500, 400));
            } else {
                showLoginError();
            }
        } 
        else if (selectedRole.equals("KASIR")) {
            // Kasir login validation
            if (username.equals("kasir") && password.equals("kasir123")) {
                stage.setScene(new Scene(new KasirView("KASIR").getView(), 500, 400));
            } else {
                showLoginError();
            }
        }
    }

    private void showLoginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Gagal");
        alert.setHeaderText(null);
        alert.setContentText("Username atau password salah!");
        alert.showAndWait();
    }
}
