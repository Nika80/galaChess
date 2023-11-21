module com.example.galachess {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.galachess to javafx.fxml;
    exports com.example.galachess;
}