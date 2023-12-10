package edu.augustana;

import edu.augustana.utils.ReadFile;

import java.net.URISyntaxException;
import java.util.ArrayList;

import java.util.HashSet;

import java.util.List;

import java.util.Set;

/**
 * Represents a collection of cards.
 */
public class CardCollection {

    /**
     * List to store the collection of cards.
     */
    public static List<Card> cardCollection = new ArrayList<Card>();

    /**
     * List to store all equipment associated with cards in the collection.
     */
    public static List<String> allCardsEquipment = new ArrayList<>();

    /**
     * Set to store possible suggestions based on the card collection.
     */
    public static Set<String> possibleSuggestions = new HashSet<>();

    public static List<String> favoritedCards = new ArrayList<>();

    public static Set<String> allCardEvents = new HashSet<>();


    public static void createCardCollection() {
        favoritedCards = ReadFile.readFileToList();

        List<List<String>> cardCollectionStringList = ReadFile.readAllCSVFilesInFolder("CSVFolder");

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

                equipment = equipment.strip();

                updatedTempEquipment.add(equipment);

                if (!allCardsEquipment.contains(equipment)){
                    allCardsEquipment.add(equipment);
                }
            }

            // Add card category to possible suggestions
            possibleSuggestions.add(cardString.get(3));

            // Add to cardEvents
            allCardEvents.add(cardString.get(1));

            // Process keywords and levels data

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
                    .setCardIsFavorited(favoritedCards.contains(cardString.get(0)))
                    .build();

            cardCollection.add(newCard);

        }
    }
}
