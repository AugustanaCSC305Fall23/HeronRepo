package edu.augustana;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import javafx.collections.FXCollections;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.geometry.Pos;
import javafx.print.PageLayout;

import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javafx.scene.Node;

import javafx.scene.control.Label;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Transform;

import java.io.*;

import java.util.ArrayList;

import java.util.List;

public class CourseLessonPlan {
    private List<LessonPlan> courseLessonPlan = new ArrayList<>();
    private TabPane lessonPlanTabPane;

    public CourseLessonPlan(TabPane lessonPlanTabPane) {
        this.lessonPlanTabPane = lessonPlanTabPane;
    }

    public void addLessonPlan() {
        courseLessonPlan.add(new LessonPlan());
    }

    public void addCardToLessonPlan(int selectedLessonPlan, Card card) {
        courseLessonPlan.get(selectedLessonPlan).add(card);
    }
    public void removeCardFromLessonPlan(int lessonPlanIndex, Card card) {
        courseLessonPlan.get(lessonPlanIndex).remove(card);
    }
    public List<LessonPlan> getCourseLessonPlanList() {
        return courseLessonPlan;
    }

    public static CourseLessonPlan loadCoursePlan(File logFile) throws IOException {
        FileReader reader = new FileReader(logFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,CourseLessonPlan.class);

    }
    public Node generatePrintableContent(LessonPlan lessonPlan) {
        VBox printableContent = new VBox();

        for (Card card : lessonPlan.getLessonCards()) {

            HBox cardLayout = new HBox();  // Use HBox for landscape orientation

            cardLayout.setSpacing(5);
            cardLayout.setAlignment(Pos.CENTER);

            Node cardNode = card.getVisualizationNode();

            String imagePath = card.getCardImage();

            if (imagePath != null && !imagePath.isEmpty()) {
                String imageFullPath = "file:src/main/resources/edu/augustana/DEMO1Pack/" + imagePath.replace(" ", "%20");

                ImageView imageView = new ImageView(imageFullPath);

                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                cardLayout.getChildren().add(imageView);
            }

            cardLayout.getChildren().addAll(cardNode);

            printableContent.getChildren().add(cardLayout);
        }
        printableContent.setRotate(90);
        return printableContent;
    }

    public void printLessonPlan() {
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null && job.showPrintDialog(null)) {
            PageLayout pageLayout = job.getPrinter().getDefaultPageLayout();
            double printableWidth = pageLayout.getPrintableWidth()*1.1;
            double printableHeight = pageLayout.getPrintableHeight()*1.4;

            WritableImage tabPaneImage = captureTabPaneScreenshot();

            if (tabPaneImage != null) {
                VBox combinedContent = new VBox();
                combinedContent.getChildren().addAll(new ImageView(tabPaneImage));

                combinedContent.setScaleX(printableWidth / combinedContent.getBoundsInParent().getWidth());
                combinedContent.setScaleY(printableHeight / combinedContent.getBoundsInParent().getHeight());

                boolean success = job.printPage(combinedContent);

                if (success) {
                    job.endJob();
                }
            }
        }
    }

    private WritableImage captureTabPaneScreenshot() {
        if (lessonPlanTabPane != null) {
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setDepthBuffer(true);
            int lessonPlanWidth = (int) lessonPlanTabPane.getWidth();
            int lessonPlanHeight = (int) lessonPlanTabPane.getHeight();
            WritableImage writableImage = new WritableImage(lessonPlanWidth,lessonPlanHeight);
            lessonPlanTabPane.snapshot(parameters, writableImage);

            // Rotate the image to landscape orientation
            WritableImage rotatedImage = new WritableImage((int) writableImage.getHeight(), (int) writableImage.getWidth());
            SnapshotParameters rotateParameters = new SnapshotParameters();
            rotateParameters.setTransform(Transform.rotate(90, 0, 0));
            ImageView imageView = new ImageView(writableImage);
            imageView.setRotate(0);
            imageView.snapshot(rotateParameters, rotatedImage);

            return rotatedImage;
        } else {
            return null;
        }
    }





    public void print(LessonPlan lessonPlan) {
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null && job.showPrintDialog(null)) {

            PageLayout pageLayout = job.getPrinter().getDefaultPageLayout();

            double printableWidth = pageLayout.getPrintableWidth();

            double printableHeight = pageLayout.getPrintableHeight();

            double cardWidth = printableWidth / 2;

            double cardHeight = printableHeight / 2;

            Node printableContent = generatePrintableContent(lessonPlan);

            boolean success = job.printPage(printableContent);

            if (success) {
                job.endJob();
            }
        }
    }



    public List<LessonPlan> getCourseLessonPlan() {
        return courseLessonPlan;
    }

    public void saveCoursePlan(File saveFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String serializedLessonPlan = gson.toJson(this);

        PrintWriter writer = new PrintWriter(new FileWriter(saveFile));

        writer.println(serializedLessonPlan);

        printLessonPlan();

        writer.close();
    }
}
