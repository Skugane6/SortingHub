module com.example.a1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.a1 to javafx.fxml;
    exports com.example.a1;
}