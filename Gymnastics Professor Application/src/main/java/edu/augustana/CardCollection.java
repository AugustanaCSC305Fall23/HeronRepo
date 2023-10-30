package edu.augustana;

import edu.augustana.utils.ReadFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardCollection {

    public static List<Card> cardCollection = new ArrayList<Card>();

    public static Set<String> allCardsEquipment = new HashSet<>();

    public static Set<String> possibleSuggestions = new HashSet<>();

    public static void createCardCollection()
    {
        List<List<String>> cardCollectionStringList = ReadFile.readCSVFile("DEMO1");
        List<String> tempEquipment = new ArrayList<>();
        List<String> tempKeywords = new ArrayList<>();
        List<String> tempLevelList = new ArrayList<>();
        int i = -1;

        for (List<String> cardString: cardCollectionStringList) {
            i++;
            if (i == 0)
                continue;
            List<String> updatedTempEquipment = new ArrayList<>();
            tempEquipment = List.of(cardString.get(9).split(","));
            for (String equipment : tempEquipment)
            {
                if (equipment.charAt(0) == '"'){
                    equipment = equipment.substring(1);
                }
                updatedTempEquipment.add(equipment);
                allCardsEquipment.add(equipment);
            }
            possibleSuggestions.add(cardString.get(3));
            tempKeywords = List.of(cardString.get(10).split(","));
            tempLevelList = List.of(cardString.get(8).split(","));
            Card newCard = Card
                    .CardBuilder
                    .cardBuilder()
                    .setCardCode(cardString.get(0))
                    .setCardEvent(cardString.get(1))
                    .setCardCategory(cardString.get(2))
                    .setCardTitle(cardString.get(3))
                    .setCardImage(cardString.get(5))
                    .setCardGender(cardString.get(6))
                    .setCardModelSex(cardString.get(7))
                    .setCardLevel(tempLevelList)
                    .setCardEquipment(updatedTempEquipment)
                    .setCardKeywords(tempKeywords)
                    .build();

            cardCollection.add(newCard);

        }
    }
}
