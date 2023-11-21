package edu.augustana;

import javafx.fxml.FXML;
import java.io.IOException;

public class WelcomeController {

    @FXML
    private void loadExistingLessonButton() throws IOException {
        GymnasticsAppMainView mainView = GymnasticsProfessorApp.switchToMainView();
        mainView.menuActionOpen(null);

    }

    @FXML
    private void createLessonPlanButton() throws IOException {
            GymnasticsProfessorApp.switchToMainView();
    }
}
