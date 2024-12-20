package com.example.beadandoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class BeadandoController {

    @FXML
    private TextField dateField;

    @FXML
    private ComboBox<String> currencyComboBox;

    @FXML
    private CheckBox activeOnlyCheckBox;

    @FXML
    protected void onOlvasClick() {
        System.out.println("Adatbázis Olvas menüpont aktiválva!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("read-data-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Adatok megjelenítése");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Hiba az Olvas nézet betöltésekor: " + e.getMessage());
        }
    }

    @FXML
    protected void onOlvas2Click() {
        System.out.println("Adatbázis Olvas2 menüpont aktiválva!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("filter-data-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Adatok szűrése");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Hiba az Olvas2 nézet betöltésekor: " + e.getMessage());
        }
    }

    @FXML
    protected void onIrClick() {
        System.out.println("Adatbázis Ír menüpont aktiválva!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-record-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Új rekord hozzáadása");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Hiba az Ír nézet betöltésekor: " + e.getMessage());
        }
    }

    @FXML
    protected void onModositClick() {
        System.out.println("Adatbázis Módosít menüpont aktiválva!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit-record-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Rekord módosítása");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Hiba a Módosít nézet betöltésekor: " + e.getMessage());
        }
    }

    @FXML
    protected void onTorolClick() {
        System.out.println("Adatbázis Töröl menüpont aktiválva!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete-record-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Rekord törlése");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Hiba a Töröl nézet betöltésekor: " + e.getMessage());
        }
    }

    // SOAP Kliens menüpontok
    @FXML
    protected void onSoapDownloadClick() {
        System.out.println("SOAP Letöltés aktiválva!");
        try {
            SOAPClient.downloadAllData("MNB.txt");
            System.out.println("Adatok sikeresen letöltve az MNB.txt fájlba.");
        } catch (Exception e) {
            System.err.println("Hiba a letöltés során: " + e.getMessage());
        }
    }


    @FXML
    protected void onSoapDownloadFilteredClick() {
        System.out.println("SOAP Letöltés2 aktiválva!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("soap-filter-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("SOAP Szűrt Letöltés");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Hiba a SOAP Szűrt Letöltés nézet betöltésekor: " + e.getMessage());
        }
    }

    @FXML
    protected void onFilterDownload() {
        String date = dateField.getText();
        String currency = currencyComboBox.getValue();
        boolean activeOnly = activeOnlyCheckBox.isSelected();

        System.out.println("Szűrő beállítások:");
        System.out.println("Dátum: " + date);
        System.out.println("Valuta: " + currency);
        System.out.println("Csak aktív: " + activeOnly);

        try {
            SOAPClient.downloadFilteredData("MNB.txt", date, currency, activeOnly);
            System.out.println("Szűrt adatok sikeresen letöltve az MNB.txt fájlba.");
        } catch (Exception e) {
            System.err.println("Hiba a szűrt letöltés során: " + e.getMessage());
        }
    }

    @FXML
    protected void onParallelClick() {
        System.out.println("Párhuzamos futás menüpont aktiválva!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("parallel-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Párhuzamos Programvégrehajtás");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Hiba a Párhuzamos nézet betöltésekor: " + e.getMessage());
        }
    }




    // Forex menüpontok
    @FXML
    protected void onSzamlaInfoClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Számlainformációk");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void onArakClick() {
        System.out.println("Forex Aktuális árak menüpont aktiválva!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("current-prices-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Aktuális árak");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Hiba az Aktuális árak nézet betöltésekor: " + e.getMessage());
        }
    }


    @FXML
    public void onHistorikusClick() {
        System.out.println("Historikus árak menüpont aktiválva!");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historical-prices-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Historikus árak");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Hiba a Historikus árak nézet betöltésekor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
