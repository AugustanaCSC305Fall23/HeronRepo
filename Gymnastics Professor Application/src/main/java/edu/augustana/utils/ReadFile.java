package edu.augustana.utils;


import edu.augustana.GymnasticsProfessorApp;

import java.io.*;
import java.net.URI;
import java.net.URL;
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

    // Writes content to a file
    public static void writeToFile(List<String> content) {
        try (FileWriter writer = new FileWriter("src/main/resources/edu/augustana/favorites.txt")) {
            for (String cardCode : content) {
                writer.write(cardCode + System.lineSeparator()); // Append each string in a new line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads content from a file
    public static List<String> readFileToList() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(GymnasticsProfessorApp.class.getResourceAsStream("favorites.txt")))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
