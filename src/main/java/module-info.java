module ucf.assignments {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports ucf.assignments;
    opens ucf.assignments to javafx.fxml;
}