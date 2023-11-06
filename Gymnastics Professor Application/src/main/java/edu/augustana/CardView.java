package edu.augustana;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;

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

        vBox.setMinWidth(400);
        vBox.setMinHeight(400);

        try {

            // Load the image from the resource path

            Image image = new Image(GymnasticsProfessorApp.class.getResource("DEMO1Pack/" + mCard.getCardImage()).toString());
            // Create an ImageView and set the image
            imageView = new ImageView(image);
            imageView.setFitHeight(400);
            imageView.setFitWidth(400);

            // Add the ImageView to the VBox
            vBox.getChildren().add(imageView);

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
        mainView.addToLessonPlan(mCard);
    }

}
