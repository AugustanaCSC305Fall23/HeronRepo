package edu.augustana;

import edu.augustana.utils.ReadFile;

import java.util.ArrayList;
import java.util.List;

public class CardCollection {

    public static List<Card> cardCollection = new ArrayList<Card>();

    public CardCollection()
    {
        List<List<String>> cardCollectionStringList = ReadFile.readCSVFile("DEMO1");
        List<String> tempEquipment = new ArrayList<>();
        List<String> tempKeywords = new ArrayList<>();

        for (List<String> cardString: cardCollectionStringList) {
            tempEquipment = List.of(cardString.get(9).split(","));
            tempKeywords = List.of(cardString.get(10).split(","));
            Card newCard = Card
                    .CardBuilder
                    .cardBuilder()
                    .setTitle(cardString.get(3))
                    .setCardCode(cardString.get(0))
                    .setEquipment(tempEquipment)
                    .setKeywords(tempKeywords)
                    .build();

            cardCollection.add(newCard);
        }
    }
}
