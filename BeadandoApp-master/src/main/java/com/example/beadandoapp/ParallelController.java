package com.example.beadandoapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ParallelController {

    @FXML
    private Label labelOne;

    @FXML
    private Label labelTwo;

    @FXML
    protected void onStartParallelTask() {
        // Első szál: 1 másodpercenként frissít
        Thread threadOne = new Thread(() -> {
            int count = 0;
            while (true) {
                int finalCount = count++;
                Platform.runLater(() -> labelOne.setText("Label 1: " + finalCount));
                try {
                    Thread.sleep(1000); // 1 másodperc
                } catch (InterruptedException e) {
                    break; // Megszakítja a szálat
                }
            }
        });

        // Második szál: 2 másodpercenként frissít
        Thread threadTwo = new Thread(() -> {
            int count = 0;
            while (true) {
                int finalCount = count++;
                Platform.runLater(() -> labelTwo.setText("Label 2: " + finalCount));
                try {
                    Thread.sleep(2000); // 2 másodperc
                } catch (InterruptedException e) {
                    break; // Megszakítja a szálat
                }
            }
        });

        // Szálak indítása
        threadOne.setDaemon(true);
        threadTwo.setDaemon(true);
        threadOne.start();
        threadTwo.start();
    }
}
