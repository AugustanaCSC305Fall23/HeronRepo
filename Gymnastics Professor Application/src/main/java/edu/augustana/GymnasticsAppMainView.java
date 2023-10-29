package edu.augustana;


import edu.augustana.constants.CategoryEnum;
import edu.augustana.constants.EventsEnum;
import edu.augustana.constants.GenderEnum;
import edu.augustana.constants.LevelEnum;
import edu.augustana.utils.SearchCardCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Main View (Home Screen) of the Gymnastics Professor Application.
 *
 */
public class GymnasticsAppMainView {

    @FXML // fx:id="categoryFilter"
    private ComboBox<String> categoryFilter; // Value injected by FXMLLoader

    @FXML // fx:id="equipFilter"
    private ComboBox<String> equipFilter; // Value injected by FXMLLoader

    @FXML // fx:id="eventFilter"
    private ComboBox<String> eventFilter; // Value injected by FXMLLoader
    @FXML // fx:id="filtersMenu"
    private HBox filtersMenu; // Value injected by FXMLLoader
    @FXML // fx:id="genderFilter"
    private ComboBox<String> genderFilter; // Value injected by FXMLLoader
    @FXML // fx:id="levelFilter"
    private ComboBox<String> levelFilter; // Value injected by FXMLLoader
    @FXML // fx:id="lpWorkSpace"
    private BorderPane lpWorkSpace; // Value injected by FXMLLoader
    @FXML // fx:id="mainMenu"
    private MenuBar mainMenu; // Value injected by FXMLLoader
    @FXML // fx:id="mainSearch"
    private TextField mainSearch; // Value injected by FXMLLoader
    @FXML // fx:id="scrollBar"
    private ScrollBar scrollBar; // Value injected by FXMLLoader

    @FXML
    private GridPane mainSearchView;
    @FXML
    private ScrollPane scrollPaneView;
    @FXML
    private ImageView lessonPlanImage;

    @FXML
    private HBox searchHBox;

    @FXML // fx:id="lessonPlanCardView"
    private ListView lessonPlanCardView;

    private CardCollectionView cardCollectionView;

    private SearchCardCollection searchCardCollection;
    //Set up components with desired features, and integrate event listeners.
    @FXML
    void initialize(){
        addOptions();
        cardCollectionView = new CardCollectionView(mainSearchView);
        cardCollectionView.switchCardCollectionToMainView();
        addEventsListeners();
        searchCardCollection = SearchCardCollection.SearchCardCollectionBuilder.searchBuilder().build();
    }

    void addOptions() {
        addOptionsForGender();
        addOptionsForLevel();
        addOptionsForEvent();
        addOptionsForCategory();

    }

    void addOptionsForGender() {
        for (GenderEnum gender: GenderEnum.values()){
            genderFilter.getItems().addAll(gender.toString());
        }
    }

    void addOptionsForLevel() {
        levelFilter.getItems().addAll(Arrays.stream(LevelEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
    }

    void addOptionsForEvent() {
        eventFilter.getItems().addAll(Arrays.stream(EventsEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
    }

    void addOptionsForCategory() {
        categoryFilter.getItems().addAll(Arrays.stream(CategoryEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
    }

    void addEventsListeners() {
        mainSearch.setOnAction(buttonHandler);
    }
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>(
            
    ) {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Hello " + event.getSource());
            searchCardCollection.setCardTitleCode(mainSearch.getText());
            List<Card> newSearchList = searchCardCollection.searchCards();
            cardCollectionView.initializeMainSearchView(newSearchList);
        }
    };


    @FXML
    void addImage(MouseEvent event) {
        lessonPlanImage.setVisible(true);
    }
    @FXML
    void clearImage(MouseEvent event){
        lessonPlanImage.setVisible(false);
    }

}



