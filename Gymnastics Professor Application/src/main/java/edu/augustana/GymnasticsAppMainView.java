package edu.augustana;


import edu.augustana.constants.CategoryEnum;
import edu.augustana.constants.EventsEnum;
import edu.augustana.constants.GenderEnum;
import edu.augustana.constants.LevelEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    private List<Card> lessonPlan = new ArrayList<>();

    //Set up components with desired features, and integrate event listeners.
    @FXML
    void initialize(){
        addOptions();

        initializeMainSearchView();
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

    void initializeMainSearchView() {
        // Assuming you have a list of Card objects named 'cardList'
        int maxColumns = 3;  // Number of columns in the GridPane
        int currentColumn = 0;  // Initialize the current column
        int currentRow = 0;  // Initialize the current row
        CardCollection.createCardCollection();
        Screen windowScreen = Screen.getPrimary();
        scrollPaneView.setMinWidth(windowScreen.getBounds().getWidth() * 0.6);
        mainSearchView.setMinWidth(windowScreen.getBounds().getWidth() * 0.3);
        lpWorkSpace.setMinWidth(windowScreen.getBounds().getWidth() * 0.7);
        cardListView.setMinHeight(windowScreen.getBounds().getHeight() * 0.8);

        // Dynamically add rows based on the number of cards
        int numRows = (CardCollection.cardCollection.size() + maxColumns - 1) / maxColumns;
        for (int i = 0; i < numRows; i++) {
            mainSearchView.addRow(i);
        }
        for (Card card : CardCollection.cardCollection) {
            CardView cardView = new CardView(card);
            HBox cardBox = cardView.makeCardList();

            // Add the cardBox to the GridPane at the current row and column
            cardListView.getItems().add(cardBox);

        }

    }
    @FXML
    void addImage(MouseEvent event) {
        lessonPlanImage.setVisible(true);
    }
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




