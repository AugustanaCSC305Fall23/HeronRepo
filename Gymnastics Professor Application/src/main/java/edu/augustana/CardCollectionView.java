package edu.augustana;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.util.List;

public class CardCollectionView {
    GridPane mGridPane;

    List<Card> mSearchCardCollectionList;

    public CardCollectionView(GridPane gridPane) {
        CardCollection.createCardCollection();
        this.mGridPane = gridPane;
    }

    public void switchCardCollectionToSearchView(List<Card> searchList) {
        mSearchCardCollectionList = searchList;
        initializeMainSearchView(searchList);
    }

    public void switchCardCollectionToMainView(){
        initializeMainSearchView(CardCollection.cardCollection);
    }


    void initializeMainSearchView(List<Card> cardCollection) {
        // Assuming you have a list of Card objects named 'cardList'
        int maxColumns = 3;  // Number of columns in the GridPane
        int currentColumn = 0;  // Initialize the current column
        int currentRow = 0;  // Initialize the current row



        Screen windowScreen = Screen.getPrimary();
        this.mGridPane.setMinWidth(windowScreen.getBounds().getWidth() * 0.5);
        this.mGridPane.setMinWidth(windowScreen.getBounds().getWidth() * 0.5);

        this.mGridPane.getChildren().clear();

        // Dynamically add rows based on the number of cards
        int numRows = (cardCollection.size() + maxColumns - 1) / maxColumns;
        for (int i = 0; i < numRows; i++) {
            this.mGridPane.addRow(i);
        }
        for (Card card : cardCollection) {
            CardView cardView = new CardView(card);
            VBox cardBox = cardView.makeCardView();

            // Add the cardBox to the GridPane at the current row and column
            this.mGridPane.add(cardBox, currentColumn, currentRow);


            // Increment the column, and if it exceeds the maximum, go to the next row
            currentColumn++;
            if (currentColumn >= maxColumns) {
                currentColumn = 0;
                currentRow++;
            }
        }

    }
}
