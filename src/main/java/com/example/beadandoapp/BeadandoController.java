package com.example.beadandoapp;

import javafx.fxml.FXML;

public class BeadandoController {

    // Adatbázis menü
    @FXML
    protected void onOlvasClick() {
        System.out.println("Adatbázis Olvas menüpont aktiválva!");
        // Itt megjeleníthetsz adatokat egy táblázatban.
    }

    @FXML
    protected void onIrClick() {
        System.out.println("Adatbázis Ír menüpont aktiválva!");
        // Itt hozzáadhatsz új rekordokat.
    }

    @FXML
    protected void onModositClick() {
        System.out.println("Adatbázis Módosít menüpont aktiválva!");
        // Itt módosíthatsz meglévő adatokat.
    }

    @FXML
    protected void onTorolClick() {
        System.out.println("Adatbázis Töröl menüpont aktiválva!");
        // Itt törölhetsz rekordokat.
    }

    // SOAP Kliens menü
    @FXML
    protected void onLetoltesClick() {
        System.out.println("SOAP Letöltés menüpont aktiválva!");
        // Itt letöltheted az adatokat.
    }

    @FXML
    protected void onGrafikonClick() {
        System.out.println("SOAP Grafikon menüpont aktiválva!");
        // Itt grafikont jeleníthetsz meg.
    }

    // Forex menü
    @FXML
    protected void onSzamlaInfoClick() {
        System.out.println("Forex Számlainformációk menüpont aktiválva!");
        // Itt számlainformációkat jeleníthetsz meg.
    }

    @FXML
    protected void onArakClick() {
        System.out.println("Forex Aktuális árak menüpont aktiválva!");
        // Itt aktuális árakat kérdezhetsz le.
    }

    @FXML
    protected void onHistorikusClick() {
        System.out.println("Forex Historikus árak menüpont aktiválva!");
        // Itt historikus adatokat kérdezhetsz le.
    }
}
