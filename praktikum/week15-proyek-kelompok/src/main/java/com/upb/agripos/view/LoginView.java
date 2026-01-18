package com.upb.agripos.view;

import com.upb.agripos.controller.LoginController;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    private final LoginController controller;
    private String selectedRole = "KASIR";

    public LoginView(Stage stage) {
        this.controller = new LoginController(stage);
    }

    public Parent getView() {
        // Main container
        VBox mainContainer = new VBox(0);
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");
        
        // ========== HEADER ==========
        VBox header = new VBox(8);
        header.setStyle("-fx-background-color: #1b9e3d; -fx-padding: 25px;");
        
        // Logo dengan padi di dalam keranjang
        Label logoLabel = new Label("🌾🛒");
        logoLabel.setStyle("-fx-font-size: 48; -fx-text-alignment: center;");
        logoLabel.setPrefWidth(60);
        
        // Text di samping kanan logo
        VBox textBox = new VBox(4);
        textBox.setStyle("-fx-padding: 8 0 0 0;");
        
        Label titleLabel = new Label("AgriPOS");
        titleLabel.setStyle("-fx-font-size: 32; -fx-font-weight: bold; -fx-text-fill: #ffffff;");
        
        Label subtitleLabel = new Label("Agricultural Point of Sale System");
        subtitleLabel.setStyle("-fx-font-size: 13; -fx-text-fill: #e8f5e9;");
        
        textBox.getChildren().addAll(titleLabel, subtitleLabel);
        
        // Layout horizontal: logo di kiri, text di kanan
        HBox headerContent = new HBox(15);
        headerContent.setStyle("-fx-alignment: center-left;");
        headerContent.getChildren().addAll(logoLabel, textBox);
        
        header.getChildren().addAll(headerContent);
        
        // ========== CONTENT ==========
        VBox content = new VBox(20);
        content.setStyle("-fx-background-color: #ffffff;");
        content.setPadding(new Insets(25, 30, 30, 30));
        
        // Login Sebagai Section
        Label loginAsLabel = new Label("Login Sebagai");
        loginAsLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #333333;");
        
        HBox roleBox = new HBox(15);
        
        // Kasir Button
        Button kasirBtn = new Button("Kasir\nTransaksi");
        kasirBtn.setStyle("-fx-font-size: 12; -fx-padding: 15; -fx-background-color: #1b9e3d; -fx-text-fill: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-border-color: #1b9e3d; -fx-border-width: 2;");
        kasirBtn.setPrefWidth(150);
        kasirBtn.setPrefHeight(80);
        kasirBtn.setWrapText(true);
        
        // Admin Button
        Button adminBtn = new Button("Admin\nKelola Sistem");
        adminBtn.setStyle("-fx-font-size: 12; -fx-padding: 15; -fx-background-color: #f0f0f0; -fx-text-fill: #666666; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-border-color: #cccccc; -fx-border-width: 2;");
        adminBtn.setPrefWidth(150);
        adminBtn.setPrefHeight(80);
        adminBtn.setWrapText(true);
        
        kasirBtn.setOnAction(e -> {
            selectedRole = "KASIR";
            kasirBtn.setStyle("-fx-font-size: 12; -fx-padding: 15; -fx-background-color: #1b9e3d; -fx-text-fill: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-border-color: #1b9e3d; -fx-border-width: 2;");
            adminBtn.setStyle("-fx-font-size: 12; -fx-padding: 15; -fx-background-color: #f0f0f0; -fx-text-fill: #666666; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-border-color: #cccccc; -fx-border-width: 2;");
        });
        
        adminBtn.setOnAction(e -> {
            selectedRole = "ADMIN";
            adminBtn.setStyle("-fx-font-size: 12; -fx-padding: 15; -fx-background-color: #1b9e3d; -fx-text-fill: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-border-color: #1b9e3d; -fx-border-width: 2;");
            kasirBtn.setStyle("-fx-font-size: 12; -fx-padding: 15; -fx-background-color: #f0f0f0; -fx-text-fill: #666666; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-border-color: #cccccc; -fx-border-width: 2;");
        });
        
        roleBox.getChildren().addAll(kasirBtn, adminBtn);
        
        // Form Section
        Label usernameLabel = new Label("Username");
        usernameLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold; -fx-text-fill: #333333;");
        
        TextField userField = new TextField();
        userField.setStyle("-fx-padding: 12; -fx-font-size: 12; -fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5; -fx-border-width: 1;");
        userField.setPromptText("👤 Masukkan username");
        userField.setPrefHeight(40);
        
        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold; -fx-text-fill: #333333;");
        
        // Password field container with visibility toggle
        HBox passwordContainer = new HBox(0);
        passwordContainer.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5; -fx-border-width: 1; -fx-background-color: #ffffff;");
        
        PasswordField passField = new PasswordField();
        passField.setStyle("-fx-padding: 12; -fx-font-size: 12; -fx-border-color: transparent; -fx-background-color: transparent;");
        passField.setPromptText("🔒 Masukkan password");
        passField.setPrefHeight(40);
        HBox.setHgrow(passField, javafx.scene.layout.Priority.ALWAYS);
        
        TextField passVisibleField = new TextField();
        passVisibleField.setStyle("-fx-padding: 12; -fx-font-size: 12; -fx-border-color: transparent; -fx-background-color: transparent;");
        passVisibleField.setPromptText("🔒 Masukkan password");
        passVisibleField.setPrefHeight(40);
        passVisibleField.setVisible(false);
        passVisibleField.setManaged(false);
        HBox.setHgrow(passVisibleField, javafx.scene.layout.Priority.ALWAYS);
        

        Button togglePassBtn = new Button("👁\u0335");
        togglePassBtn.setStyle("-fx-font-size: 16; -fx-padding: 0; -fx-background-color: transparent; -fx-border-color: transparent; -fx-cursor: hand;");
        togglePassBtn.setPrefHeight(40);
        togglePassBtn.setPrefWidth(40);
        togglePassBtn.setPadding(new Insets(0, 12, 0, 0));
        
        boolean[] isPasswordVisible = {false};
        
        togglePassBtn.setOnAction(e -> {
            isPasswordVisible[0] = !isPasswordVisible[0];
            
            if (isPasswordVisible[0]) {
                // Show password
                passVisibleField.setText(passField.getText());
                passField.setVisible(false);
                passField.setManaged(false);
                passVisibleField.setVisible(true);
                passVisibleField.setManaged(true);
                togglePassBtn.setText("👁️");
            } else {
                // Hide password
                passField.setText(passVisibleField.getText());
                passVisibleField.setVisible(false);
                passVisibleField.setManaged(false);
                passField.setVisible(true);
                passField.setManaged(true);
                togglePassBtn.setText("👁\u0335");
            }
        });
        
        // Sync text between both fields
        passField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (isPasswordVisible[0]) {
                passVisibleField.setText(newVal);
            }
        });
        
        passVisibleField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (isPasswordVisible[0]) {
                passField.setText(newVal);
            }
        });
        
        passwordContainer.getChildren().addAll(passField, passVisibleField, togglePassBtn);
        
        Label errorMsg = new Label();
        errorMsg.setStyle("-fx-text-fill: #d32f2f; -fx-font-size: 11;");
        
        // Login Button
        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-font-size: 13; -fx-padding: 12; -fx-background-color: #1b9e3d; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5; -fx-font-weight: bold; -fx-cursor: hand;");
        loginBtn.setPrefWidth(Double.MAX_VALUE);
        loginBtn.setPrefHeight(45);
        
        loginBtn.setOnMouseEntered(e -> loginBtn.setStyle("-fx-font-size: 13; -fx-padding: 12; -fx-background-color: #188732; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5; -fx-font-weight: bold; -fx-cursor: hand;"));
        loginBtn.setOnMouseExited(e -> loginBtn.setStyle("-fx-font-size: 13; -fx-padding: 12; -fx-background-color: #1b9e3d; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5; -fx-font-weight: bold; -fx-cursor: hand;"));
        
        loginBtn.setOnAction(e -> {
            errorMsg.setText("");
            String password = isPasswordVisible[0] ? passVisibleField.getText() : passField.getText();
            controller.login(userField.getText(), password, selectedRole);
        });
        
        // Demo Credentials
        Label demoLabel = new Label("Demo Credentials:");
        demoLabel.setStyle("-fx-font-size: 11; -fx-font-weight: bold; -fx-text-fill: #666666;");
        
        Label demoKasir = new Label("👤 Kasir: kasir / kasir123");
        demoKasir.setStyle("-fx-font-size: 10; -fx-text-fill: #333333;");
        
        Label demoAdmin = new Label("👤 Admin: admin / admin123");
        demoAdmin.setStyle("-fx-font-size: 10; -fx-text-fill: #333333;");
        
        // Add to content
        content.getChildren().addAll(
            loginAsLabel, roleBox,
            usernameLabel, userField,
            passwordLabel, passwordContainer,
            errorMsg,
            loginBtn,
            demoLabel, demoKasir, demoAdmin
        );
        
        // Add header and content to main
        mainContainer.getChildren().addAll(header, content);
        
        return mainContainer;
    }
}
