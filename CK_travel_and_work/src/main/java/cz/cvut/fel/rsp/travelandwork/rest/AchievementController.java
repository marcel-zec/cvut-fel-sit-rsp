package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.dao.AchievementDao;
import cz.cvut.fel.rsp.travelandwork.model.Achievement;
import cz.cvut.fel.rsp.travelandwork.model.User;
import cz.cvut.fel.rsp.travelandwork.service.AchievementService;
import cz.cvut.fel.rsp.travelandwork.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achievement")
public class AchievementController {

    private static final Logger LOG = LoggerFactory.getLogger(AchievementController.class);
    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Achievement> getAll() {
        return achievementService.findAll();
    }
}

