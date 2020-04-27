package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.environment.util.Generator;
import cz.cvut.fel.rsp.travelandwork.exception.BadDateException;
import cz.cvut.fel.rsp.travelandwork.exception.BadPassword;
import cz.cvut.fel.rsp.travelandwork.exception.NotAllowedException;
import cz.cvut.fel.rsp.travelandwork.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class EnrollmentServiceTest {

    private Enrollment enrollment;
    private User user;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserService userService;

    @Before
    public void prepare() throws BadPassword {
        enrollment = new Enrollment();
        enrollment.setActual_xp_reward(2);
        enrollment.setState(EnrollmentState.ACTIVE);
        enrollment.setEnrollDate(LocalDateTime.now().plusDays(7));
        enrollment.setDeposit_was_paid(false);
        enrollment.setTrip(Generator.generateTrip());
        enrollment.setTravelJournal(new TravelJournal());
        enrollment.setTripSession(new TripSession());
        user = Generator.generateUser();
        userService.createUser(user,user.getPassword());
    }


    @Test
    @Transactional
    @Rollback
    public void create_CreatesEnrollment() throws BadDateException {
//        enrollmentService.create(enrollment);
//        System.out.println(Arrays.toString(enrollmentService.findAll().toArray()));
//        assertTrue(enrollmentService.findAll().stream().anyMatch(enrollment1 -> enrollment1.getId().equals(enrollment.getId())));
    }

    // TODO - testy - dodelat EnrollmentService testy, az se naimplementuje metoda create

    @Test
    @Transactional
    @Rollback
    public void findAll(){
        enrollmentService.findAll();
    }

    @Test
    @Transactional
    @Rollback
    public void findAllOfUser() throws NotAllowedException {
        enrollmentService.findAllOfUser(user);
    }

    @Test
    @Transactional
    @Rollback
    public void findAllOfUserActive() throws NotAllowedException {
        enrollmentService.findAllOfUserActive(user);
    }

    @Test
    @Transactional
    @Rollback
    public void findAllOfUserFinished() throws NotAllowedException {

        enrollmentService.findAllOfUserFinished(user);
    }

}
