package com.arctiq.wright.controller;

import com.arctiq.wright.model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanesControllerTest {

    private PlanesController planesController;

    @BeforeEach
    void setUp() {
        planesController = new PlanesController();
    }

    @Test
void testGetAllPlanes() {
    List<Plane> actualPlanes = planesController.getAllPlanes();

    assertFalse(actualPlanes.isEmpty());
}

    // Add more test methods here for other methods in the PlanesController class
}