package com.example.beadandoapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoricalPricesController {

    @FXML
    private ComboBox<String> currencyPairComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<PriceData> pricesTable;

    @FXML
    private TableColumn<PriceData, String> dateColumn;

    @FXML
    private TableColumn<PriceData, Double> priceColumn;

    @FXML
    private Canvas priceChart;

    private final OandaApiClient apiClient = new OandaApiClient();

    @FXML
    public void initialize() {
        // Devizapárok betöltése
        currencyPairComboBox.setItems(FXCollections.observableArrayList(
                "EUR/USD", "USD/JPY", "GBP/USD", "AUD/USD", "USD/CAD"
        ));

        // Táblázat oszlopainak beállítása
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    public void fetchHistoricalPrices() {
        String selectedPair = currencyPairComboBox.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (selectedPair == null || startDate == null || endDate == null) {
            System.out.println("Kérlek, töltsd ki az összes mezőt!");
            return;
        }

        try {
            String formattedPair = selectedPair.replace("/", "_");
            List<PriceData> historicalPrices = apiClient.getHistoricalPrices(formattedPair, startDate, endDate);

            // Táblázat frissítése
            pricesTable.setItems(FXCollections.observableArrayList(historicalPrices));

            // Grafikon rajzolása
            drawChart(historicalPrices);

        } catch (Exception e) {
            System.out.println("Hiba történt: " + e.getMessage());
        }
    }

    private void drawChart(List<PriceData> historicalPrices) {
        GraphicsContext gc = priceChart.getGraphicsContext2D();
        gc.clearRect(0, 0, priceChart.getWidth(), priceChart.getHeight());

        if (historicalPrices.isEmpty()) return;

        double width = priceChart.getWidth();
        double height = priceChart.getHeight();
        double xStep = width / historicalPrices.size();
        double yMax = historicalPrices.stream().mapToDouble(PriceData::getPrice).max().orElse(1.0);

        gc.strokeLine(0, height, width, height); // X tengely
        gc.strokeLine(0, 0, 0, height);          // Y tengely

        for (int i = 0; i < historicalPrices.size() - 1; i++) {
            double x1 = i * xStep;
            double y1 = height - (historicalPrices.get(i).getPrice() / yMax) * height;
            double x2 = (i + 1) * xStep;
            double y2 = height - (historicalPrices.get(i + 1).getPrice() / yMax) * height;

            gc.strokeLine(x1, y1, x2, y2);
        }
    }
}
