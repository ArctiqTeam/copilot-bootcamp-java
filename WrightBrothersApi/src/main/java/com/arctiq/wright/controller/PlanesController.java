package com.arctiq.wright.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.arctiq.wright.model.Plane;


@RestController
@RequestMapping("/planes")
public class PlanesController {

    private List<Plane> planes = new ArrayList<>();

    public PlanesController() {
        planes.add(new Plane(1, "Wright Flyer", 1903, "The first successful heavier-than-air powered aircraft.", 12));
        planes.add(new Plane(2, "Wright Flyer II", 1904, "A refinement of the original Flyer with better performance.", 24));
        planes.add(new Plane(3, "Wright Model A", 1908, "The first commercially successful airplane.", 40));
    }

    @GetMapping("/")
    public List<Plane> getAllPlanes() {
        return planes;
    }

    // Search Planes by name
    @GetMapping("/search")
    public ResponseEntity<List<Plane>> searchPlanes(@RequestParam String name) {
        List<Plane> matchingPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingPlanes.add(plane);
            }
        }
        // return 404 if no matching planes found
        if (matchingPlanes.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(matchingPlanes);
    }

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
        // Check if the plane already exists
        Optional<Plane> existingPlane = planes.stream().filter(p -> p.getId() == plane.getId()).findFirst();
        if (existingPlane.isPresent()) {
            return ResponseEntity.status(409).body(plane);
        } else {
            planes.add(plane);
            return ResponseEntity.status(201).body(plane);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plane> updatePlane(@PathVariable int id, @RequestBody Plane plane) {
        Optional<Plane> existingPlane = planes.stream().filter(p -> p.getId() == id).findFirst();
        if (existingPlane.isPresent()) {
            Plane updatedPlane = existingPlane.get();
            updatedPlane.setName(plane.getName());
            updatedPlane.setYear(plane.getYear());
            updatedPlane.setDescription(plane.getDescription());
            return ResponseEntity.ok(updatedPlane);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable int id) {
        Optional<Plane> plane = planes.stream().filter(p -> p.getId() == id).findFirst();
        if (plane.isPresent()) {
            planes.remove(plane.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}