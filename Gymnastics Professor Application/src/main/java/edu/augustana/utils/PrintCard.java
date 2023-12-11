package edu.augustana.utils;

import edu.augustana.Card;
import edu.augustana.CardCollection;
import edu.augustana.GymnasticsProfessorApp;
import edu.augustana.LessonPlan;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;

public class PrintCard {

    public static void print(List<LessonPlan> courseLessonPlanCards) {
        final int COLUMN_LIMIT = 2;  // Number of columns per page
        final double CARD_WIDTH = 250;
        final double CARD_HEIGHT = 200;

        Printer printer = Printer.getDefaultPrinter();
        PrinterJob printerJob = PrinterJob.createPrinterJob(printer);

        if (printerJob != null) {
            int lessonNumber = 0;

            for (LessonPlan eachLessonPlan : courseLessonPlanCards) {
                GridPane gridPane = new GridPane(); // New GridPane for each lesson
                int rowIndex = 0;
                int columnIndex = 0;

                // Add the lesson label in its own row
                Label lessonLabel = new Label("Lesson " + lessonNumber++);
                gridPane.add(lessonLabel, columnIndex, rowIndex++);

                for (String cardEvent : CardCollection.allCardEvents) {
                    boolean eventExistsInLesson = false;

                    for (Card eachCard : eachLessonPlan.getLessonCards()) {
                        if (SearchCardCollection.isEqualSubsequence(eachCard.getCardEvent(), cardEvent)) {
                            eventExistsInLesson = true;
                            break;
                        }
                    }

                    if (eventExistsInLesson) {
                        Label eventLabel = new Label(cardEvent);
                        columnIndex = 0; // Reset columnIndex for the new event label
                        gridPane.add(eventLabel, columnIndex, rowIndex++);

                        for (Card eachCard : eachLessonPlan.getLessonCards()) {
                            if (SearchCardCollection.isEqualSubsequence(eachCard.getCardEvent(), cardEvent)) {
                                // Start a new row for the next card if column limit is reached
                                if (columnIndex >= COLUMN_LIMIT) {
                                    columnIndex = 0;
                                    rowIndex++;
                                }

                                // Check if adding the next card will exceed page height
                                if ((rowIndex + 1) * CARD_HEIGHT > printer.getDefaultPageLayout().getPrintableHeight()) {
                                    printerJob.printPage(gridPane);
                                    gridPane = new GridPane(); // Create a new GridPane for the next page
                                    rowIndex = 0; // Reset rowIndex for the new page
                                    columnIndex = 0;
                                }

                                Image eachCardImg = new Image(GymnasticsProfessorApp.class.getResource("DEMO1Pack/" + eachCard.getCardImage()).toString());
                                ImageView imageView = new ImageView(eachCardImg);
                                imageView.setFitWidth(CARD_WIDTH);
                                imageView.setFitHeight(CARD_HEIGHT);

                                gridPane.add(imageView, columnIndex++, rowIndex);

                                // Check if we need a new page again after adding the image
                                if ((rowIndex + 1) * CARD_HEIGHT > printer.getDefaultPageLayout().getPrintableHeight()) {
                                    printerJob.printPage(gridPane);
                                    gridPane = new GridPane(); // Create a new GridPane for the next page
                                    rowIndex = 0; // Reset rowIndex for the new page
                                    columnIndex = 0;
                                }
                            }
                        }
                    }
                }

                // Print the current page/lesson
                if (!gridPane.getChildren().isEmpty()) {
                    printerJob.printPage(gridPane);
                }
            }

            printerJob.endJob();
        } else {
            System.out.println("Could not create a print job");
        }
    }

    public static void printText(List<LessonPlan> courseLessonPlanCards) {
        Printer printer = Printer.getDefaultPrinter();
        PrinterJob printerJob = PrinterJob.createPrinterJob(printer);

        if (printerJob != null) {
            int lessonNumber = 0;

            for (LessonPlan eachLessonPlan : courseLessonPlanCards) {
                VBox vBox = new VBox(10); // VBox with a spacing of 10

                // Add the lesson label
                Label lessonLabel = new Label("Lesson " + lessonNumber++);
                vBox.getChildren().add(lessonLabel);

                for (String cardEvent : CardCollection.allCardEvents) {
                    boolean eventExistsInLesson = false;

                    for (Card eachCard : eachLessonPlan.getLessonCards()) {
                        if (SearchCardCollection.isEqualSubsequence(eachCard.getCardEvent(), cardEvent)) {
                            eventExistsInLesson = true;
                            break;
                        }
                    }

                    if (eventExistsInLesson) {
                        Label eventLabel = new Label(cardEvent);
                        vBox.getChildren().add(eventLabel);

                        for (Card eachCard : eachLessonPlan.getLessonCards()) {
                            if (SearchCardCollection.isEqualSubsequence(eachCard.getCardEvent(), cardEvent)) {
                                Label cardTitleLabel = new Label(eachCard.getCardTitle());
                                vBox.getChildren().add(cardTitleLabel);
                            }
                        }
                    }
                }

                // Print the current page/lesson
                if (!vBox.getChildren().isEmpty()) {
                    printerJob.printPage(vBox);
                }
            }

            printerJob.endJob();
        } else {
            System.out.println("Could not create a print job");
        }
    }



}