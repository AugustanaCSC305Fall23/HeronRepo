package edu.augustana;


//import com.sun.javafx.menu.MenuItemBase;
import edu.augustana.constants.CategoryEnum;
import edu.augustana.constants.EventsEnum;
import edu.augustana.constants.GenderEnum;
import edu.augustana.constants.LevelEnum;
import edu.augustana.constants.ModelSexEnum;
import edu.augustana.utils.PrintCard;
import edu.augustana.utils.SearchCardCollection;
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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Screen;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Main View (Home Screen) of the Gymnastics Professor Application.
 *
 */
public class GymnasticsAppMainView {



    @FXML // fx:id="filtersMenu"
    private HBox filtersMenu; // Value injected by FXMLLoader

    @FXML // fx:id="eventFilter"
    private ComboBox<String> eventFilter; // Value injected by FXMLLoader @FXML // fx:id="categoryFilter"

    @FXML // fx:id="categoryFilter"
    private ComboBox<String> categoryFilter; // Value injected by FXMLLoader

    @FXML // fx:id="equipFilter"
    private ComboBox<String> equipFilter; // Value injected by FXMLLoader

    @FXML // fx:id="levelFilter"
    private ComboBox<String> levelFilter; // Value injected by FXMLLoader

    @FXML // fx:id="genderFilter"
    private ComboBox<String> genderFilter; // Value injected by FXMLLoader

    @FXML
    private ComboBox<String> modelSexFilter;  // Value injected by FXMLLoader

    @FXML // fx:id="lpWorkSpace"
    private BorderPane lpWorkSpace; // Value injected by FXMLLoader

    @FXML // fx:id="mainMenu"
    private MenuBar mainMenu; // Value injected by FXMLLoader

    @FXML // fx:id="mainSearch"
    private TextField mainSearch; // Value injected by FXMLLoader

    @FXML // fx:id="scrollBar"
    private ScrollBar scrollBar; // Value injected by FXMLLoader

    @FXML
    private Button clearFilter;

    @FXML
    private ListView mainSearchView;
    @FXML
    private ScrollPane scrollPaneView;
    @FXML
    private ImageView lessonPlanImage;
    @FXML
    private ListView cardListView;
    @FXML
    private HBox searchHBox;

    private LessonPlan lessonPlan;

    @FXML // fx:id="lessonPlanCardView"
    private ListView<Card> lessonPlanListView;

    private CardCollectionView cardCollectionView;

    private SearchCardCollection searchCardCollection;



    //Set up components with desired features, and integrate event listeners.
    @FXML
    void initialize(){
        cardCollectionView = new CardCollectionView(mainSearchView,this);//pass mainView instance
        cardCollectionView.switchCardCollectionToMainView();
        addOptions();
        addEventsListeners();
        TextFields.bindAutoCompletion(mainSearch, CardCollection.possibleSuggestions);
        searchCardCollection = SearchCardCollection.SearchCardCollectionBuilder.searchBuilder().build();
        lessonPlan = new LessonPlan();
        Screen windowScreen = Screen.getPrimary();
        lpWorkSpace.setMinWidth(windowScreen.getBounds().getWidth() * 0.7);
        lessonPlanListView.setMinHeight(windowScreen.getBounds().getHeight() * 0.8);
        lessonPlanListView.setCellFactory(param -> new CardListCell() );

    }

    void addOptions() {
        addOptionsForEvent();
        addOptionsForCategory();
        addOptionsForEquipment();
        addOptionsForLevel();
        addOptionsForGender();
        addOptionsForModelSex();
    }


