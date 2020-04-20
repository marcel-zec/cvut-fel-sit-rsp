package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.EnrollmentDao;
import cz.cvut.fel.rsp.travelandwork.dto.EnrollmentDto;
import cz.cvut.fel.rsp.travelandwork.exception.BadDateException;
import cz.cvut.fel.rsp.travelandwork.model.Enrollment;
import cz.cvut.fel.rsp.travelandwork.model.EnrollmentState;
import cz.cvut.fel.rsp.travelandwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentDao enrollmentDao;
    private final TranslateService translateService;

    @Autowired
    public EnrollmentService(EnrollmentDao enrollmentDao, TranslateService translateService) {
        this.enrollmentDao = enrollmentDao;
        this.translateService =  translateService;
    }

    @Transactional
    public List<Enrollment> findAll(){
        return enrollmentDao.findAll();
    }

    @Transactional
    public void create(Enrollment enrollment) throws BadDateException {
        if (enrollment.getEnrollDate().isBefore(LocalDateTime.now())) throw new BadDateException();
    }

    @Transactional
    public List<EnrollmentDto> findAllOfUser(User user){

        List<Enrollment> enrollments = user.getTravel_journal().getEnrollments();
        List<EnrollmentDto> enrollmentDtos = new ArrayList<EnrollmentDto>();

        for (Enrollment e : enrollments) {
             enrollmentDtos.add(translateService.translateEnrollment(e));
        }
        return enrollmentDtos;
    }

    @Transactional
    public List<EnrollmentDto> findAllOfUserFinished(User user){
        List<EnrollmentDto> userEnrollments = findAllOfUser(user);
        List<EnrollmentDto> finished = new ArrayList<EnrollmentDto>();

        for (EnrollmentDto enrollmentDto : userEnrollments) {
            if (enrollmentDto.getState()== EnrollmentState.FINISHED) finished.add(enrollmentDto);
        }
        return finished;
    }

    @Transactional
    public List<EnrollmentDto> findAllOfUserActive(User user){
        List<EnrollmentDto> userEnrollments = findAllOfUser(user);
        List<EnrollmentDto> active_canceled = new ArrayList<EnrollmentDto>();

        for (EnrollmentDto enrollmentDto : userEnrollments) {
            if (enrollmentDto.getState()!= EnrollmentState.FINISHED) active_canceled.add(enrollmentDto);
        }
        return active_canceled;
    }
}
