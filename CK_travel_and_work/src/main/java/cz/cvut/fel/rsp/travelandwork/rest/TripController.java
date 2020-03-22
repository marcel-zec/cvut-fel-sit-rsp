package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/{identificator}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable String identifictor) throws NotFoundException{

    }

    @DeleteMapping(value = "/{identificator}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String identifictor) throws NotFoundException {

    }
}
