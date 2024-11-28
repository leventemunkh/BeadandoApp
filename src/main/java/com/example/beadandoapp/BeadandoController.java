package com.example.beadandoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BeadandoController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}