package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.Enrollment;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentDao extends BaseDao<Enrollment> {
    protected EnrollmentDao() {
        super(Enrollment.class);
    }
}
