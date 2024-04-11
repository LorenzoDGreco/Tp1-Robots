module canlor.tp1robots {
    requires javafx.controls;
    requires javafx.fxml;


    opens canlor.tp1robots to javafx.fxml;
    exports canlor.tp1robots;
    exports canlor.tp1robots.view;
    opens canlor.tp1robots.view to javafx.fxml;
}