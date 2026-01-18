package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class KasirView {

    public KasirView(String role) {
    }

    public Parent getView() {
        Label label = new Label("Halaman Kasir");
        
        VBox box = new VBox(10, label);
        box.setPadding(new Insets(20));
        return box;
    }
}
