package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.EnrollmentDao;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    private final EnrollmentDao enrollmentDao;

    public EnrollmentService(EnrollmentDao enrollmentDao) {
        this.enrollmentDao = enrollmentDao;
    }


}
