module at.kmve.jahoot {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.kmve.jahoot to javafx.fxml;
    exports at.kmve.jahoot;
}