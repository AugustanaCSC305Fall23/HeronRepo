package edu.augustana;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;

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

    public HBox makeCardList() {
        HBox hBox = new HBox();
        Label label = new Label();
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> addToLessonPlan());

        try {
            label.setText(mCard.getSearchString());

            // Add the ImageView to the VBox
            hBox.getChildren().add(label);
            hBox.getChildren().add(addButton);
        } catch (Exception e) {
            System.out.print(mCard.getSearchString());
        }

        return hBox;
    }


    private void addToLessonPlan(){
        System.out.println("Add to LessonPlan: " + mCard);
        mainView.addToLessonPlan(mCard);
    }

}
