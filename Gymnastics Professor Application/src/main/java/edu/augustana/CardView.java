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
        BorderPane bp = new BorderPane();
        HBox hBox = new HBox();
        Label label = new Label();
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> addToLessonPlan());
        addButton.setStyle("-fx-border-radius: 100%; -fx-border-color: #000; -fx-background-color: white; -fx-background-radius: 20 20 20 20;");
        addButton.setFont(Font.font("system", FontWeight.BOLD, FontPosture.REGULAR, 12));

        try {
            label.setText(mCard.getSearchString());

            // Add the search string to the HBox
            hBox.getChildren().add(label);

            //Add and format the search string HBox and add button
            bp.setLeft(hBox);
            bp.setRight(addButton);
        } catch (Exception e) {
            System.out.print(mCard.getSearchString());
        }

        return bp;
    }


    private void addToLessonPlan(){
        mainView.addToLessonPlan(mCard);
    }

}
