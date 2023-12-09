package edu.augustana;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;

import javafx.scene.layout.HBox;

import javafx.stage.Screen;

import javafx.scene.control.ListView;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Represents the view for displaying and interacting with a collection of cards.
 */
public class CardCollectionView {
    GridPane mGridPane;
    ListView mListView;
    List<Card> mSearchCardCollectionList;
    GymnasticsAppMainView mainView;

    /**
     * Constructs a CardCollectionView.
     *
     * @param listView The ListView component to display cards.
     * @param mainView The main application view.
     */

    public CardCollectionView(ListView listView, GymnasticsAppMainView mainView) {
        CardCollection.createCardCollection();
        this.mListView = listView;
        this.mainView = mainView;//to set the main view
    }

    public void switchCardCollectionToSearchView(List<Card> searchList) {
        mSearchCardCollectionList = searchList;
        initializeMainSearchView(searchList);
    }

    /**
     * Switches the card collection view to the main view.
     */
    public void switchCardCollectionToMainView(){
        initializeMainSearchView(CardCollection.cardCollection);
    }

    /**
     * Initializes the main search view with a given card collection.
     *
     * @param cardCollection The list of cards to be displayed.
     */
    void initializeMainSearchView(List<Card> cardCollection) {
        Screen windowScreen = Screen.getPrimary();

        this.mListView.setMinWidth(windowScreen.getBounds().getWidth() * 0.3);

        this.mListView.setMinHeight(windowScreen.getBounds().getHeight() * 0.77);

        this.mListView.getItems().clear();

        for (Card card : cardCollection) {

            CardView cardView = new CardView(card,mainView);

            BorderPane cardBox = cardView.makeCardList();

            // Add the cardBox to the ListView
            this.mListView.getItems().add(cardBox);


        }

    }
}
