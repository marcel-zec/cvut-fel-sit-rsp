package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import cz.cvut.fel.rsp.travelandwork.service.TripSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip_session")
public class TripSessionController {

    private TripSessionService tripSessionService;

    @Autowired
    public TripSessionController(TripSessionService tripSessionService) {
        this.tripSessionService = tripSessionService;
    }


    @GetMapping(value = "/{trip_short_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TripSession> findAllInTrip(@PathVariable String trip_short_name) {
        return tripSessionService.findAllInTrip(trip_short_name);
    }

    @PostMapping(value = "/{trip_short_name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@PathVariable String trip_short_name, @RequestBody TripSession tripSession) throws Exception {
        tripSessionService.create(trip_short_name, tripSession);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Long id) throws NotFoundException{

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NotFoundException {

    }
}
