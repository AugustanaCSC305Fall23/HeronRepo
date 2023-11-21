package edu.augustana;

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

        Button addToLpButton = new Button("Add");
        addToLpButton.setOnAction(event -> addToLessonPlan());

        //Sets the styling for the add button to the same as standard over the rest of the app
        addToLpButton.setStyle("-fx-border-radius: 100%; -fx-border-color: #000; -fx-background-color: white; -fx-background-radius: 20 20 20 20;");
        addToLpButton.setFont(Font.font("system", FontWeight.BOLD, FontPosture.REGULAR, 12));

        try {
            searchString.setText(mCard.getSearchString());

            //Add and format the search string HBox and add button
            stringFrame.setLeft(searchString);
            stringFrame.setRight(addToLpButton);
        } catch (Exception e) {
            System.out.print(mCard.getSearchString());
        }

        return stringFrame;
    }


    private void addToLessonPlan(){
        mainView.addToLessonPlan(mCard);
    }

}
