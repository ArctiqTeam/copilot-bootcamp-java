package com.arctiq.wright.controller;

import com.arctiq.wright.model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Stream;

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

    @Test
    void testGetPlaneByIdExists() {
        ResponseEntity<Plane> response = planesController.getPlaneById(1);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testGetPlaneByIdNotExists() {
        ResponseEntity<Plane> response = planesController.getPlaneById(999);

        assertNull(response.getBody());
        assertEquals(404, response.getStatusCode().value());
    }

@ParameterizedTest
@MethodSource("provideSearchTerms")
void testSearchPlaneByName(String searchTerm, int expectedCount) {
    // Setup test data
    List<Plane> planes = List.of(
            new Plane(1, "Wright Flyer", 1903, "Description", 12, "imageUrl1"),
            new Plane(2, "Wright Flyer II", 1904, "Description", 24, "imageUrl2"),
            new Plane(3, "Wright Flyer III", 1908, "Description", 40, "imageUrl3"),
            new Plane(4, "Wright Model A", 1910, "Description", 60, "imageUrl4"),
            new Plane(5, "Wright Model B", 1912, "Description", 80, "imageUrl5")
    );
    ResponseEntity<String> setupResponse = planesController.setupPlanesData(planes);
    assertEquals(HttpStatus.CREATED, setupResponse.getStatusCode());

    // Perform search
    ResponseEntity<List<Plane>> response = planesController.searchPlaneByName(searchTerm);

    // Assert the amount of planes returned
    if (response.getStatusCode() == HttpStatus.OK) {
        assertNotNull(response.getBody());
        assertEquals(expectedCount, response.getBody().size());
    } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
        assertEquals(0, response.getBody().size());
    } else {
        fail("Unexpected response status: " + response.getStatusCode());
    }
}

    private static Stream<Arguments> provideSearchTerms() {
        return Stream.of(
                Arguments.of("Wright Flyer III", 1),
                Arguments.of("Wright", 5),
                Arguments.of("wright flyer", 3),
                Arguments.of(" Wright flyer ", 3)
        );
    }
    // Add more test methods here for other methods in the PlanesController class
}