    void addOptionsForEvent() {
        eventFilter.getItems().add("Event");
        eventFilter.getItems().addAll(Arrays.stream(EventsEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
    }


    void addOptionsForCategory() {
        categoryFilter.getItems().add("Category");
        categoryFilter.getItems().addAll(Arrays.stream(CategoryEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
    }


    void addOptionsForEquipment() {
        equipFilter.getItems().add("Equipment");
        for (String equipment : CardCollection.allCardsEquipment) {
            equipFilter.getItems().addAll(equipment);
        }
    }

    void addOptionsForLevel() {
        levelFilter.getItems().add("Level");
        levelFilter.getItems().addAll(Arrays.stream(LevelEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
    }

    void addOptionsForGender() {
        genderFilter.getItems().add("Gender");
        for (GenderEnum gender: GenderEnum.values()){
            genderFilter.getItems().add(gender.toString());
        }
    }
    void addOptionsForModelSex(){
        modelSexFilter.getItems().add("Model Sex");
        for (ModelSexEnum modelSex: ModelSexEnum.values()){
           modelSexFilter.getItems().add(modelSex.toString());
        }

    }


    void addEventsListeners() {
        mainSearch.setOnAction(buttonHandler);
        mainSearch.textProperty().addListener((o, oldText, newText) -> {
            runSearchForText(newText);
        });

        eventFilter.setOnAction(buttonHandler);
        categoryFilter.setOnAction(buttonHandler);
        equipFilter.setOnAction(buttonHandler);
        levelFilter.setOnAction(buttonHandler);
        genderFilter.setOnAction(buttonHandler);
        modelSexFilter.setOnAction(buttonHandler);
        clearFilter.setOnAction(clearHandler);

    }

    private void runSearchForText(String text){
        searchCardCollection.setCardTitleCode(text);
        List<Card> newSearchList = searchCardCollection.searchCards();
        cardCollectionView.initializeMainSearchView(newSearchList);

    }


    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Node source = (Node) event.getSource();
            String id = source.getId();
            List<Card> newSearchList;

            switch (id) {
                case "mainSearch":
                    runSearchForText(mainSearch.getText());
                    break;
                case "eventFilter":
                    searchCardCollection.setCardEvent(eventFilter.getSelectionModel().getSelectedItem());
                    newSearchList = searchCardCollection.searchCards();
                    cardCollectionView.initializeMainSearchView(newSearchList);
                    break;
                case "categoryFilter":
                    searchCardCollection.setCardCategory(categoryFilter.getSelectionModel().getSelectedItem());
                    newSearchList = searchCardCollection.searchCards();
                    cardCollectionView.initializeMainSearchView(newSearchList);
                    break;
                case "equipFilter":
                    searchCardCollection.setCardEquipment(equipFilter.getSelectionModel().getSelectedItem());
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
                case "modelSexFilter":
                    searchCardCollection.setCardModelSex(modelSexFilter.getSelectionModel().getSelectedItem());
                    newSearchList = searchCardCollection.searchCards();
                    cardCollectionView.initializeMainSearchView(newSearchList);
                    break;

            }

        }
    };

    EventHandler<ActionEvent> clearHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            eventFilter.getSelectionModel().select(0);
            categoryFilter.getSelectionModel().select(0);
            equipFilter.getSelectionModel().select(0);
            levelFilter.getSelectionModel().select(0);
            genderFilter.getSelectionModel().select(0);
            modelSexFilter.getSelectionModel().select(0);
            clearSearchBuilder();
        }
    };

    private void clearSearchBuilder() {
        searchCardCollection.setCardEvent(null);
        searchCardCollection.setCardCategory(null);
        searchCardCollection.setCardEquipment(null);
        searchCardCollection.setCardLevel(null);
        searchCardCollection.setCardGender(null);
        searchCardCollection.setCardModelSex(null);
        cardCollectionView.initializeMainSearchView(CardCollection.cardCollection);
    }

    @FXML
    void clearImage(MouseEvent event){
        lessonPlanListView.getItems().clear(); // Clear all items in the lesson plan list view
        lessonPlan.clear();
    }
    public void addToLessonPlan(Card mCard) {
        lessonPlan.add(mCard);
        lessonPlanListView.getItems().add(mCard);
    }
    public class CardListCell extends ListCell<Card> {

        private final ImageView imageView = new ImageView();
        private final Text cardDetails = new Text();
        private final Button removeButton = new Button("Remove");

        private final HBox cardBox = new HBox(10);

        public CardListCell() {
            removeButton.setOnAction(event -> removeFromLessonPlan());
            cardBox.getChildren().addAll(imageView, cardDetails,removeButton);
        }

        @Override
        protected void updateItem(Card card, boolean empty) {
            super.updateItem(card, empty);

            if (empty || card == null) {
                setGraphic(null);
            } else {
                if (card.getCardImage() != null) {
                    Image image = new Image(getClass().getResource("DEMO1Pack/" + card.getCardImage()).toString());
                    imageView.setImage(image);
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                }

                cardDetails.setText("Card Code: " + card.getCardCode() + "\nTitle: " + card.getCardTitle());

                setGraphic(cardBox);
            }
        }
        private void removeFromLessonPlan() {
            Card card = getItem();
            if (card != null) {
                lessonPlan.remove(card);
                lessonPlanListView.getItems().remove(card);
            }
        }
    }


}



