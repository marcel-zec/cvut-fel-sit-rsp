package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.dto.EnrollmentDto;
import cz.cvut.fel.rsp.travelandwork.security.SecurityUtils;
import cz.cvut.fel.rsp.travelandwork.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/enrollment")
public class EnrollmentController {

    private static final Logger LOG = LoggerFactory.getLogger(EnrollmentController.class);
    private final EnrollmentService enrollmentService;


    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping(value = "/complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnrollmentDto> getAllOfUserFinished(){
        return enrollmentService.findAllOfUserFinished(SecurityUtils.getCurrentUser());
    }

    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnrollmentDto> getAllOfUserActiveAndCancel(){
        return enrollmentService.findAllOfUserActive(SecurityUtils.getCurrentUser());
    }
}
