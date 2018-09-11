package com.minaiev.animals.processor.handler.impl;


import com.google.common.collect.HashMultiset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class NumbersLineHandlerTest {
    private NumbersLineHandler numbersLineHandler;

    @BeforeEach
    void setup() {
        numbersLineHandler = new NumbersLineHandler();
    }

    @Test
    void shouldNotHandle_IfLineEqualsCategory() {
        boolean canHandle = numbersLineHandler.canHandle(numbersLineHandler.getCategory());

        assertFalse(canHandle, "Line handler should not handle line equals to category.");
    }

    @Test
    void shouldHandle_IfLineNotEqualsCategory() {
        boolean canHandle = numbersLineHandler.canHandle("Not category");

        assertTrue(canHandle, "Line handler should handle line if it's not equal to category.");
    }

    @Test
    void shouldNotAddDuplicates_onHandle() {
        String line1 = "number";
        String line2 = "another number";
        numbersLineHandler.handle(line1);
        numbersLineHandler.handle(line1);
        numbersLineHandler.handle(line2);

        HashMultiset<String> results = (HashMultiset<String>) numbersLineHandler.getResults();
        assertFalse(results.isEmpty(), "Results should not be empty");
        assertEquals(2, results.elementSet().size(), "Size of entries should be 2");
    }

    @Test
    void shouldOutputUniqueNumbersWithCount() {
        String line1 = "num1";
        String line2 = "num2";
        numbersLineHandler.handle(line1);
        numbersLineHandler.handle(line1);
        numbersLineHandler.handle(line2);

        String result = numbersLineHandler.getFormattedOutput();
        assertEquals("num1: 2\nnum2: 1", result, "Formatted numbers with count should be displayed");
    }


}