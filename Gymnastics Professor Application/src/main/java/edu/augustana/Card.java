package edu.augustana;

import javafx.scene.Node;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Represents a card with various attributes.
 */
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

    /**
     * Constructor for the Card class.
     *
     * @param cardBuilder The builder object used to construct the Card.
     */
    public Card(CardBuilder cardBuilder)
    {
        this.cardCode = cardBuilder.cardCode;
        this.cardTitle = cardBuilder.cardTitle;
        this.cardKeywords = cardBuilder.cardKeywords;
        this.cardEquipment = cardBuilder.cardEquipment;
        this.cardLevel = cardBuilder.cardLevel;
        this.cardGender = cardBuilder.cardGender;
        this.cardModelSex = cardBuilder.cardModelSex;
        this.cardCategory = cardBuilder.cardCategory;
        this.cardImage = cardBuilder.cardImage;
        this.cardEvent = cardBuilder.cardEvent;
    }
    /**
     * This is a group of getters method to get the card attributes.
     *
     * @return The card attribute (code, title, etc...)
     */

    public String getCardCode() {
        return cardCode;
    }
    public String getCardTitle() {
        return cardTitle;
    }
    public String getCardEvent() {
        return cardEvent;
    }
    public String getCardCategory() {
        return cardCategory;
    }
    public String getCardGender() {
        return cardGender;
    }
    public String getCardImage() {
        return cardImage;
    }
    public String getCardModelSex() {
        return cardModelSex;
    }
    public List<String> getCardLevel() {
        return cardLevel;
    }
    public List<String> getCardEquipment() {
        return cardEquipment;
    }
    public List<String> getCardKeywords() {
        return cardKeywords;
    }

    /**
     * Creates a visualization node for the card using JavaFX.
     *
     * @return A JavaFX Node representing the card.
     */
    public Node getVisualizationNode() {
        VBox cardNode = new VBox();

        cardNode.setSpacing(5);

        Label cardCodeLabel = new Label("Code: " + cardCode);

        Label cardTitleLabel = new Label("Title: " + cardTitle);

        cardNode.getChildren().addAll(cardCodeLabel, cardTitleLabel);

        cardNode.setPrefWidth(200);

        cardNode.setPrefHeight(150);

        return cardNode;
    }
    /**
     * Overrides the default toString method to provide a string representation of the Card.
     *
     * @return A string representation of the Card.
     */
    @Override
    public String toString() {
        return "Card{" +
                "cardCode='" + cardCode + '\'' +
                ", cardTitle='" + cardTitle + '\'' +
                ", cardEvent='" + cardEvent + '\'' +
                ", cardCategory='" + cardCategory + '\'' +
                ", cardGender='" + cardGender + '\'' +
                ", cardImage='" + cardImage + '\'' +
                ", cardModelSex='" + cardModelSex + '\'' +
                ", cardLevel=" + cardLevel +
                ", cardEquipment=" + cardEquipment +
                ", cardKeywords=" + cardKeywords +
                '}';
    }

    /**
     * Generates a search string for the card.
     *
     * @return A string used for searching the card.
     */
    public String getSearchString() {return cardCode + ": " + cardTitle;} //Returns a string of what will be displayed in the search

    /**
     * Builder class for constructing Card objects.
     */
    public static class CardBuilder {
        private String cardCode;
        private String cardTitle;
        private String cardEvent;
        private String cardCategory;
        private String cardGender;
        private String cardModelSex;
        private String cardImage;
        private List<String> cardLevel;
        private List<String> cardEquipment;
        private List<String> cardKeywords;

        /**
         * Static method to create a new instance of the CardBuilder.
         *
         * @return A new CardBuilder object.
         */
        public static CardBuilder cardBuilder() {
            return new CardBuilder();
        }

        private CardBuilder(){}

        /**
         * These are the setter methods for the card attributes in the builder.
         *
         * @param newCardCode The new card code.
         * @return The CardBuilder instance.
         */

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
            this.cardLevel = cardLevels;
            return this;
        }
        public CardBuilder setCardEquipment(List<String> associatedEquipment) {
            this.cardEquipment = associatedEquipment;
            return this;
        }
        public  CardBuilder setCardKeywords(List<String> associatedKeywords) {
            this.cardKeywords = associatedKeywords;
            return this;
        }

        /**
         * Builds and returns a new Card instance using the current state of the builder.
         *
         * @return A new Card object.
         */
        public Card build()
        {
            return new Card(this);
        }

    }
}
