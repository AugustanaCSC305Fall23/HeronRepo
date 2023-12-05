package edu.augustana;

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

public class CardView {
    private Card mCard;
    private GymnasticsAppMainView mainView;
    private TabPane tabView;

    public CardView(Card card, GymnasticsAppMainView mainView) {
        this.mCard = card;

        this.mainView = mainView;
    }

    public CardView(Card card, TabPane tabView) {
        this.mCard = card;
        this.tabView = tabView;
    }

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

    public BorderPane makeCardList() {
        //The BorderPane stringFrame is used instead of an HBox for formatting purposes
        BorderPane stringFrame = new BorderPane();
        Label searchString = new Label();

        //Creates HBox to contain buttons, so they don't stack vertically
        HBox buttonBar = new HBox();

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

            buttonBar.getChildren().addAll(expandCardButton, addToLpButton);
            buttonBar.setSpacing(5);

            stringFrame.setLeft(searchString);
            stringFrame.setRight(buttonBar);
        } catch (Exception e) {
            System.out.print(mCard.getSearchString());
        }

        return stringFrame;
    }

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
        stringFrame.setBottom(vbox);
        vbox.setAlignment(Pos.CENTER);

        return stringFrame;
    }


    private void addToLessonPlan(){
        mainView.addToLessonPlan(mCard);
    }

}
