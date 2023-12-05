package edu.augustana;

import java.util.prefs.Preferences;

public class UserPreferencesManager {
    private static final String RECENT_FILES_KEY = "recentFiles";

    private Preferences preferences;

    public UserPreferencesManager() {
        this.preferences = Preferences.userNodeForPackage(UserPreferencesManager.class);
    }

    public void saveRecentFilesToPreferences(String[] recentFiles) {
        preferences.put(RECENT_FILES_KEY, String.join(",", recentFiles));
    }

    public String[] getRecentFiles() {
        String recentFilesString = preferences.get(RECENT_FILES_KEY, "");
        return recentFilesString.split(",");
    }
}
