package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseLessonPlan {
    private List<LessonPlan> courseLessonPlan = new ArrayList<>();

    public void addLessonPlan() {
        courseLessonPlan.add(new LessonPlan());
    }

    public void addCardToLessonPlan(int selectedLessonPlan, Card card) {
        courseLessonPlan.get(selectedLessonPlan).add(card);
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
            VBox cardLayout = new VBox();
            cardLayout.setSpacing(10);


            Node cardNode = card.getVisualizationNode();

            String imagePath = card.getCardImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                String imageFullPath = "file:src/main/resources/edu/augustana/DEMO1Pack/" + imagePath.replace(" ", "%20");
                ImageView imageView = new ImageView(imageFullPath);
                imageView.setFitWidth(250);
                imageView.setFitHeight(250);
                cardLayout.getChildren().add(imageView);
            }

            cardLayout.getChildren().addAll(cardNode);

            printableContent.getChildren().add(cardLayout);
        }

        return printableContent;
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
        writer.close();
    }
}
