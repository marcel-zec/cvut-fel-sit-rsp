package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TripReviewDao;
import cz.cvut.fel.rsp.travelandwork.model.TripReview;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TripReviewService {

    private final TripReviewDao tripReviewDao;

    public TripReviewService(TripReviewDao tripReviewDao) {
        this.tripReviewDao = tripReviewDao;
    }

    @Transactional
    public List<TripReview> findAll() {
        return tripReviewDao.findAll();
    }

    @Transactional
    public TripReview find(Long id) {
        return tripReviewDao.find(id);
    }

    @Transactional
    public void persist(TripReview tripReview) {
        tripReviewDao.persist(tripReview);
    }

    @Transactional
    public void update(TripReview tripReview) {
        Objects.requireNonNull(tripReview);
        tripReviewDao.update(tripReview);
    }
}
