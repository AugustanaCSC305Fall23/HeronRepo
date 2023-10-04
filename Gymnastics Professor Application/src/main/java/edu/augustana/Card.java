package edu.augustana;

import java.util.List;

public class Card {
    private String cardCode;

    private String cardTitle;

    private enum gender {
        N, F, M
    }

    private enum modelSex{
        F, M,
    }

    private enum  level{
        ALL, B, AB, I
    }

    private List<String> equipment;

    private List<String> keywords;

    public Card(CardBuilder cardBuilder)
    {
        this.cardCode = cardBuilder.cardCode;
        this.cardTitle = cardBuilder.cardTitle;
        this.keywords = cardBuilder.keywords;
        this.equipment = cardBuilder.equipment;
    }


    public static class CardBuilder {
        private String cardCode;

        private String cardTitle;

        private enum gender {
            N, F, M
        }

        private enum modelSex{
            F, M,
        }

        private enum  level{
            ALL, B, AB, I
        }

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

        public CardBuilder setGender(String newGender) {
            return this;
        }

        public CardBuilder setTitle(String newCardTitle) {
            this.cardTitle = newCardTitle;
            return this;
        }

        public CardBuilder setEquipment(List<String> associatedEquipment) {
            this.equipment = associatedEquipment;
            return this;
        }

        public  CardBuilder setKeywords(List<String> associatedKeywords) {
            this.keywords = associatedKeywords;
            return this;
        }

        public Card build()
        {
            return new Card(this);
        }

    }
}
