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
        planes.add(plane);
        return ResponseEntity.status(201).body(plane);
    }
}