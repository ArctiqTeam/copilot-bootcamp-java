package com.arctiq.wright.controller;

import com.arctiq.wright.model.Flight;
import com.arctiq.wright.model.FlightStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightsController {

    private List<Flight> flights = new ArrayList<>();

    public FlightsController() {
        flights.add(new Flight(
                1,
                "WB001",
                "Kitty Hawk, North Carolina",
                "Manteo, NC",
                java.util.Date.from(java.time.LocalDateTime.of(1903, 12, 17, 10, 35, 0).atZone(java.time.ZoneId.systemDefault()).toInstant()),
                java.util.Date.from(java.time.LocalDateTime.of(1903, 12, 17, 10, 35+12, 0).atZone(java.time.ZoneId.systemDefault()).toInstant()),
                FlightStatus.Scheduled,
                100,
                false,
                "171203-DEP-ARR-WB001",
                "L4B-H2C-R3A-S1D-T2E"));
        flights.add(new Flight(
                2,
                "WB002",
                "Kitty Hawk, North Carolina",
                "Manteo, NC",
                java.util.Date.from(java.time.LocalDateTime.of(1903, 12, 17, 10, 35, 0).atZone(java.time.ZoneId.systemDefault()).toInstant()),
                java.util.Date.from(java.time.LocalDateTime.of(1903, 12, 17, 10, 35+12, 0).atZone(java.time.ZoneId.systemDefault()).toInstant()),
                FlightStatus.Scheduled,
                100,
                false,
                "171203-DEP-ARR-WB002",
                "L4B-H2C-R3A-S1D-T2E"));
        flights.add(new Flight(
                3,
                "WB003",
                "Kitty Hawk, North Carolina",
                "Manteo, NC",
                java.util.Date.from(java.time.LocalDateTime.of(1903, 12, 17, 10, 35, 0).atZone(java.time.ZoneId.systemDefault()).toInstant()),
                java.util.Date.from(java.time.LocalDateTime.of(1903, 12, 17, 10, 35+12, 0).atZone(java.time.ZoneId.systemDefault()).toInstant()),
                FlightStatus.Scheduled,
                100,
                false,
                "171203-DEP-ARR-WB003",
                "L4B-H2C-R3A-S1D-T2E"));
    }

    @GetMapping("/")
    public List<Flight> getAllFlights() {
        return flights;
    }

    @PostMapping("/")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        flights.add(flight);
        return ResponseEntity.status(201).body(flight);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable int id) {
        Optional<Flight> flight = flights.stream().filter(f -> f.getId() == id).findFirst();
        if (flight.isPresent()) {
            return ResponseEntity.ok(flight.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable int id, @RequestBody FlightStatus status) {
        Optional<Flight> flight = flights.stream().filter(f -> f.getId() == id).findFirst();
        if (flight.isPresent()) {
            // Implement your logic for updating flight status
            return ResponseEntity.status(200).body("Flight status updated.");
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/{id}/takeFlight/{flightLength}")
    public ResponseEntity<String> takeFlight(@PathVariable int id, @PathVariable int flightLength) {
        // Implement your logic for taking flight
        return ResponseEntity.status(200).body("Flight took off.");
    }

    @PostMapping("/{id}/lightningStrike")
    public ResponseEntity<String> lightningStrike(@PathVariable int id) {
        // Implement your logic for lightning strike
        return ResponseEntity.status(200).body("Lightning strike handled.");
    }

    @PostMapping("/{id}/calculateAerodynamics")
    public ResponseEntity<String> calculateAerodynamics(@PathVariable int id) {
        // Implement your logic for calculating aerodynamics
        return ResponseEntity.status(200).body("Aerodynamics calculated.");
    }

}