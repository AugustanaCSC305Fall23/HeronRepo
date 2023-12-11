package edu.augustana;

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

import javafx.stage.FileChooser;

import javafx.stage.Screen;

import javafx.stage.Window;

import org.controlsfx.control.textfield.TextFields;

import java.io.File;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import java.util.stream.Collectors;


/**
 * The GymnasticsAppMainView class represents the main view (home screen) of the Gymnastics Professor Application.
 * It includes FXML-injected components, event handlers, and methods for initializing and handling user interactions.
 */


public class GymnasticsAppMainView {

    // FXML-injected components
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
    private ListView cardListView;

    @FXML
    private HBox searchHBox;

    @FXML
    private Button clearButton;

    @FXML
    private MenuItem printFileAction;

    @FXML
    private Button AddNewLessonPlan;

    @FXML
    private Button printButton;

    @FXML
    private TabPane lessonPlanTabPane;
    @FXML
    private TextArea lessonCustomNotes;
    @FXML
    private TextArea eachLessonEquipment;
    @FXML
    private ComboBox<String> favoritesFilter;

    private CardCollectionView cardCollectionView;

    private SearchCardCollection searchCardCollection;

    private CourseLessonPlan courseLessonPlan;

    private List<LessonPlanView> courseLessonPlanView;

    private int lessonPlanNumber = 0;

    private int selectedLessonPaneNumber;

    private UserPreferencesManager preferencesManager;

    private UndoRedoHandler undoRedoHandler;

    @FXML
    private TextArea coachesNotesTextArea;

    //Set up components with desired features, and integrate event listeners.
    @FXML
    void initialize(){
        searchCardCollection = SearchCardCollection.SearchCardCollectionBuilder.searchBuilder().build();

        courseLessonPlanView = new ArrayList<>();

        courseLessonPlan = new CourseLessonPlan();

        cardCollectionView = new CardCollectionView(mainSearchView,this);//pass mainView instance

        cardCollectionView.switchCardCollectionToMainView();

        setupFilters();

        addEventsListeners();

        TextFields.bindAutoCompletion(mainSearch, CardCollection.possibleSuggestions);

        Screen windowScreen = Screen.getPrimary();

        lpWorkSpace.setMinWidth(windowScreen.getBounds().getWidth() * 0.7);


        lessonPlanTabPane.setMinHeight(windowScreen.getBounds().getHeight() * 0.7);

        undoRedoHandler = new UndoRedoHandler(this);

        addNewLessonTab();

        preferencesManager = new UserPreferencesManager();

    }
    private void printLessonPlan() {
        int selectedLessonPlanIndex = 0;

        courseLessonPlan.print(courseLessonPlan.getCourseLessonPlan().get(selectedLessonPlanIndex));
    }
    /**
     * Sets up the various filters with their respective options.
     */

    private void setupFilters() {
        setupFilter(eventFilter, "Event", Arrays.stream(EventsEnum.values()).map(Enum::name).collect(Collectors.toList()));
        setupFilter(categoryFilter, "Category", Arrays.stream(CategoryEnum.values()).map(Enum::name).collect(Collectors.toList()));
        setupFilter(equipFilter, "Equipment", CardCollection.allCardsEquipment);
        setupFilter(levelFilter, "Level", Arrays.stream(LevelEnum.values()).map(Enum::name).collect(Collectors.toList()));
        setupFilter(genderFilter, "Gender", Arrays.stream(GenderEnum.values()).map(Enum::toString).collect(Collectors.toList()));
        setupFilter(modelSexFilter, "Model Sex", Arrays.stream(ModelSexEnum.values()).map(Enum::toString).collect(Collectors.toList()));
        setupFilter(favoritesFilter, "Favorites", Arrays.asList("Show", "Don't Show"));
    }

    private void setupFilter(ComboBox<String> filter, String promptText, List<String> items) {
        filter.setPromptText(promptText);
        filter.getItems().add(promptText);
        filter.getItems().addAll(items);
    }


