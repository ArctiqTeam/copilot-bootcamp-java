package com.arctiq.wright.controller;

import com.arctiq.wright.model.Flight;
import com.arctiq.wright.model.FlightStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightsControllerTest {

    private FlightsController flightsController;

    @BeforeEach
    public void setUp() {
        flightsController = new FlightsController();
    }

    @Test
    public void testCreateFlight() {
        Flight flight = new Flight(
                4,
                "WB004",
                "Kitty Hawk, North Carolina",
                "Manteo, NC",
                new Date(),
                new Date(),
                FlightStatus.Scheduled,
                100,
                false,
                "171203-DEP-ARR-WB004",
                "L4B-H2C-R3A-S1D-T2E");

        ResponseEntity<Flight> response = flightsController.createFlight(flight);

        assertEquals(201, response.getStatusCode().value());
        assertEquals(flight, response.getBody());
    }

    // Add more tests for other methods in the FlightsController class...
}