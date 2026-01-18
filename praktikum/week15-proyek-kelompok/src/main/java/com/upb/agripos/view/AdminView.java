package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AdminView {

    private final VBox root;

    public AdminView(String role) {
        Label label = new Label("Dashboard Admin");
        
        root = new VBox(10, label);
        root.setPadding(new Insets(20));
    }

    public Parent getView() {
        return root;
    }
}
