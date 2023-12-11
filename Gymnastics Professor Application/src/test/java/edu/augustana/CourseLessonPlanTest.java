package edu.augustana;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseLessonPlanTest {

    @Test
    void testAddLessonPlan() {
        CourseLessonPlan courseLessonPlan = new CourseLessonPlan();

        // Initially, the course should have no lesson plans
        assertEquals(0, courseLessonPlan.getCourseLessonPlanList().size());

        // Adding a lesson plan
        courseLessonPlan.addLessonPlan();

        // After adding, there should be one lesson plan
        assertEquals(1, courseLessonPlan.getCourseLessonPlanList().size());
    }

    @Test
    void testAddAndRemoveCardFromLessonPlan() {
        CourseLessonPlan courseLessonPlan = new CourseLessonPlan();

        // Adding a lesson plan
        courseLessonPlan.addLessonPlan();

        // Creating a sample Card
        Card card = Card.CardBuilder.cardBuilder()
                .setCardCode("C001")
                .setCardTitle("Sample Card Title")
                // Set other card properties as needed
                .build();

        // Adding the card to the first lesson plan
        courseLessonPlan.addCardToLessonPlan(0, card);

        // Retrieving the lesson plan and checking if the card is added
        LessonPlan lessonPlan = courseLessonPlan.getCourseLessonPlanList().get(0);
        assertEquals(1, lessonPlan.getLessonCards().size());

        // Removing the card from the lesson plan
        courseLessonPlan.removeCardFromLessonPlan(0, card);

        // After removal, there should be no cards in the lesson plan
        assertEquals(0, lessonPlan.getLessonCards().size());
    }
}