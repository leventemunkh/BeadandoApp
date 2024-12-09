package com.example.beadandoapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class EditRecordController {

    @FXML
    private ComboBox<String> recordSelector;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField stationField;

    // Egy térkép a rekordok azonosítójához és nevéhez
    private Map<String, Integer> recordMap = new HashMap<>();

    @FXML
    public void initialize() {
        // Töltsük be a rekordokat az adatbázisból
        ObservableList<String> records = FXCollections.observableArrayList();
        DataBaseHelper.getRecords((id, name) -> {
            records.add(name);
            recordMap.put(name, id);
        });
        recordSelector.setItems(records);

        // Rekord kiválasztása esetén töltsük be az adatokat
        recordSelector.setOnAction(event -> {
            String selectedName = recordSelector.getValue();
            if (selectedName != null) {
                int id = recordMap.get(selectedName);
                Map<String, Object> recordData = DataBaseHelper.getRecordById(id);
                nameField.setText((String) recordData.get("nev"));
                lengthField.setText(String.valueOf(recordData.get("hossz")));
                stationField.setText(String.valueOf(recordData.get("allomas")));
            }
        });
    }

    @FXML
    protected void onUpdateButtonClick() {
        String name = nameField.getText();
        String lengthText = lengthField.getText();
        String stationText = stationField.getText();
        String selectedName = recordSelector.getValue();

        if (selectedName != null) {
            int id = recordMap.get(selectedName);

            try {
                double length = Double.parseDouble(lengthText);
                int station = Integer.parseInt(stationText);

                DataBaseHelper.updateRecord(id, name, length, station);

                System.out.println("Rekord módosítva: " + name);
            } catch (NumberFormatException e) {
                System.err.println("Hibás számformátum!");
            }
        }
    }
}
