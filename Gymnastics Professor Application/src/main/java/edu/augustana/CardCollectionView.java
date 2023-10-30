package edu.augustana;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.scene.control.ListView;

import java.util.List;

public class CardCollectionView {
    GridPane mGridPane;

    ListView mListView;

    List<Card> mSearchCardCollectionList;

    public CardCollectionView(ListView listView) {
        CardCollection.createCardCollection();
        this.mListView = listView;
    }

    public void switchCardCollectionToSearchView(List<Card> searchList) {
        mSearchCardCollectionList = searchList;
        initializeMainSearchView(searchList);
    }

    public void switchCardCollectionToMainView(){
        initializeMainSearchView(CardCollection.cardCollection);
    }


    void initializeMainSearchView(List<Card> cardCollection) {


        Screen windowScreen = Screen.getPrimary();
        this.mListView.setMinWidth(windowScreen.getBounds().getWidth() * 0.3);
        this.mListView.setMinHeight(windowScreen.getBounds().getHeight() * 0.8);

        this.mListView.getChildrenUnmodifiable().clear();

        for (Card card : cardCollection) {
            CardView cardView = new CardView(card);
            HBox cardBox = cardView.makeCardList();

            // Add the cardBox to the GridPane at the current row and column
            this.mListView.getItems().add(cardBox);


        }

    }
}
