package edu.augustana;

import java.util.List;

public class Card {
    private String cardCode;
    private String cardTitle;

    private String cardEvent;

    private String cardCategory;

    private String cardGender;

    private String cardImage;

    private String cardModelSex;

    private List<String> cardLevel;

    private List<String> cardEquipment;

    private List<String> cardKeywords;

    public Card(CardBuilder cardBuilder)
    {
        this.cardCode = cardBuilder.cardCode;
        this.cardTitle = cardBuilder.cardTitle;
        this.cardKeywords = cardBuilder.keywords;
        this.cardEquipment = cardBuilder.equipment;
        this.cardLevel = cardBuilder.cardLevel;
        this.cardGender = cardBuilder.cardGender;
        this.cardModelSex = cardBuilder.cardModelSex;
        this.cardCategory = cardBuilder.cardCategory;
        this.cardImage = cardBuilder.cardImage;
        this.cardEvent = cardBuilder.cardEvent;
    }


    public static class CardBuilder {
        private String cardCode;

        private String cardTitle;

        private String cardEvent;

        private String cardCategory;

        private String cardGender;

        private String cardModelSex;

        private String cardImage;

        private List<String> cardLevel;

        private List<String> equipment;

        private List<String> keywords;

        public static CardBuilder cardBuilder() {
            return new CardBuilder();
        }

        private CardBuilder(){}

        public CardBuilder setCardCode(String newCardCode) {
            this.cardCode = newCardCode;
            return this;
        }

        public CardBuilder setCardGender(String newGender) {
            this.cardGender = newGender;
            return this;
        }

        public CardBuilder setCardModelSex(String modelGender){
            this.cardModelSex = modelGender;
            return this;
        }

        public CardBuilder setCardTitle(String newCardTitle) {
            this.cardTitle = newCardTitle;
            return this;
        }

        public CardBuilder setCardEvent(String cardEvent) {
            this.cardEvent = cardEvent;
            return this;
        }

        public CardBuilder setCardCategory(String cardCategory) {
            this.cardCategory = cardCategory;
            return this;
        }

        public CardBuilder setCardImage(String cardImage){
            this.cardImage = cardImage;
            return this;
        }

        public CardBuilder setCardLevel(List<String> cardLevels){
            for (String level: cardLevels) {
                this.cardLevel.add(level);
            }
            return this;
        }

        public CardBuilder setCardEquipment(List<String> associatedEquipment) {
            this.equipment = associatedEquipment;
            return this;
        }

        public  CardBuilder setCardKeywords(List<String> associatedKeywords) {
            this.keywords = associatedKeywords;
            return this;
        }

        public Card build()
        {
            return new Card(this);
        }

    }
}
