package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;

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

    public static CourseLessonPlan loadCoursePlan(File logFile) throws IOException {
        FileReader reader = new FileReader(logFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,CourseLessonPlan.class);
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
