package edu.augustana.utils;


import edu.augustana.GymnasticsProfessorApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static List<List<String>> readAllCSVFilesInFolder(String folderPath) {
        List<List<String>> allCardsList = new ArrayList<>();

        try {
            Files.list(Paths.get(GymnasticsProfessorApp.class.getResource(folderPath).toURI()))
                    .filter(path -> path.toString().endsWith(".csv"))
                    .forEach(path -> {
                        File file = path.toFile();
                        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                String[] tempArr = line.split(",");
                                allCardsList.add(List.of(tempArr));
                            }
                        } catch (Exception e) {
                            System.out.println("Failed to read file: " + file.getName());
                        }
                    });
        } catch (Exception e) {
            System.out.println("Failed to process folder: " + folderPath);
        }
        return allCardsList;
    }
}
