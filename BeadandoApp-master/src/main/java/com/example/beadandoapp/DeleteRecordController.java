package com.example.beadandoapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.HashMap;
import java.util.Map;

public class DeleteRecordController {

    @FXML
    private ComboBox<String> recordSelector;

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
    }

    @FXML
    protected void onDeleteButtonClick() {
        String selectedName = recordSelector.getValue();

        if (selectedName != null) {
            int id = recordMap.get(selectedName);
            DataBaseHelper.deleteRecord(id);
            recordSelector.getItems().remove(selectedName);
            System.out.println("Rekord törölve: " + selectedName);
        } else {
            System.err.println("Nem választottál ki rekordot!");
        }
    }
}
