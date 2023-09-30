package edu.augustana;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


/**
 * Main View (Home Screen) of the Gymnastics Professor Application.
 *
 */
public class GymnasticsAppMainView {

    @FXML // fx:id="categoryFilter"
    private ComboBox<?> categoryFilter; // Value injected by FXMLLoader

    @FXML // fx:id="equipFilter"
    private ComboBox<?> equipFilter; // Value injected by FXMLLoader

    @FXML // fx:id="eventFilter"
    private ComboBox<?> eventFilter; // Value injected by FXMLLoader

    @FXML // fx:id="filtersMenu"
    private HBox filtersMenu; // Value injected by FXMLLoader

    @FXML // fx:id="genderFilter"
    private ComboBox<?> genderFilter; // Value injected by FXMLLoader

    @FXML // fx:id="levelFilter"
    private ComboBox<?> levelFilter; // Value injected by FXMLLoader

    @FXML // fx:id="lpWorkSpace"
    private BorderPane lpWorkSpace; // Value injected by FXMLLoader

    @FXML // fx:id="mainMenu"
    private MenuBar mainMenu; // Value injected by FXMLLoader

    @FXML // fx:id="mainSearch"
    private TextField mainSearch; // Value injected by FXMLLoader

    @FXML // fx:id="scrollBar"
    private ScrollBar scrollBar; // Value injected by FXMLLoader

    //Set up components with desired features, and integrate event listeners.
    @FXML
    void initialize(){}

}



