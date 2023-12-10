package edu.augustana;

import edu.augustana.Card;
import edu.augustana.CourseLessonPlan;
import javafx.scene.control.TabPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LessonPlanViewTest {

    private CourseLessonPlan courseLessonPlan;

    @BeforeEach
    public void setUp() {
        TabPane tabPane = new TabPane();
        courseLessonPlan = new CourseLessonPlan(tabPane);
        courseLessonPlan.addLessonPlan();
    }

    @Test
    public void testPrintLessonPlan() {

        Card card1 = Card.CardBuilder.cardBuilder().setCardCode("1").setCardTitle("Card 1").build();
        Card card2 = Card.CardBuilder.cardBuilder().setCardCode("2").setCardTitle("Card 2").build();

        courseLessonPlan.addCardToLessonPlan(0, card1);
        courseLessonPlan.addCardToLessonPlan(0, card2);

        int totalCardsInLessonPlanBeforePrinting = courseLessonPlan.getCourseLessonPlanList().get(0).getLessonCards().size();

        courseLessonPlan.printLessonPlan();

        int totalCardsInLessonPlanAfterPrinting = courseLessonPlan.getCourseLessonPlanList().get(0).getLessonCards().size();

        assertEquals(totalCardsInLessonPlanBeforePrinting, totalCardsInLessonPlanAfterPrinting);
    }
}
