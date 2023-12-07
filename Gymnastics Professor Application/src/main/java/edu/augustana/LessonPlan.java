package edu.augustana;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

public class LessonPlan {
    private List<Card> lessonCards = FXCollections.observableArrayList();
    private String coachesNotes;
    public void add(Card mCard) {
        lessonCards.add(mCard);
    }
    public void remove(Card card) {
        lessonCards.remove(card);
    }
    public void clear(){
        lessonCards.clear();
    }

    public static LessonPlan loadLessonPlan(File logFile) throws IOException {
        FileReader reader = new FileReader(logFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,LessonPlan.class);
    }

    public String getCoachesNotes(){
        return coachesNotes;
    }
    public void setCoachesNotes(String coachesNotes){
        this.coachesNotes = coachesNotes;
    }
    public List<Card> getLessonCards() {
        return  lessonCards;
    }

    public void saveLessonPlan(File saveFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedLessonPlan = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(saveFile));
        writer.println(serializedLessonPlan);
        writer.close();
    }

}
