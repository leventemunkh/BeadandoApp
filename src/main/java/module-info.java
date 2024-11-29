module com.example.beadandoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.sun.xml.ws;
    requires java.xml.bind;
    requires java.desktop;
    requires jakarta.activation;


    opens com.example.beadandoapp to javafx.fxml;
    exports com.example.beadandoapp;
}
