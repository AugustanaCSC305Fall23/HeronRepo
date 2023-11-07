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




    public void createGridView(int selectedPane) {
        this.tabPane.getTabs().get(selectedPane).setContent(null);
        // Create a VBox to hold both the title and the grid
        VBox layout = new VBox(10); // 10 is the spacing between elements
        layout.setPadding(new Insets(20, 20, 20, 20)); // Padding around the VBox

        // Create a ScrollPane for the grid
        ScrollPane scrollPane = new ScrollPane();

        // Optional: Make ScrollPane fit the width of the content
        scrollPane.setFitToWidth(true);

        // Assume lessonEvents is a List<String> of event titles
        for (String cardEvent : lessonEvents) {
            // Create a label for the event title and add it to the layout
            Label titleLabel = new Label(cardEvent);
            titleLabel.getStyleClass().add("event-title"); // Add a style class for CSS (optional)

            // Add the title label to the VBox layout
            layout.getChildren().add(titleLabel);
            // Create a GridPane for cards
            GridPane gridPane = new GridPane();
            gridPane.setHgap(20); // Horizontal gap
            gridPane.setVgap(15); // Vertical gap
            // Populate the GridPane with cards that match the event
            int row = 0, column = 0;
            for (Card card : lessonCards) {
                if (SearchCardCollection.isEqualSubsequence(cardEvent, card.getCardEvent())) {
                    CardView eachCardView = new CardView(card, this.tabPane);
                    gridPane.add(eachCardView.makeCardView(), column, row);
                    Button removeButton = new Button("Remove");
                    removeButton.setOnAction(event -> removeCardFromLessonPlanView(card, selectedPane));
                    gridPane.add(removeButton, column, row);

                    column = (column + 1) % 3;
                    if (column == 0) {
                        row++;
                    }
                }
            }
            layout.getChildren().add(gridPane);
        }
        scrollPane.setContent(layout);
        this.tabPane.getTabs().get(selectedPane).setContent(scrollPane);
    }
}
