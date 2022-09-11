module com.example.tirgul_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tirgul_2 to javafx.fxml;
    exports com.example.tirgul_2;
}