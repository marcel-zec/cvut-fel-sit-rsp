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



    //TODO - od Marcela - presunute do TripControlerra a odstranený requestbody lebo to vieme zistit
//    @GetMapping(value = "/trips", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void showAllTrips(@RequestBody User user) {
//
//    }

    //TODO - od Marcela - to je otázka či to budeme mať jak osobitné dotazy alebo to budeme filtrovať na frontende, ale ak to bude jak dotaz tak som to dal do TripControllera
//    @GetMapping(value = "/tripsCantAfford", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void showAllTripsCantAfford(@RequestBody User user) {
//
//    }
//
//    @GetMapping(value = "/tripsCanAfford", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void showAllTripsCanAfford(@RequestBody User user) {
//
//    }

    //TODO - od Marcela - tak ako sme pisali na Slacku, že to asi bude cez dotaz na trip jurnal
//    @GetMapping(value = "/trips", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void showTripHistory(@RequestBody User user) {
//
//    }

    //TODO - od Marcela - len som dal preč requestbody lebo vieme zistit kto je prihlaseny
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void showCurrentUser() {

    }

    //TODO - od Marcela - nato môžeme používať SettingController z našej semestrálky z EAR
//    //Upravit si svuj profil - heslo
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> changePassword(@RequestBody User user) {
//        return null;
//    }

    //TODO - od Marcela - asi je zbytočné mať metoódu len na zmenu emailu, radšej na celý profil, tak som to len prepísal z changeEmail na update
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody User user) {
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

