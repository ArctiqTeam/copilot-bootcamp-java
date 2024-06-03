package com.arctiq.wright.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.arctiq.wright.model.Airfield;

@RestController
@RequestMapping("/airfields")
public class AirfieldController {

    private List<Airfield> airfields = new ArrayList<>();

    public AirfieldController() {
        airfields.add(new Airfield(1, "Huffman Prairie", "Dayton, Ohio", "1904-1910", "The Wright Brothers' primary testing and development site."));
        airfields.add(new Airfield(2, "Kill Devil Hills", "North Carolina", "1900-1903", "Site of the Wright Brothers' first controlled, sustained flight."));
        airfields.add(new Airfield(3, "Simms Station", "Dayton, Ohio", "1910-1916", "Location of the Wright Brothers' first civilian training school."));
    }

    @GetMapping("/")
    public List<Airfield> getAllAirfields() {
        return airfields;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airfield> getAirfieldById(@PathVariable int id) {
        Optional<Airfield> airfield = airfields.stream().filter(a -> a.getId() == id).findFirst();
        if (airfield.isPresent()) {
            return ResponseEntity.ok(airfield.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Airfield> createAirfield(@RequestBody Airfield airfield) {
        Optional<Airfield> existingAirfield = airfields.stream().filter(a -> a.getId() == airfield.getId()).findFirst();
        if (existingAirfield.isPresent()) {
            return ResponseEntity.status(409).body(airfield);
        } else {
            airfields.add(airfield);
            return ResponseEntity.status(201).body(airfield);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airfield> updateAirfield(@PathVariable int id, @RequestBody Airfield airfield) {
        Optional<Airfield> existingAirfield = airfields.stream().filter(a -> a.getId() == id).findFirst();
        if (existingAirfield.isPresent()) {
            Airfield updatedAirfield = existingAirfield.get();
            updatedAirfield.setName(airfield.getName());
            updatedAirfield.setLocation(airfield.getLocation());
            updatedAirfield.setDatesOfUse(airfield.getDatesOfUse());
            updatedAirfield.setSignificance(airfield.getSignificance());
            return ResponseEntity.ok(updatedAirfield);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirfield(@PathVariable int id) {
        Optional<Airfield> airfield = airfields.stream().filter(a -> a.getId() == id).findFirst();
        if (airfield.isPresent()) {
            airfields.remove(airfield.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
