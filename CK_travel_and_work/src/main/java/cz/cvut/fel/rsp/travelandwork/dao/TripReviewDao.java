package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.TripReview;
import org.springframework.stereotype.Repository;


@Repository
public class TripReviewDao extends BaseDao<TripReview> {
    public TripReviewDao() {
        super(TripReview.class);
    }
}