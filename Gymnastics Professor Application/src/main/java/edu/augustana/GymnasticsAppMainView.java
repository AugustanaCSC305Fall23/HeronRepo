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
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
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
    private ListView cardListView;

    @FXML
    private HBox searchHBox;

    @FXML // fx:id="lessonPlanCardView"
    private ListView lessonPlanCardView;


    private CardCollectionView cardCollectionView;

    private SearchCardCollection searchCardCollection;

    private List<Card> lessonPlan = new ArrayList<>();

    private AutoCompletionBinding<String> autoCompletionBinding;


    //Set up components with desired features, and integrate event listeners.
    @FXML
    void initialize(){
        cardCollectionView = new CardCollectionView(mainSearchView);
        cardCollectionView.switchCardCollectionToMainView();
        addOptions();
        addEventsListeners();
        TextFields.bindAutoCompletion(mainSearch, CardCollection.possibleSuggestions);
        searchCardCollection = SearchCardCollection.SearchCardCollectionBuilder.searchBuilder().build();
    }

    void addOptions() {
        addOptionsForGender();
        addOptionsForLevel();
        addOptionsForEvent();
        addOptionsForCategory();
        addOptionsForEquipment();
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

    void addOptionsForEquipment() {
        for (String equipment : CardCollection.allCardsEquipment) {
            equipFilter.getItems().addAll(equipment);
        }
    }


    void addEventsListeners() {
        mainSearch.setOnAction(buttonHandler);
        categoryFilter.setOnAction(buttonHandler);
        equipFilter.setOnAction(buttonHandler);
        eventFilter.setOnAction(buttonHandler);
        levelFilter.setOnAction(buttonHandler);
        genderFilter.setOnAction(buttonHandler);
    }

    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>(
            
    ) {
        @Override
        public void handle(ActionEvent event) {
            Node source = (Node) event.getSource();
            String id = source.getId();
            List<Card> newSearchList;

            switch (id) {
                case "mainSearch":
                    searchCardCollection.setCardTitleCode(mainSearch.getText());
                    newSearchList = searchCardCollection.searchCards();
                    cardCollectionView.initializeMainSearchView(newSearchList);
                    break;
                case "categoryFilter":
                    searchCardCollection.setCardCategory(categoryFilter.getSelectionModel().getSelectedItem());
                    newSearchList = searchCardCollection.searchCards();
                    cardCollectionView.initializeMainSearchView(newSearchList);
                    break;
                case "equipFilter":
                    searchCardCollection.setCardEquipment(equipFilter.getSelectionModel().getSelectedItem().strip());
                    newSearchList = searchCardCollection.searchCards();
                    cardCollectionView.initializeMainSearchView(newSearchList);
                    break;
                case "eventFilter":
                    searchCardCollection.setCardEvent(eventFilter.getSelectionModel().getSelectedItem());
                    newSearchList = searchCardCollection.searchCards();
                    cardCollectionView.initializeMainSearchView(newSearchList);
                    break;
                case "levelFilter":
                    searchCardCollection.setCardLevel(levelFilter.getSelectionModel().getSelectedItem());
                    newSearchList = searchCardCollection.searchCards();
                    cardCollectionView.initializeMainSearchView(newSearchList);
                    break;
                case "genderFilter":
                    searchCardCollection.setCardGender(genderFilter.getSelectionModel().getSelectedItem());
                    newSearchList = searchCardCollection.searchCards();
                    cardCollectionView.initializeMainSearchView(newSearchList);
                    break;
            }

        }
    };

    @FXML
    void clearImage(MouseEvent event){
        lessonPlanImage.setVisible(false);
    }
    public void addToLessonPlan(Card mCard) {

        lessonPlan.add(mCard);
        lessonPlanCardView.getItems().add(mCard);
    }
    public class CardListCell extends ListCell<Card> {

        private final ImageView imageView = new ImageView();
        private final Text cardDetails = new Text();

        private final HBox cardBox = new HBox(10);

        public CardListCell() {
            cardBox.getChildren().addAll(imageView, cardDetails);
        }

        @Override
        protected void updateItem(Card card, boolean empty) {
            super.updateItem(card, empty);

            if (empty || card == null) {
                setGraphic(null);
            } else {
                Image image = new Image(GymnasticsProfessorApp.class.getResource("DEMO1Pack/" + card.getCardImage()).toString());
                imageView.setImage(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                cardDetails.setText("Card Code: " + card.getCardCode() + "\nTitle: " + card.getCardTitle());

                setGraphic(cardBox);
            }
        }
    }
}




