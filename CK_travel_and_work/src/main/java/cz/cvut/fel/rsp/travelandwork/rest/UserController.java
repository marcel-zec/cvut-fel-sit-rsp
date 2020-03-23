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


    //TODO - najst sposob ako to budeme zobrazovat, ci cez TripJurnal alebo ce list Enrollments
//    @GetMapping(value = "/trips", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void showTripHistory(@RequestBody User user) {
//
//    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void showCurrentUser() {

    }

    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody User user) {
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

