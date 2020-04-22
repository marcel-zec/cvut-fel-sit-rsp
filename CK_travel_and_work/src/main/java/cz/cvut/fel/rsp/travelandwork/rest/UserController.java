package cz.cvut.fel.rsp.travelandwork.rest;

import com.google.api.client.util.SecurityUtils;
import cz.cvut.fel.rsp.travelandwork.dto.RequestWrapper;
import cz.cvut.fel.rsp.travelandwork.dto.UserDto;
import cz.cvut.fel.rsp.travelandwork.exception.BadPassword;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.exception.UnauthorizedException;
import cz.cvut.fel.rsp.travelandwork.model.User;
import cz.cvut.fel.rsp.travelandwork.security.SecurityUtils;
import cz.cvut.fel.rsp.travelandwork.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials="true")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody RequestWrapper requestWrapper) throws BadPassword {
        userService.create(requestWrapper.getUser(), requestWrapper.getPassword_control());
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

    //TODO - práva len pre admina
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> showAll() {
        return userService.findAll();
    }


    @GetMapping(value= "current", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto showCurrentUser() throws UnauthorizedException {
        return userService.showCurrentUser();
    }

    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody User user) {
        return null;
    }


    @DeleteMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
        userService.delete(id);
        //LOG.debug("User {} successfully removed.");
        //final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
        //return new ResponseEntity<>(headers, HttpStatus.OK);
        return null;
    }










}

