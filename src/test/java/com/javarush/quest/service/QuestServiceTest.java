package com.javarush.quest.service;

import com.javarush.quest.model.QuestStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class QuestServiceTest {

    private QuestService questService;

    @BeforeEach
    void setUp() {
        questService = new QuestService();
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "bridge", "captain", "win", "lose1", "lose2", "lose3"})
    @DisplayName("Test each step is not null")
    void testEachStepNotNull(String stepId) {
        assertNotNull(questService.getStep(stepId), "Шаг " + stepId + " не должен быть null");
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "bridge", "captain", "win", "lose1", "lose2", "lose3"})
    @DisplayName("Test get ID each step")
    void testGetIdEachStep(String stepId) {
        QuestStep step = questService.getStep(stepId);
        assertEquals(stepId, step.getId(), "ID шага должен быть равен " + stepId);
    }

    @ParameterizedTest
    @CsvSource({
            "start, Принять вызов НЛО?",
            "bridge, Поднимаешься на мостик",
            "captain, Ты кто?",
            "win, Победа",
            "lose1, отклонил вызов",
            "lose2, не пошел на переговоры",
            "lose3, ложь разоблачили"
    })
    @DisplayName("Test get text each step")
    void testGetTextEachStep(String stepId, String text) {
        QuestStep step = questService.getStep(stepId);
        assertTrue(step.getText().contains(text));
    }

    @ParameterizedTest
    @CsvSource({
            "start, Принять вызов",
            "bridge, Подняться на мостик",
            "captain, Рассказать правду о себе",
            "win, 'null'",
            "lose1, 'null'",
            "lose2, 'null'",
            "lose3, 'null'"
    })
    @DisplayName("Test get option1 each step")
    void testGetOption1EachStep(String stepId, String option1) {
        QuestStep step = questService.getStep(stepId);
        if (option1.equals("null"))
            option1 = null;
        assertEquals(option1, step.getOption1());
    }

    @ParameterizedTest
    @CsvSource({
            "start, Отклонить вызов",
            "bridge, Отказаться подниматься на мостик",
            "captain, Солгать о себе",
            "win, 'null'",
            "lose1, 'null'",
            "lose2, 'null'",
            "lose3, 'null'"
    })
    @DisplayName("Test get option2 each step")
    void testGetOption2EachStep(String stepId, String option2) {
        QuestStep step = questService.getStep(stepId);
        if (option2.equals("null"))
            option2 = null;
        assertEquals(option2, step.getOption2());
    }

    @ParameterizedTest
    @CsvSource({
            "start, bridge",
            "bridge, captain",
            "captain, win",
            "win, 'null'",
            "lose1, 'null'",
            "lose2, 'null'",
            "lose3, 'null'"
    })
    @DisplayName("Test get nextStepId1 each step")
    void testGetNextStepId1EachStep(String stepId, String nextStepId1) {
        QuestStep step = questService.getStep(stepId);
        if (nextStepId1.equals("null"))
            nextStepId1 = null;
        assertEquals(nextStepId1, step.getNextStepId1());
    }


    @ParameterizedTest
    @CsvSource({
            "start, lose1",
            "bridge, lose2",
            "captain, lose3",
            "win, 'null'",
            "lose1, 'null'",
            "lose2, 'null'",
            "lose3, 'null'"
    })
    @DisplayName("Test get nextStepId2 each step")
    void testGetNextStepId2EachStep(String stepId, String nextStepId2) {
        QuestStep step = questService.getStep(stepId);
        if (nextStepId2.equals("null"))
            nextStepId2 = null;
        assertEquals(nextStepId2, step.getNextStepId2());
    }

    @Test
    @DisplayName("Test get non-existent step")
    void testGetNonExistentStep() {
        QuestStep step = questService.getStep("non-existent");
        assertNull(step);
    }

    @ParameterizedTest
    @CsvSource({
            "start, false",
            "bridge, false",
            "captain, false",
            "win, true",
            "lose1, true",
            "lose2, true",
            "lose3, true"
    })
    @DisplayName("Test QuestService.isFinalStep method")
    void testIsFinalStepMethod(String stepId, boolean value) {
        QuestStep step = questService.getStep(stepId);
        assertEquals(value, questService.isFinalStep(stepId));
    }
}