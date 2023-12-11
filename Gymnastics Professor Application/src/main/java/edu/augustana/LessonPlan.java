package edu.augustana;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

/**
 * The LessonPlan class represents a collection of cards and additional information associated with a gymnastics lesson plan.
 * It provides methods for adding, removing, and clearing lesson cards, as well as loading and saving lesson plans from/to files.
 */
public class LessonPlan {
    private List<Card> lessonCards = FXCollections.observableArrayList();

    private String customLessonPlanNotes = "";

    private String coachesNotes;
    /**
     * Adds a card to the lesson plan.
     *
     * @param mCard The Card object to be added to the lesson plan.
     */
    public void add(Card mCard) {
        lessonCards.add(mCard);
    }
    /**
     * Removes a specific card from the lesson plan.
     *
     * @param card The Card object to be removed from the lesson plan.
     */
    public void remove(Card card) {
        lessonCards.remove(card);
    }
    /**
     * Clears all cards from the lesson plan.
     */

    public void clear(){
        lessonCards.clear();
    }

    public static LessonPlan loadLessonPlan(File logFile) throws IOException {
        FileReader reader = new FileReader(logFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,LessonPlan.class);
    }
    /**
     * Retrieves the coaches' notes associated with the lesson plan.
     *
     * @return The coaches' notes for the lesson plan.
     */

    public String getCoachesNotes(){
        return coachesNotes;
    }
    /**
     * Sets the coaches' notes for the lesson plan.
     *
     * @param coachesNotes The coaches' notes to be set for the lesson plan.
     */
    public void setCoachesNotes(String coachesNotes){
        this.coachesNotes = coachesNotes;
    }
    /**
     * Retrieves the list of cards in the lesson plan.
     *
     * @return The list of cards in the lesson plan.
     */

    public String getCustomLessonPlanNotes() {
        return customLessonPlanNotes;
    }

    public void setCustomLessonPlanNotes(String customNotes) {
        customLessonPlanNotes = customNotes;
    }

    public List<Card> getLessonCards() {
        return  lessonCards;
    }

    /**
     * Saves the lesson plan to a specified file.
     *
     * @param saveFile The File object representing the file to which the lesson plan will be saved.
     * @throws IOException If an I/O error occurs during the saving process.
     */
    public void saveLessonPlan(File saveFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedLessonPlan = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(saveFile));
        writer.println(serializedLessonPlan);
        writer.close();
    }

}
