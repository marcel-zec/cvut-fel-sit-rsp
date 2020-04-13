package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.model.Achievement;
import cz.cvut.fel.rsp.travelandwork.service.AchievementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/achievement")
public class AchievementController {

    private static final Logger LOG = LoggerFactory.getLogger(AchievementController.class);
    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Achievement get(@PathVariable Long id){
        return achievementService.find(id);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Achievement achievement){
        achievementService.update(achievement);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Achievement> getAll() {
        return achievementService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Achievement achievement){
        achievementService.create(achievement);
    }
}

