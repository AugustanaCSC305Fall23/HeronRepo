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

/**
 * The CourseLessonPlan class represents a collection of lesson plans in the Gymnastics Professor Application.
 * It provides methods for managing lesson plans, adding and removing cards from lesson plans, and printing lesson plans.
 */
public class CourseLessonPlan {
    private List<LessonPlan> courseLessonPlan = new ArrayList<>();

    public CourseLessonPlan() {
    }

    /**
     * Adds a new lesson plan to the course.
     */
    public void addLessonPlan() {
        courseLessonPlan.add(new LessonPlan());
    }

    /**
     * Adds a card to a specific lesson plan within the course.
     *
     * @param selectedLessonPlan The index of the lesson plan where the card will be added.
     * @param card               The Card object to be added to the lesson plan.
     */
    public void addCardToLessonPlan(int selectedLessonPlan, Card card) {
        courseLessonPlan.get(selectedLessonPlan).add(card);
    }

    /**
     * Removes a card from a specific lesson plan within the course.
     *
     * @param lessonPlanIndex The index of the lesson plan from which the card will be removed.
     * @param card            The Card object to be removed from the lesson plan.
     */
    public void removeCardFromLessonPlan(int lessonPlanIndex, Card card) {
        courseLessonPlan.get(lessonPlanIndex).remove(card);
    }
    /**
     * Retrieves the list of lesson plans in the course.
     *
     * @return The list of lesson plans in the course.
     */

    public List<LessonPlan> getCourseLessonPlanList() {
        return courseLessonPlan;
    }

    public void resetCoursePlanList() {
        courseLessonPlan = new ArrayList<>();
    }

    /**
     * Loads a course plan from a file using Gson.
     *
     * @param logFile The File object representing the file to load the course plan from.
     * @return The CourseLessonPlan object loaded from the file.
     * @throws IOException If an I/O error occurs during file reading.
     */
    public static CourseLessonPlan loadCoursePlan(File logFile) throws IOException {
        FileReader reader = new FileReader(logFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,CourseLessonPlan.class);

    }

    /**
     * Generates a printable content node for a given lesson plan.
     *
     * @param lessonPlan The LessonPlan object for which to generate printable content.
     * @return The Node containing printable content for the lesson plan.
     */
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


    /**
     * Prints a lesson plan using JavaFX PrinterJob.
     *
     * @param lessonPlan The LessonPlan object to be printed.
     */

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

    /**
     * Retrieves the list of lesson plans in the course.
     *
     * @return The list of lesson plans in the course.
     */
    public List<LessonPlan> getCourseLessonPlan() {
        return courseLessonPlan;
    }

    /**
     * Saves the course plan to a file using Gson.
     *
     * @param saveFile The File object representing the file to save the course plan to.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public void saveCoursePlan(File saveFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String serializedLessonPlan = gson.toJson(this);

        PrintWriter writer = new PrintWriter(new FileWriter(saveFile));

        writer.println(serializedLessonPlan);
        writer.close();
    }

    public CourseLessonPlan clone() {
        Gson gson = new Gson();
        String serializedLessonPlan = gson.toJson(this);
        return gson.fromJson(serializedLessonPlan, CourseLessonPlan.class);
    }
}
