package edu.augustana.utils;

import edu.augustana.Card;
import edu.augustana.CardCollection;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SearchCardCollection {
    @Nullable
    private String cardEvent;
    @Nullable
    private String cardCategory;
    @Nullable
    private String cardEquipment;
    @Nullable
    private String cardLevel;
    @Nullable
    private String cardGender;

    public String getCardTitleCode() {
        return cardTitleCode;
    }

    public void setCardTitleCode(String cardTitleCode) {
        this.cardTitleCode = cardTitleCode;
    }

    @Nullable
    private String cardTitleCode;

    public SearchCardCollection(SearchCardCollectionBuilder searchCardCollectionBuilder) {
        this.cardEvent = searchCardCollectionBuilder.cardEvent;
        this.cardCategory = searchCardCollectionBuilder.cardCategory;
        this.cardEquipment = searchCardCollectionBuilder.cardEquipment;
        this.cardLevel = searchCardCollectionBuilder.cardLevel;
        this.cardGender = searchCardCollectionBuilder.cardGender;
    }

    public String getCardEvent() {
        return cardEvent;
    }

    public void setCardEvent(String cardEvent) {
        this.cardEvent = cardEvent;
    }

    public String getCardCategory() {
        return cardCategory;
    }

    public void setCardCategory(String cardCategory) {
        this.cardCategory = cardCategory;
    }

    public String getCardEquipment() {
        return cardEquipment;
    }

    public void setCardEquipment(String cardEquipment) {
        this.cardEquipment = cardEquipment;
    }

    public String getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(String cardLevel) {
        this.cardLevel = cardLevel;
    }

    public String getCardGender() {
        return cardGender;
    }

    public void setCardGender(String cardGender) {
        this.cardGender = cardGender;
    }

    public List<Card> searchCards() {
        List<Card> resultsFromSearch = CardCollection
                .cardCollection
                .stream()
                .filter(
                        eachCard -> (
                                        (this.cardEvent == null  ||  this.cardEvent == "ALL" || isEqualSubsequence(eachCard.getCardEvent(), this.cardEvent))
                                &&
                                        (this.cardCategory == null || isEqualSubsequence(eachCard.getCardCategory(), this.cardCategory))
                                &&
                                        (this.cardEquipment == null || (eachCard.getCardEquipment().contains(this.cardEquipment)))
                                &&
                                        (this.cardLevel == null || (eachCard.getCardLevel().contains(this.cardLevel)))
                                &&
                                        (this.cardGender == null || this.cardGender == "ALL" || isEqualSubsequence(eachCard.getCardGender(),this.cardGender))
                                &&
                                                (
                                        (this.cardTitleCode == null || isEqualSubsequence(eachCard.getCardCode(), this.cardTitleCode))
                                ||
                                        (this.cardTitleCode == null || isEqualSubsequence(eachCard.getCardTitle(), this.cardTitleCode))
                                                )
                        )
                )
                .collect(Collectors.toList());

        return resultsFromSearch;
    }

    public static class SearchCardCollectionBuilder {
        @Nullable
        private String cardEvent;

        @Nullable private String cardCategory;

        @Nullable private String cardEquipment;

        @Nullable private String cardLevel;

        @Nullable private String cardGender;
        public static SearchCardCollectionBuilder searchBuilder() {
            return new SearchCardCollectionBuilder();
        }
        private SearchCardCollectionBuilder(){};

        public SearchCardCollectionBuilder setCardEvent(String cardEvent) {
            this.cardEvent = cardEvent;
            return this;
        }

        public SearchCardCollectionBuilder setCardCategory(String cardCategory) {
            this.cardCategory = cardCategory;
            return this;
        }

        public SearchCardCollectionBuilder setCardEquipment(String cardEquipment) {
            this.cardEquipment = cardEquipment;
            return this;
        }

        public SearchCardCollectionBuilder setCardLevel(String cardLevel) {
            this.cardLevel = cardLevel;
            return this;
        }

        public SearchCardCollectionBuilder setCardGender(String cardGender) {
            this.cardGender = cardGender;
            return this;
        }

        public SearchCardCollection build(){
            return new SearchCardCollection(this);
        }


    }

    public static boolean isEqualSubsequence(String A, String B) {
        A = A.toLowerCase();
        B = B.toLowerCase();

        int i = 0, j = 0;

        // Check if A is a subsequence of B
        while (i < A.length() && j < B.length()) {
            if (A.charAt(i) == B.charAt(j)) {
                i++;
            }
            j++;
        }
        if (i == A.length()) return true; // A is a subsequence of B

        // Reset pointers and check if B is a subsequence of A
        i = 0;
        j = 0;
        while (i < B.length() && j < A.length()) {
            if (B.charAt(i) == A.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == B.length(); // return true if B is a subsequence of A, else false
    }

}
