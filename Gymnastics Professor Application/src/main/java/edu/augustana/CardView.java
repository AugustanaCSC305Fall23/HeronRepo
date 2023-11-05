package edu.augustana;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CardView {
    private Card mCard;
    private GymnasticsAppMainView mainView;

    public CardView(Card card, GymnasticsAppMainView mainView) {
        this.mCard = card;
        this.mainView = mainView;
    }

    public VBox makeCardView() {
        VBox vBox = new VBox();
        ImageView imageView = new ImageView();
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> addToLessonPlan());
        vBox.setMinWidth(500);
        vBox.setMinHeight(500);

        try {

            // Load the image from the resource path

            Image image = new Image(GymnasticsProfessorApp.class.getResource("DEMO1Pack/" + mCard.getCardImage()).toString());
            // Create an ImageView and set the image
            imageView = new ImageView(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);

            // Add the ImageView to the VBox
            vBox.getChildren().add(imageView);
            vBox.getChildren().add(addButton);
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
