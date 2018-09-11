package com.minaiev.animals.processor.handler.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class AnimalsLineHandlerTest {
    private AnimalsLineHandler animalsLineHandler;

    @BeforeEach
    void setup() {
        animalsLineHandler = new AnimalsLineHandler();
    }

    @Test
    void shouldNotHandle_IfLineEqualsCategory() {
        boolean canHandle = animalsLineHandler.canHandle(animalsLineHandler.getCategory());

        assertFalse(canHandle, "Line handler should not handle line equals to category.");
    }

    @Test
    void shouldHandle_IfLineNotEqualsCategory() {
        boolean canHandle = animalsLineHandler.canHandle("animal");

        assertTrue(canHandle, "Line handler should handle line if it's not equal to category.");
    }

    @Test
    void shouldNotAddDuplicates_onHandle() {
        String line1 = "cow";
        String line2 = "dog";
        animalsLineHandler.handle(line1);
        animalsLineHandler.handle(line1);
        animalsLineHandler.handle(line2);

        Collection<String> results = animalsLineHandler.getResults();
        assertFalse(results.isEmpty(), "Results should not be empty");
        assertEquals(2, results.size(), "Size of entries should be 2");
    }

    @Test
    void shouldOutputUniqueAnimalsWithCount() {
        String line1 = "sheep";
        String line2 = "cow";
        String line3 = "dog";
        animalsLineHandler.handle(line1);
        animalsLineHandler.handle(line2);
        animalsLineHandler.handle(line3);

        String result = animalsLineHandler.getFormattedOutput();
        assertEquals("cow\ndog\nsheep", result, "Formatted animals should be displayed ordered alphabetically");
    }


}