package edu.augustana;

import edu.augustana.utils.SearchCardCollection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LessonPlanView {
    private Set<String> lessonEvents = new HashSet<>();
    private List<Card> lessonCards = FXCollections.observableArrayList();
    private TabPane tabPane;
    @FXML
    private Button clearButton;

    private String coachesNotes;

    public LessonPlanView(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void addCardToLessonPlanView(Card card, int selectedPane){

        lessonCards.add(card);
        lessonEvents.add(card.getCardEvent());
        createGridView(selectedPane);
    }
    public void removeCardFromLessonPlanView(Card card, int selectedPane) {
        lessonCards.remove(card);

        String removedEvent = card.getCardEvent();

        boolean hasRemainingCardsWithEvent = lessonCards.stream()
                .anyMatch(c -> c.getCardEvent().equals(removedEvent));

        if (!hasRemainingCardsWithEvent) {
            lessonEvents.remove(removedEvent);
        }

        createGridView(selectedPane);
    }

    public void clearLessonPlanView() {
        lessonCards.clear();
        lessonEvents.clear();
    }
    public void setCoachesNotes(String coachesNotes){
        this.coachesNotes = coachesNotes;
    }

    public void createGridView(int selectedPane) {
        this.tabPane.getTabs().get(selectedPane).setContent(null);

        VBox layout = createMainLayout();

        ScrollPane scrollPane = createScrollPane(layout);

        for (String cardEvent : lessonEvents) {
            layout.getChildren().addAll(createEventTitleLabel(cardEvent), createCardGrid(cardEvent, selectedPane));
        }

        scrollPane.setContent(layout);
        this.tabPane.getTabs().get(selectedPane).setContent(scrollPane);

        if (coachesNotes != null && !coachesNotes.isEmpty()) {
            layout.getChildren().add(createCoachesNotesLabel());
        }
    }

    private VBox createMainLayout() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        return layout;
    }

    private ScrollPane createScrollPane(VBox layout) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    private Label createEventTitleLabel(String cardEvent) {
        Label titleLabel = new Label(cardEvent);
        titleLabel.getStyleClass().add("event-title");
        return titleLabel;
    }

    private GridPane createCardGrid(String cardEvent, int selectedPane) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(15);

        int row = 0, column = 0;

        for (Card card : lessonCards) {
            if (SearchCardCollection.isEqualSubsequence(cardEvent, card.getCardEvent())) {
                CardView eachCardView = new CardView(card, this.tabPane);
                gridPane.add(eachCardView.makeCardView(), column, row);
                gridPane.add(createRemoveButton(card, selectedPane), column, row);

                column = (column + 1) % 3;
                if (column == 0) {
                    row++;
                }
            }
        }

        return gridPane;
    }

    private Button createRemoveButton(Card card, int selectedPane) {
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> removeCardFromLessonPlanView(card, selectedPane));
        return removeButton;
    }

    private Label createCoachesNotesLabel() {
        Label coachesNotesLabel = new Label("Coaches' Notes: " + coachesNotes);
        coachesNotesLabel.getStyleClass().add("coaches-notes-label");
        return coachesNotesLabel;
    }

}
