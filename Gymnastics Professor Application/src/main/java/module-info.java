module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.augustana to javafx.fxml;
    exports edu.augustana;
    exports edu.augustana.constants;
    opens edu.augustana.constants to javafx.fxml;
}
