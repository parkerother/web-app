package com.openwebtechnology.demo.challenge.boat.controller;

import com.openwebtechnology.demo.challenge.boat.model.Boat;
import com.openwebtechnology.demo.challenge.boat.service.BoatService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class BoatController {

    @Autowired
    BoatService service;

    @GetMapping("/boats")
    public ResponseEntity<List<Boat>> getAllBoats() {
        try {
            List<Boat> Boats = service.getAllBoats();
            if (Boats.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(Boats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/boats/{id}")
    public ResponseEntity<Boat> getBoatById(@PathVariable("id") long id) {
        Boat boatData = service.getBoatById(id);
        if (null!=boatData){
            return new ResponseEntity<>(boatData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/boats")
    public ResponseEntity<Boat> createBoat(@RequestBody Boat boat) {
        try {
            val boatCreate = service.createBoat(boat);
            if (null != boatCreate) {
                return new ResponseEntity<>(boatCreate, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/boats/{id}")
    public ResponseEntity<Boat> updateBoat(@PathVariable("id") long id, @RequestBody Boat boat) {
      Boat boatUpdate= service.updateBoat(id,boat);
        if (null!=boatUpdate) {
             return new ResponseEntity<>(boatUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/boats/{id}")
    public ResponseEntity<HttpStatus> deleteBoat(@PathVariable("id") long id) {
        try {
            service.deleteBoatById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/boats")
    public ResponseEntity<HttpStatus> deleteAllBoats() {
        try {
            service.deleteAllBoats();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
