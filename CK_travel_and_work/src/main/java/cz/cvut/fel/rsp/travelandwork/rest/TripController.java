package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/trip")
public class TripController {

    private TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Trip> getAll() {
        return tripService.findAll();
    }

    @GetMapping(value = "/{identificator}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Trip get(@PathVariable Long identificator) {
        return tripService.find(identificator);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Trip trip) {

    }

    @PatchMapping(value = "/{identificator}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Long identificator) throws NotFoundException{

    }

    @DeleteMapping(value = "/{identificator}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long identificator) throws NotFoundException {

    }

    @PostMapping(value = "/{identificator}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signUpToTrip(@PathVariable Long identificator) {
        //ResponseEntity<Void>
        //return new ResponseEntity<>(headers, HttpStatus.SUCCESS);
        tripService.signUpToTrip(identificator);
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
