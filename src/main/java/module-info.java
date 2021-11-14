module com.example.gomez_dugancop3330assignment4part2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.gomez_dugancop3330assignment4part2 to javafx.fxml;
    exports com.example.gomez_dugancop3330assignment4part2;
}