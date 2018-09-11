package com.minaiev.animals.processor.handler.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CarsLineHandlerTest {
    private CarsLineHandler carsLineHandler;

    @BeforeEach
    void setup() {
        carsLineHandler = new CarsLineHandler();
    }

    @Test
    void shouldNotHandle_IfLineEqualsCategory() {
        boolean canHandle = carsLineHandler.canHandle(carsLineHandler.getCategory());

        assertFalse(canHandle, "Line handler should not handle line equals to category.");
    }

    @Test
    void shouldHandle_IfLineNotEqualsCategory() {
        boolean canHandle = carsLineHandler.canHandle("bmw");

        assertTrue(canHandle, "Line handler should handle line if it's not equal to category.");
    }

    @Test
    void shouldNotAddDuplicates_onHandle() {
        String line1 = "Opel";
        String line2 = "opel";
        String line3 = "bmw";
        carsLineHandler.handle(line1);
        carsLineHandler.handle(line2);
        carsLineHandler.handle(line3);

        Collection<String> results = carsLineHandler.getResults();
        assertFalse(results.isEmpty(), "Results should not be empty");
        assertEquals(2, results.size(), "Size of entries should be 2");
    }

    @Test
    void shouldOutputUniqueAnimalsWithCount() {
        String line1 = "Opel";
        String line2 = "VW";
        String line3 = "vw";
        carsLineHandler.handle(line1);
        carsLineHandler.handle(line2);
        carsLineHandler.handle(line3);

        String result = carsLineHandler.getFormattedOutput();
        assertEquals("vw (7336a2c49b0045fa1340bf899f785e70)\nopel (f65b7d39472c52142ea2f4ea5e115d59)", result, "Formatted cars with hash should be displayed reverse ordered");
    }


}