package edu.augustana;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseLessonPlanTest {

    @Test
    void testAddLessonPlan() {
        CourseLessonPlan courseLessonPlan = new CourseLessonPlan();

        assertEquals(0, courseLessonPlan.getCourseLessonPlanList().size());

        courseLessonPlan.addLessonPlan();

        assertEquals(1, courseLessonPlan.getCourseLessonPlanList().size());
    }

    @Test
    void testAddAndRemoveCardFromLessonPlan() {
        CourseLessonPlan courseLessonPlan = new CourseLessonPlan();

        courseLessonPlan.addLessonPlan();

        Card card = Card.CardBuilder.cardBuilder()
                .setCardCode("C001")
                .setCardTitle("Sample Card Title")
                .build();

        courseLessonPlan.addCardToLessonPlan(0, card);

        LessonPlan lessonPlan = courseLessonPlan.getCourseLessonPlanList().get(0);
        assertEquals(1, lessonPlan.getLessonCards().size());

        courseLessonPlan.removeCardFromLessonPlan(0, card);

        assertEquals(0, lessonPlan.getLessonCards().size());
    }
}