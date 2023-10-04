package edu.augustana.utils;


import edu.augustana.GymnasticsProfessorApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static List<List<String>> readCSVFile(String csvFile) {
        List<List<String>> allCardsList = new ArrayList<>();
        try {
            File file = new File(GymnasticsProfessorApp.class.getResource(csvFile + ".csv").toURI());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String[] tempArr;

            while((line = bufferedReader.readLine()) != null) {
                tempArr = line.split(",");
                allCardsList.add(List.of(tempArr));
            }
            bufferedReader.close();


        }catch (Exception e)
        {
            System.out.println("Failed to read");
        } finally {
            return allCardsList;
        }
    }
}
