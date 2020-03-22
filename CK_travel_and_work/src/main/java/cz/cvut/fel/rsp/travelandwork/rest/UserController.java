package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.model.User;
import cz.cvut.fel.rsp.travelandwork.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody User user) {
        //userService.persist(user);
        //LOG.debug("User {} successfully registered.", user);
        //final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
        //return new ResponseEntity<>(headers, HttpStatus.CREATED);
        return null;
    }



    @GetMapping(value = "/trips", produces = MediaType.APPLICATION_JSON_VALUE)
    public void showAllTrips(@RequestBody User user) {

    }

    @GetMapping(value = "/tripsCantAfford", produces = MediaType.APPLICATION_JSON_VALUE)
    public void showAllTripsCantAfford(@RequestBody User user) {

    }

    @GetMapping(value = "/tripsCanAfford", produces = MediaType.APPLICATION_JSON_VALUE)
    public void showAllTripsCanAfford(@RequestBody User user) {

    }

    @PostMapping(value = "/trips/trip/signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUpToTrip(@RequestBody Trip trip) {
        //return new ResponseEntity<>(headers, HttpStatus.SUCCESS);
        return null;
    }

    @PostMapping(value = "/trips/trip/checkOut", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> checkOuFromTrip(@RequestBody Trip trip) {
        //return new ResponseEntity<>(headers, HttpStatus.SUCCESS);
        return null;
    }

    @GetMapping(value = "/trips", produces = MediaType.APPLICATION_JSON_VALUE)
    public void showTripHistory(@RequestBody Trip trip) {

    }

    @GetMapping(value = "/trips", produces = MediaType.APPLICATION_JSON_VALUE)
    public void showUserDetails(@RequestBody User user) {

    }

    //Upravit si svuj profil - heslo
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changePassword(@RequestBody User user) {
        return null;
    }

    //Upravit si svuj profil - email
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changeEmail(@RequestBody User user) {
        return null;
    }


    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> remove(@RequestBody User user) {
        //userService.remove(user);
        //LOG.debug("User {} successfully removed.");
        //final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
        //return new ResponseEntity<>(headers, HttpStatus.OK);
        return null;
    }










}

