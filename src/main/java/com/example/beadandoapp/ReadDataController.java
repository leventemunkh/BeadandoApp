package com.example.beadandoapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReadDataController {

    @FXML
    private TableView<Record> dataTable;

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
        // Állítsuk be az oszlopokhoz tartozó mezőket
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        columnStations.setCellValueFactory(new PropertyValueFactory<>("stations"));

        // Töltsük be az adatokat az adatbázisból
        ObservableList<Record> records = FXCollections.observableArrayList(DataBaseHelper.getAllRecords());
        dataTable.setItems(records);
    }
}
