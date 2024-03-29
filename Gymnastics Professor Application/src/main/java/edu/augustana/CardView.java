package edu.augustana;

import edu.augustana.utils.ReadFile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.TabPane;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.scene.text.Font;

import javafx.scene.text.FontPosture;

import javafx.scene.text.FontWeight;


    /**
     * Represents the view for displaying a single card.
     */
public class CardView {
    private Card mCard;
    private GymnasticsAppMainView mainView;
    private TabPane tabView;
    private Button favButton = new Button("\u2764");

    /**
     * Constructs a CardView.
     *
     * @param card      The card to be displayed.
     * @param mainView  The main application view.
     */

    public CardView(Card card, GymnasticsAppMainView mainView) {
        this.mCard = card;

        this.mainView = mainView;
    }
    /**
     * Constructs a CardView.
     *
     * @param card      The card to be displayed.
     * @param tabView   The TabPane to which the card is associated.
     */
    public CardView(Card card, TabPane tabView) {
        this.mCard = card;
        this.tabView = tabView;
    }
    /**
     * Creates a VBox containing the card's image.
     *
     * @return A VBox containing the card's image.
     */
    public VBox makeCardView() {
        VBox vBox = new VBox();

        ImageView imageView = new ImageView();

        vBox.setMinWidth(300);

        vBox.setMinHeight(300);

        try {

            // Load the image from the resource path
            Image image = new Image(GymnasticsProfessorApp.class.getResource("DEMO1Pack/" + mCard.getCardImage()).toString());
            // Create an ImageView and set the image
            imageView = new ImageView(image);

            imageView.setFitHeight(300);

            imageView.setFitWidth(300);

            // Add the ImageView to the VBox
            vBox.getChildren().add(imageView);
             } catch (Exception e) {
            System.out.print(mCard.getCardImage());
        }

        return vBox;
    }
    /**
     * Creates a BorderPane containing the card's title, buttons, and associated actions.
     *
     * @return A BorderPane containing the card's title, buttons, and actions.
     */
    public BorderPane makeCardList() {
        //The BorderPane stringFrame is used instead of an HBox for formatting purposes
        BorderPane stringFrame = new BorderPane();
        Label searchString = new Label();

        //Creates HBox to contain buttons, so they don't stack vertically
        HBox buttonBar = new HBox();

        // Create the favorite button with a heart icon
         // Unicode for a solid heart

        if (this.mCard.getCardFavorite()) {
            // Optional: Style the button
            favButton.setStyle("-fx-font-size: 24px; " + // Increase font size
                    "-fx-background-color: transparent; " + // Make background transparent
                    "-fx-text-fill: red;");
        }
        else {
            favButton.setStyle("-fx-font-size: 24px; " + // Increase font size
                    "-fx-background-color: transparent; " + // Make background transparent
                    "-fx-text-fill: black;");
        }

        favButton.setOnAction(event -> {
            addRemoveToFavorites();// Change heart color to red
        });


        Button addToLpButton = new Button("Add");
        addToLpButton.setOnAction(event -> addToLessonPlan());

        //Sets the styling for the add button to the same as standard over the rest of the app
        addToLpButton.setStyle("-fx-border-radius: 100%; -fx-border-color: #000; -fx-background-color: white; -fx-background-radius: 20 20 20 20;");
        addToLpButton.setFont(Font.font("system", FontWeight.BOLD, FontPosture.REGULAR, 12));

        Button expandCardButton = new Button("Expand");
        expandCardButton.setOnAction(event -> expandCard(stringFrame));

        //Sets the styling for the expand button to the same as standard over the rest of the app
        expandCardButton.setStyle("-fx-border-radius: 100%; -fx-border-color: #000; -fx-background-color: white; -fx-background-radius: 20 20 20 20;");
        expandCardButton.setFont(Font.font("system", FontWeight.BOLD, FontPosture.REGULAR, 12));

        try {
            searchString.setText(mCard.getSearchString());

            buttonBar.getChildren().addAll(favButton, expandCardButton, addToLpButton);
            buttonBar.setSpacing(5);
            buttonBar.setPadding(new Insets(0, 0, 2, 0));

            stringFrame.setLeft(searchString);
            stringFrame.setRight(buttonBar);
        } catch (Exception e) {
            System.out.print(mCard.getSearchString());
        }

        return stringFrame;
    }
    /**
     * Expands the card to show a larger image and a close button.
     *
     * @param stringFrame The BorderPane representing the card view.
     * @return The modified BorderPane after expanding the card.
     */
    public BorderPane expandCard(BorderPane stringFrame) {
        VBox vbox = new VBox();

        ImageView cardMagnify = new ImageView();

        // Load the image from the resource path
        Image image = new Image(GymnasticsProfessorApp.class.getResource("DEMO1Pack/" + mCard.getCardImage()).toString());
        // Create an ImageView and set the image
        cardMagnify = new ImageView(image);

        //Sets the size of cardMagnify while also keeping the apperance of the cards
        cardMagnify.setFitWidth(400);
        cardMagnify.setFitHeight(310);

        Button closeExpand = new Button("Close");
        closeExpand.setStyle("-fx-border-radius: 100%; -fx-border-color: #000; -fx-background-color: white; -fx-background-radius: 20 20 20 20;");
        closeExpand.setFont(Font.font("system", FontWeight.BOLD, FontPosture.REGULAR, 12));
        closeExpand.setOnAction(event -> vbox.getChildren().clear());

        // Add the ImageView to the VBox
        vbox.getChildren().addAll(cardMagnify, closeExpand);
        vbox.setSpacing(2);
        stringFrame.setBottom(vbox);
        vbox.setAlignment(Pos.CENTER);

        return stringFrame;
    }

    /**
     * Adds the card to the lesson plan.
     */
    private void addToLessonPlan(){
        mainView.addToLessonPlan(mCard);
    }

    private void addRemoveToFavorites()  {
        //remove card
        if (CardCollection.favoritedCards.contains(this.mCard.getCardCode()))
        {
            favButton.setStyle("-fx-font-size: 24px; " + // Increase font size
                    "-fx-background-color: transparent; " + // Make background transparent
                    "-fx-text-fill: black;");
            CardCollection.favoritedCards.remove(this.mCard.getCardCode());
            this.mCard.setCardIsFavorited(false);
        }
        else
        {
            favButton.setStyle("-fx-font-size: 24px; " + // Increase font size
                    "-fx-background-color: transparent; " + // Make background transparent
                    "-fx-text-fill: red;");
            CardCollection.favoritedCards.add(this.mCard.getCardCode());
            this.mCard.setCardIsFavorited(true);

        }
        ReadFile.writeToFile(CardCollection.favoritedCards);
    }

}
