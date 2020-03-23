package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip_session")
public class TripSessionController {

    @Autowired
    public TripSessionController() {

    }

    @GetMapping(value = "/{identificator}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void get() {

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create() {

    }

    @PatchMapping(value = "/{identificator}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Long identifictor) throws NotFoundException{

    }

    @DeleteMapping(value = "/{identificator}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long identifictor) throws NotFoundException {

    }
}
