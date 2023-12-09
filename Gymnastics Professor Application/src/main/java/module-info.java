module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.controlsfx.controls;
    requires com.google.gson;
    requires java.desktop;
    requires java.prefs;

    // Open edu.augustana package to both javafx.fxml and com.google.gson
    opens edu.augustana to javafx.fxml, com.google.gson;

    exports edu.augustana;
    exports edu.augustana.constants;

    // This is okay if you have additional types in this package that are only used by FXML
    opens edu.augustana.constants to javafx.fxml;
}