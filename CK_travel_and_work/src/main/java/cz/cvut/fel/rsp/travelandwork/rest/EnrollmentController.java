package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.dto.EnrollmentDto;
import cz.cvut.fel.rsp.travelandwork.exception.NotAllowedException;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.security.SecurityUtils;
import cz.cvut.fel.rsp.travelandwork.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnrollmentDto> getAllOfUserFinished() throws NotAllowedException {
        return enrollmentService.findAllOfUserFinished(SecurityUtils.getCurrentUser());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnrollmentDto> getAllOfUserActiveAndCancel() throws NotAllowedException {
        return enrollmentService.findAllOfUserActive(SecurityUtils.getCurrentUser());
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN')")
    @GetMapping(value = "/complete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnrollmentDto> getAllOfUserFinishedAdmin(@PathVariable Long id) throws NotAllowedException, NotFoundException {
        return enrollmentService.findAllOfUserFinished(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN')")
    @GetMapping(value = "/active/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnrollmentDto> getAllOfUserActiveAndCancelAdmin(@PathVariable Long id) throws NotAllowedException, NotFoundException {
        return enrollmentService.findAllOfUserActive(id);
    }


}
