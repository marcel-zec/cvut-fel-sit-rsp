package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.UserReview;
import org.springframework.stereotype.Repository;

@Repository
public class UserReviewDao extends BaseDao<UserReview> {
    protected UserReviewDao() {
        super(UserReview.class);
    }
}
