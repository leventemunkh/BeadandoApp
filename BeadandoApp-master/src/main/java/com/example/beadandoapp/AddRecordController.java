package com.example.beadandoapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddRecordController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField stationField;

    @FXML
    protected void onAddButtonClick() {
        String name = nameField.getText();
        String lengthText = lengthField.getText();
        String stationText = stationField.getText();

        try {
            double length = Double.parseDouble(lengthText);
            int station = Integer.parseInt(stationText);

            DataBaseHelper.addRecord(name, length, station);

            System.out.println("Új rekord hozzáadva: " + name);
        } catch (NumberFormatException e) {
            System.err.println("Hibás számformátum!");
        }
    }
}
