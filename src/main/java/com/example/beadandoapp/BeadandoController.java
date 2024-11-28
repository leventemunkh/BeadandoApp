package com.example.beadandoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class BeadandoController {

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
    protected void onLetoltesClick() {
        System.out.println("SOAP Letöltés menüpont aktiválva!");
        // Itt lehet majd adatokat letölteni a SOAP klienssel
    }

    @FXML
    protected void onGrafikonClick() {
        System.out.println("SOAP Grafikon menüpont aktiválva!");
        // Itt lehet majd egy grafikont megjeleníteni a letöltött adatok alapján
    }

    // Forex menüpontok
    @FXML
    protected void onSzamlaInfoClick() {
        System.out.println("Forex Számlainformációk menüpont aktiválva!");
        // Itt lehet majd számlainformációkat megjeleníteni
    }

    @FXML
    protected void onArakClick() {
        System.out.println("Forex Aktuális árak menüpont aktiválva!");
        // Itt lehet majd aktuális árakat lekérdezni
    }

    @FXML
    protected void onHistorikusClick() {
        System.out.println("Forex Historikus árak menüpont aktiválva!");
        // Itt lehet majd historikus árakat megjeleníteni
    }



}
