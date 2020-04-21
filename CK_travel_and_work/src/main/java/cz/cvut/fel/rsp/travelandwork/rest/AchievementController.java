package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.model.AchievementCertificate;
import cz.cvut.fel.rsp.travelandwork.service.AchievementCertificateService;
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
@RequestMapping("/achievement/certificate")
public class AchievementController {

    private static final Logger LOG = LoggerFactory.getLogger(AchievementController.class);
    private final AchievementCertificateService achievementCertificateService;

    @Autowired
    public AchievementController(AchievementCertificateService achievementCertificateService) {
        this.achievementCertificateService = achievementCertificateService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AchievementCertificate get(@PathVariable Long id){
        return achievementCertificateService.find(id);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody AchievementCertificate achievement){
        achievementCertificateService.update(achievement);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AchievementCertificate> getAll() {
        return achievementCertificateService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AchievementCertificate achievement){
        achievementCertificateService.create(achievement);
    }
}

