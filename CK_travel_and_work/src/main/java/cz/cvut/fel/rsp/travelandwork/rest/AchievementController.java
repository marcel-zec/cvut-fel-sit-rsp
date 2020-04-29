package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.model.Achievement;
import cz.cvut.fel.rsp.travelandwork.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/achievement")
public class AchievementController {

    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {

        this.achievementService = achievementService;
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Achievement> getAll(){
        return achievementService.findAll();
    }
}
