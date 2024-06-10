package com.arctiq.wright.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.arctiq.wright.model.Plane;


@RestController
@RequestMapping("/planes")
public class PlanesController {

    private List<Plane> planes = new ArrayList<>();

    public PlanesController() {
        planes.add(new Plane(
            1,
            "Wright Flyer",
            1903,
            "The first successful heavier-than-air powered aircraft.",
            12,
            "imageUrl1"
        ));
        planes.add(new Plane(
            2,
            "Wright Flyer II",
            1904,
            "A refinement of the original Flyer with better performance.",
            24,
            "imageUrl2"
        ));
        planes.add(new Plane(
            3,
            "Wright Model A",
            1908,
            "The first commercially successful airplane.",
            40,
            "imageUrl3"
        ));
        planes.add(new Plane(
            4,
            "Wright Model B",
            1910,
            "An improved version of the Model A.",
            60,
            "imageUrl4"
        ));
        planes.add(new Plane(
            5,
            "Wright Model C",
            1912,
            "A further refinement of the Model B.",
            80,
            "imageUrl5"
        ));
    }

    @GetMapping("/")
    public List<Plane> getAllPlanes() {
        return planes;
    }

/**
 * Handles the GET request to fetch a plane by its id.
 *
 * @param id The id of the plane to be fetched.
 * @return ResponseEntity containing the Plane object if found, or a 404 status code if not found.
 */
@GetMapping("/{id}")
public ResponseEntity<Plane> getPlaneById(@PathVariable int id) {
    Optional<Plane> plane = planes.stream().filter(p -> p.getId() == id).findFirst();
    if (plane.isPresent()) {
        return ResponseEntity.ok(plane.get());
    } else {
        return ResponseEntity.status(404).build();
    }
}

    @PostMapping("/")
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane) {
        // return bad request if plane already exists by name
        if (planes.stream().anyMatch(p -> p.getName().equals(plane.getName()))) {
            return ResponseEntity.status(400).build();
        }

        // return bad request if plane is null
        if (plane == null) {
            return ResponseEntity.status(400).build();
        }
        planes.add(plane);
        return ResponseEntity.status(201).body(plane);
    }

    // search plane by name
    @GetMapping("/search")
    public ResponseEntity<List<Plane>> searchPlaneByName(@RequestParam String name) {
        List<Plane> matchingPlanes = planes.stream()
            .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
        if (!matchingPlanes.isEmpty()) {
            return ResponseEntity.ok(matchingPlanes);
        } else {
            return ResponseEntity.status(404).body(new ArrayList<>());
        }
    }

    // Implement POST /planes/setup to setup the planes data accepting a list of planes as input returning a code of 201 if successful
    @PostMapping("/setup")
    public ResponseEntity<String> setupPlanesData(@RequestBody List<Plane> planes) {
        this.planes = planes;
        return ResponseEntity.status(201).build();
    }
}