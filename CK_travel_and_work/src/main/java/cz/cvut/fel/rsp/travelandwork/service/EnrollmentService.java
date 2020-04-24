package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.EnrollmentDao;
import cz.cvut.fel.rsp.travelandwork.dao.UserDao;
import cz.cvut.fel.rsp.travelandwork.dto.EnrollmentDto;
import cz.cvut.fel.rsp.travelandwork.exception.BadDateException;
import cz.cvut.fel.rsp.travelandwork.exception.NotAllowedException;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.Enrollment;
import cz.cvut.fel.rsp.travelandwork.model.EnrollmentState;
import cz.cvut.fel.rsp.travelandwork.model.User;
import cz.cvut.fel.rsp.travelandwork.service.security.AccessService;
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
    private final AccessService accessService;
    private final UserDao userDao;

    @Autowired
    public EnrollmentService(EnrollmentDao enrollmentDao, TranslateService translateService, AccessService accessService, UserDao userDao) {
        this.enrollmentDao = enrollmentDao;
        this.translateService =  translateService;
        this.accessService = accessService;
        this.userDao = userDao;
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
    public List<EnrollmentDto> findAllOfUser(User current_user) throws NotAllowedException {

        User user = accessService.getUser(current_user);
        if (user == null) throw new NotAllowedException();

        List<Enrollment> enrollments = user.getTravel_journal().getEnrollments();
        List<EnrollmentDto> enrollmentDtos = new ArrayList<EnrollmentDto>();

        if (enrollments != null && enrollments.size()>0){
            for (Enrollment e : enrollments) {
                enrollmentDtos.add(translateService.translateEnrollment(e));
            }
        }
        return enrollmentDtos;
    }

    @Transactional
    public List<EnrollmentDto> findAllOfUserFinished(User current_user) throws NotAllowedException {
        List<EnrollmentDto> userEnrollments = findAllOfUser(current_user);
        List<EnrollmentDto> finished = new ArrayList<EnrollmentDto>();

        for (EnrollmentDto enrollmentDto : userEnrollments) {
            if (enrollmentDto.getState()== EnrollmentState.FINISHED) finished.add(enrollmentDto);
        }
        return finished;
    }

    @Transactional
    public List<EnrollmentDto> findAllOfUserActive(User current_user) throws NotAllowedException {
        List<EnrollmentDto> userEnrollments = findAllOfUser(current_user);
        List<EnrollmentDto> active_canceled = new ArrayList<EnrollmentDto>();

        for (EnrollmentDto enrollmentDto : userEnrollments) {
            if (enrollmentDto.getState()!= EnrollmentState.FINISHED) active_canceled.add(enrollmentDto);
        }
        return active_canceled;
    }

    @Transactional
    public List<EnrollmentDto> findAllOfUserFinished(Long id) throws NotFoundException, NotAllowedException {
        User user = userDao.find(id);
        if (user == null) throw new NotFoundException();
        return findAllOfUserFinished(user);
    }

    @Transactional
    public List<EnrollmentDto> findAllOfUserActive(Long id) throws NotFoundException, NotAllowedException {
        User user = userDao.find(id);
        if (user == null) throw new NotFoundException();
        return findAllOfUserActive(user);
    }
}
