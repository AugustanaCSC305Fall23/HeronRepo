package edu.augustana;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LessonPlanView {
    private Set<String> lessonEvents = new HashSet<>();

    private List<Card> lessonCards = FXCollections.observableArrayList();
    private TabPane tabPane;

    public LessonPlanView(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void addCardToLessonPlanView(Card card, int selectedPane){
        lessonCards.add(card);
        createGridView(selectedPane);
    }


    public void createGridView(int selectedPane) {
        GridPane gridPane = new GridPane();
        ScrollPane scrollPane = new ScrollPane();

        // Set padding between cells
        gridPane.setHgap(20); // Horizontal gap
        gridPane.setVgap(15); // Vertical gap

        // Set padding around the grid (optional)
        gridPane.setPadding(new Insets(20, 20, 20, 20)); // Top, right, bottom, left padding

        int row = 0, column = 0;
        for (Card card : lessonCards) {
            CardView eachCardView = new CardView(card, this.tabPane);
            gridPane.add(eachCardView.makeCardView(), column, row);

            // Toggle the column index between 0 and 1
            column = (column + 1) % 3;

            // If we've just populated the second column, increment the row index
            if (column == 0) {
                row++;
            }
        }

        scrollPane.setContent(gridPane);
        this.tabPane.getTabs().get(selectedPane).setContent(scrollPane);
    }

}
