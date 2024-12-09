package com.example.beadandoapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.List;

public class FilterDataController {

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> lengthSelector;

    @FXML
    private RadioButton stationsLow;

    @FXML
    private RadioButton stationsHigh;

    @FXML
    private CheckBox guidedOnly;

    @FXML
    private TableView<Record> resultTable;

    @FXML
    private TableColumn<Record, Integer> columnId;

    @FXML
    private TableColumn<Record, String> columnName;

    @FXML
    private TableColumn<Record, Double> columnLength;

    @FXML
    private TableColumn<Record, Integer> columnStations;

    @FXML
    public void initialize() {
        // ComboBox elemek hozzáadása
        lengthSelector.setItems(FXCollections.observableArrayList(
                "Kisebb mint 5 km",
                "5-10 km között",
                "Nagyobb mint 10 km"
        ));

        // TableView oszlopok összekötése
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        columnStations.setCellValueFactory(new PropertyValueFactory<>("stations"));
    }

    @FXML
    protected void onFilterClick() {
        String name = nameField.getText();
        String lengthCriteria = lengthSelector.getValue();
        boolean lessStations = stationsLow.isSelected();
        boolean guided = guidedOnly.isSelected();

        // Adatok szűrése az adatbázisból
        List<Record> filteredRecords = DataBaseHelper.getFilteredRecords(name, lengthCriteria, lessStations, guided);

        // Adatok betöltése a TableView-ba
        ObservableList<Record> data = FXCollections.observableArrayList(filteredRecords);
        resultTable.setItems(data);
    }
}
