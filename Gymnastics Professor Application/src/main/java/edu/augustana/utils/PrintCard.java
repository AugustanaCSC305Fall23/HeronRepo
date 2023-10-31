package edu.augustana.utils;

import edu.augustana.Card;
import edu.augustana.GymnasticsProfessorApp;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;

public class PrintCard {

    public static void print(List<Card> lessonPlanCards) {
        Printer printer = Printer.getDefaultPrinter();
        PrinterJob printerJob = PrinterJob.createPrinterJob(printer);

        if (printerJob != null) {
            boolean success = true;
            for (Card eachCard : lessonPlanCards) {
                URL imageUrl = GymnasticsProfessorApp.class.getResource("DEMO1Pack/" + eachCard.getCardImage());
                if (imageUrl != null) {
                    System.out.println(imageUrl.toString());
                    Image eachCardImg = new Image(imageUrl.toString());
                    ImageView imageView = new ImageView(eachCardImg);
                    imageView.setFitWidth(500);
                    imageView.setFitHeight(500);
                    imageView.setFitWidth(printer.getDefaultPageLayout().getPrintableWidth()); // Fit image to printable width
                    success = printerJob.printPage(imageView);
                    if (!success) {
                        System.out.println("Failed to print image: " + eachCard.getCardImage());
                        break; // Exit the loop if printing fails
                    }
                } else {
                    System.out.println("Image not found: " + eachCard.getCardImage());
                }
            }
            printerJob.endJob(); // End the print job outside of the loop
        } else {
            System.out.println("Could not create a print job");
        }
    }

}
