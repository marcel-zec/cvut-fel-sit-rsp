package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    public TripController() {

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void getAll() {

    }

    @GetMapping(value = "/{identificator}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void get(@PathVariable String identifictor) {

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create() {

    }

    @PostMapping(value = "/{identificator}")
    public void update(@PathVariable String identifictor) throws NotFoundException{

    }

    @DeleteMapping(value = "/{identificator}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String identifictor) throws NotFoundException {

    }

    @PostMapping(value = "/{identificator}")
    public ResponseEntity<Void> signUpToTrip(@RequestBody Trip trip) {
        //return new ResponseEntity<>(headers, HttpStatus.SUCCESS);
        return null;
    }

    @GetMapping(value = "/trips", produces = MediaType.APPLICATION_JSON_VALUE)
    public void showAllActiveTripsOfUser() {

    }

    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public void showTripHistoryOfUser() {

    }

    @GetMapping(value = "/cannotAfford", produces = MediaType.APPLICATION_JSON_VALUE)
    public void showAllTripsCantUserAfford() {

    }

    @GetMapping(value = "/canAfford", produces = MediaType.APPLICATION_JSON_VALUE)
    public void showAllTripsCanUserAfford() {

    }
}
