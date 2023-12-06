package edu.augustana;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class UserPreferencesManager {
    private static final String RECENT_FILES_KEY = "recentFiles";

    private static Preferences preferences = Preferences.userNodeForPackage(UserPreferencesManager.class);


    public static void saveRecentFilesToPreferences(List<String> recentFiles) {
        preferences.put(RECENT_FILES_KEY, String.join(",", recentFiles));
    }

    public static List<String> getRecentFiles() {
        String recentFilesString = preferences.get(RECENT_FILES_KEY, "");
        System.out.println("recentFileString is " + recentFilesString);
        if (recentFilesString.isEmpty()) {
            return new ArrayList<>();
        } else {
            return List.of(recentFilesString.split(","));
        }
    }

    public static void addRecentFile(String fileName) {
        List<String> recentFiles = new ArrayList<>(getRecentFiles());
        recentFiles.add(fileName);
        System.out.println("fileName is " + fileName);
        if (recentFiles.size() > 5) {
            recentFiles.remove(0);
        }
        saveRecentFilesToPreferences(recentFiles);

    }
}