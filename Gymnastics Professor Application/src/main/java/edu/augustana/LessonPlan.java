package edu.augustana;

import java.util.ArrayList;
import java.util.List;

public class LessonPlan {

    private List<Card> lessonCards = new ArrayList<>();


    public void add(Card mCard) {
        lessonCards.add(mCard);
    }

    public void remove(Card card) {
        lessonCards.remove(card);
    }
    public void clear(){
        lessonCards.clear();
    }

}
