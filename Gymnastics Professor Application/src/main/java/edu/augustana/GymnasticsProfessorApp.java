package edu.augustana;

import edu.augustana.utils.ReadFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class GymnasticsProfessorApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainUI"));

        //Get the primary screen's dimensions
        Screen windowScreen = Screen.getPrimary();
        double windowScreenWidth = windowScreen.getBounds().getWidth();
        double windowScreenHeight = windowScreen.getBounds().getHeight();


        stage.setScene(scene);

        //Set stage dimensions to match window screen's size
        stage.setWidth(windowScreenWidth);
        stage.setHeight(windowScreenHeight);
        stage.setMaximized(true);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymnasticsProfessorApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}