package edu.augustana;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;

public class CardView {
    private Card mCard;

    public CardView(Card card) {
        this.mCard = card;
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
            imageView.setFitHeight(500);
            imageView.setFitWidth(500);

            // Add the ImageView to the VBox
            vBox.getChildren().add(imageView);
            vBox.getChildren().add(addButton);
        } catch (Exception e) {
            System.out.print(mCard.getCardImage());
        }


        return vBox;
    }
    private void addToLessonPlan(){
    }

}
