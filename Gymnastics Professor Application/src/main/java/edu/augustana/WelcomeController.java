package edu.augustana;

import javafx.fxml.FXML;
import java.io.IOException;
import java.util.List;

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
    @FXML

    private void initialize(){
        List<String> recentFiles = UserPreferencesManager.getRecentFiles();
        System.out.println("recent = " + recentFiles);
    }
}
