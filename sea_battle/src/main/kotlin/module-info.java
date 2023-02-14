module com.example.sea_battle {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens seaBattle to javafx.fxml;
    exports seaBattle;
}