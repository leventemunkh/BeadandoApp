package com.example.beadandoapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class CurrentPricesController {

    @FXML
    private ComboBox<String> currencyPairComboBox;

    @FXML
    private Label priceLabel;

    private final OandaApiClient apiClient = new OandaApiClient();

    @FXML
    public void initialize() {
        currencyPairComboBox.setItems(FXCollections.observableArrayList(
                "EUR/USD", "USD/JPY", "GBP/USD", "AUD/USD", "USD/CAD"
        ));
    }

    @FXML
    public void fetchCurrentPrice() {
        String selectedPair = currencyPairComboBox.getValue();
        if (selectedPair != null) {
            try {
                String formattedPair = selectedPair.replace("/", "_");
                String price = apiClient.getCurrentPrice(formattedPair);
                priceLabel.setText("Ár: " + price);
            } catch (Exception e) {
                priceLabel.setText("Hiba történt: " + e.getMessage());
            }
        } else {
            priceLabel.setText("Kérlek, válassz egy devizapárt!");
        }
    }


}
