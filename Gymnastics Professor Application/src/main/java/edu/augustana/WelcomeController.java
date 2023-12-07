package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
   @FXML
    private ListView<String> recentFilesListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Screen windowScreen = Screen.getPrimary();

        List<String> recentFiles = UserPreferencesManager.getRecentFiles();
        recentFilesListView.getItems().addAll(recentFiles);
        recentFilesListView.setOnMouseClicked(this::handleListViewItemClick);
        recentFilesListView.setMinHeight(windowScreen.getBounds().getHeight() * 0.75);

    }
    @FXML
    private void loadExistingLessonButton() throws IOException {
        GymnasticsAppMainView mainView = GymnasticsProfessorApp.switchToMainView();
        mainView.menuActionOpen(null);

    }

    private void loadLessonPlanFromFile(String filePath) throws IOException {
        if (filePath != null) {
            GymnasticsAppMainView mainView = GymnasticsProfessorApp.switchToMainView();
            mainView.loadLessonPlan(new File(filePath));
        }
    }

    @FXML
    private void loadLessonFromListViewButton() throws IOException{
        String selectedFilePath = recentFilesListView.getSelectionModel().getSelectedItem();
        loadLessonPlanFromFile(selectedFilePath);
    }


    @FXML
    private void createLessonPlanButton() throws IOException {
            GymnasticsProfessorApp.switchToMainView();
    }

    private void handleListViewItemClick(MouseEvent event) {
        // Load the selected lesson plan from the ListView
        String selectedFilePath = recentFilesListView.getSelectionModel().getSelectedItem();

            try {
                loadLessonPlanFromFile(selectedFilePath);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception according to your needs
            }
        }

    @FXML

    private void initialize(){
        List<String> recentFiles = UserPreferencesManager.getRecentFiles();
        System.out.println("recent = " + recentFiles);
    }


}
