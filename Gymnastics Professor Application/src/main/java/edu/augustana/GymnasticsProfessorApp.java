package edu.augustana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The GymnasticsProfessorApp class serves as the main entry point for the Gymnastics Professor Application.
 * It extends the JavaFX Application class and manages the initialization and switching of scenes.
 */
public class GymnasticsProfessorApp extends Application {
    private static Scene scene;

    /**
     * The start method is called when the JavaFX application is launched. It initializes the primary stage,
     * sets the scene with the WelcomePage, and maximizes the stage to match the primary screen's dimensions.
     *
     * @param stage The primary stage for the JavaFX application.
     * @throws IOException If an I/O error occurs during scene loading.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("WelcomePage"));

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

    /**
     * Switches the scene to the main view by loading the MainUI.fxml file.
     *
     * @return The controller for the GymnasticsAppMainView.
     * @throws IOException If an I/O error occurs during scene loading.
     */

    public static GymnasticsAppMainView switchToMainView() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(GymnasticsProfessorApp.class.getResource( "MainUI.fxml"));

        Parent root = fxmlLoader.load();
        GymnasticsAppMainView controller = fxmlLoader.getController();
        scene.setRoot(root);
        scene.getStylesheets().add(String.valueOf(GymnasticsProfessorApp.class.getResource( "stylesheet.css")));
        return controller;

    }

    /**
     * Sets the root of the scene to the specified FXML file.
     *
     * @param fxml The name of the FXML file (excluding the file extension) to set as the root.
     * @throws IOException If an I/O error occurs during scene loading.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads the FXML file and returns the Parent root node.
     *
     * @param fxml The name of the FXML file (excluding the file extension) to load.
     * @return The root node of the loaded FXML file.
     * @throws IOException If an I/O error occurs during scene loading.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymnasticsProfessorApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}