    /**
     * Adds event listeners to the relevant components, such as buttons and filter options.
     */

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
        favoritesFilter.setOnAction(buttonHandler);
        clearFilter.setOnAction(clearHandler);
    }

    /**
     * Handles the action when the "Print" button is clicked.
     *
     * @param event The ActionEvent triggered by the button click.
     */

    @FXML
    private void handlePrintAction(ActionEvent event) {
        PrintCard.print(courseLessonPlan.getCourseLessonPlanList());
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
                case "favoritesFilter":
                    searchCardCollection.setCardIsFavorited(favoritesFilter.getSelectionModel().getSelectedItem());
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
            favoritesFilter.getSelectionModel().select(0);
            clearSearchBuilder();
            undoRedoHandler.saveState();
        }
    };

    private void clearSearchBuilder() {
        searchCardCollection.setCardEvent(null);
        searchCardCollection.setCardCategory(null);
        searchCardCollection.setCardEquipment(null);
        searchCardCollection.setCardLevel(null);
        searchCardCollection.setCardGender(null);
        searchCardCollection.setCardModelSex(null);
        searchCardCollection.setCardIsFavorited(null);
        cardCollectionView.initializeMainSearchView(CardCollection.cardCollection);
    }

    @FXML
    private void menuActionSaveAs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Course Plan");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Lesson Plan (*.courselessonplan)", "*.courselessonplan");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = mainSearchView.getScene().getWindow();
        File chosenFile = fileChooser.showSaveDialog(mainWindow);
        saveCurrentCourseToFile(chosenFile);
        UserPreferencesManager.addRecentFile(chosenFile.getAbsolutePath());

    }

    private void saveCurrentCourseToFile(File chosenFile) {
        if (chosenFile != null) {
            try {
                courseLessonPlan.saveCoursePlan(chosenFile);
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Error saving lesson plan: " + chosenFile).show();
            }
        }
    }

    @FXML
    void menuUndoAction(ActionEvent event) {
        if (!undoRedoHandler.undoStackEmpty())
            System.out.println("Came here");
            clearEntireCoursePlan();
        undoRedoHandler.undo();
    }

    @FXML
    void menuRedoAction(ActionEvent event) {
        if (!undoRedoHandler.redoStackEmpty())
            clearEntireCoursePlan();
        undoRedoHandler.redo();
    }

    private void clearEntireCoursePlan() {
        for (int i = 0; i < lessonPlanTabPane.getTabs().size(); i++) {
            lessonPlanTabPane.getTabs().get(i).setContent(null);
            courseLessonPlan.getCourseLessonPlanList().get(i).clear();
            courseLessonPlanView.get(i).clearLessonPlanView();
        }
        lessonPlanTabPane.getTabs().clear();
        selectedLessonPaneNumber = 0;
    }

    @FXML
    void menuActionOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Course Plan");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Course Logs (*.courselessonplan)", "*.courselessonplan");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = mainSearchView.getScene().getWindow();
        File chosenFile = fileChooser.showOpenDialog(mainWindow);
        loadLessonPlan(chosenFile);
    }
    public void loadLessonPlan(File chosenFile) {
        if (chosenFile != null) {
            try {
                for (int i = 0; i < lessonPlanTabPane.getTabs().size(); i++) {
                    lessonPlanTabPane.getTabs().get(i).setContent(null);
                    courseLessonPlan.getCourseLessonPlanList().get(i).clear();
                    courseLessonPlanView.get(i).clearLessonPlanView();
                }
                lessonPlanTabPane.getTabs().clear();
                courseLessonPlan = CourseLessonPlan.loadCoursePlan(chosenFile);
                selectedLessonPaneNumber = 0;
                displayLoadFromFile();
                UserPreferencesManager.addRecentFile(chosenFile.getAbsolutePath());

            } catch (IOException ex) {
                new Alert(Alert.AlertType.ERROR, "Error loading course plan: " + chosenFile).show();
            }
        }
    }


    private void displayLoadFromFile() {
        List<LessonPlan> lessonPlans = new ArrayList<>(courseLessonPlan.getCourseLessonPlan());
        selectedLessonPaneNumber = 0;
        lessonPlanNumber = 0;

        for (int i = 0; i < lessonPlans.size(); i++) {
            LessonPlan eachLessonPlan = lessonPlans.get(i);
            addNewLessonTab();
            courseLessonPlanView.get(selectedLessonPaneNumber)
                    .setCoachesNotes(eachLessonPlan.getCoachesNotes());
            for (Card eachCard : new ArrayList<>(eachLessonPlan.getLessonCards())) {
                courseLessonPlanView.get(selectedLessonPaneNumber).addCardToLessonPlanView(eachCard, selectedLessonPaneNumber);
            }
            selectedLessonPaneNumber++;
        }
    }

    public State createMemento() {
        return new State();
    }

    public void restoreState(State canvasState) {
        canvasState.restore();
        displayLoadFromFile();
    }

    @FXML
    private void addNewLessonTab() {
        courseLessonPlanView.add(new LessonPlanView(lessonPlanTabPane));
        courseLessonPlan.addLessonPlan();
        Tab newLessonTab = new Tab("Lesson " + lessonPlanNumber++);
        lessonPlanTabPane.getTabs().add(newLessonTab);
        // Add an event listener for selection changes
        lessonPlanTabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab != null) {
                selectedLessonPaneNumber = lessonPlanTabPane.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    private void clearCurrentLessonPlan(){
        lessonPlanTabPane.getTabs().get(selectedLessonPaneNumber).setContent(null);
        courseLessonPlan.getCourseLessonPlanList().get(selectedLessonPaneNumber).clear();
        courseLessonPlanView.get(selectedLessonPaneNumber).clearLessonPlanView();
        undoRedoHandler.saveState();
    }

    @FXML
    void clearImage(MouseEvent event){
//        lessonPlanListView.getItems().clear(); // Clear all items in the lesson plan list view
//        lessonPlan.clear();
    }
    public void addToLessonPlan(Card mCard) {
        courseLessonPlan.addCardToLessonPlan(selectedLessonPaneNumber, mCard);
        courseLessonPlanView.get(selectedLessonPaneNumber).addCardToLessonPlanView(mCard, selectedLessonPaneNumber);
        courseLessonPlanView.get(selectedLessonPaneNumber).createGridView(selectedLessonPaneNumber);
        undoRedoHandler.saveState();
    }
    @FXML
    public void handlePrintButtonClicked(ActionEvent actionEvent) {
        CourseLessonPlan lessonPlan = new CourseLessonPlan();
        lessonPlan.print(courseLessonPlan.getCourseLessonPlan().get(selectedLessonPaneNumber));
    }

    @FXML
    private void handleSaveLessonPlanAction(ActionEvent event){
        LessonPlan currentLessonPlan = getCurrentLessonPlan();
        if (currentLessonPlan != null) {
            currentLessonPlan.setCoachesNotes(coachesNotesTextArea.getText());
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Lesson Plan");
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Lesson Plan (*.lessonplan)", "*.lessonplan");
                fileChooser.getExtensionFilters().add(filter);
                File chosenFile = fileChooser.showSaveDialog(mainSearchView.getScene().getWindow());
                if (chosenFile != null) {
                    currentLessonPlan.saveLessonPlan(chosenFile);
                }
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }

    private LessonPlan getCurrentLessonPlan() {
        Tab selectedTab= lessonPlanTabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            int selectedIndex = lessonPlanTabPane.getTabs().indexOf(selectedTab);
            return courseLessonPlan.getCourseLessonPlanList().get(selectedIndex);
        }
        return null;
    }

    public class CardListCell extends ListCell<Card> {

        private final ImageView imageView = new ImageView();
        private final Text cardDetails = new Text();
        private final Button removeButton = new Button("Remove");

        private final HBox cardBox = new HBox(10);

        /**
         * The CardListCell class represents a custom ListCell for displaying Card objects in a ListView.
         * It includes an image, card details, and a "Remove" button.
         */
        public CardListCell() {
            removeButton.setOnAction(event -> removeFromLessonPlan());
            cardBox.getChildren().addAll(imageView, cardDetails,removeButton);
        }

        /**
         * Updates the content of the ListCell with the provided Card information.
         *
         * @param card  The Card object to display.
         * @param empty Indicates whether the cell is empty or not.
         */
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

        /**
         * Removes the associated Card from the lesson plan when the "Remove" button is clicked.
         */
        private void removeFromLessonPlan() {
            Card card = getItem();
            if (card != null) {

            }
        }
    }
    public class State {
        private CourseLessonPlan courseLessonPlan;

        public State() {
            courseLessonPlan = (CourseLessonPlan) GymnasticsAppMainView.this.courseLessonPlan.clone();
        }

        public void restore() {
            GymnasticsAppMainView.this.courseLessonPlan = (CourseLessonPlan) courseLessonPlan.clone();
        }
    }

}



