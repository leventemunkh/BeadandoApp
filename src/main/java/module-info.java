module com.example.beadandoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.beadandoapp to javafx.fxml;
    exports com.example.beadandoapp;
}