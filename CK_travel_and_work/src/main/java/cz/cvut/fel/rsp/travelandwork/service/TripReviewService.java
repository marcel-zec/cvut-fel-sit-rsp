package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TripDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripReviewDao;
import cz.cvut.fel.rsp.travelandwork.dao.UserDao;
import cz.cvut.fel.rsp.travelandwork.exception.NotAllowedException;
import cz.cvut.fel.rsp.travelandwork.exception.UnauthorizedException;
import cz.cvut.fel.rsp.travelandwork.model.TripReview;
import cz.cvut.fel.rsp.travelandwork.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TripReviewService {

    private final TripReviewDao tripReviewDao;
    private final UserDao userDao;
    private final TripDao tripDao;

    public TripReviewService(TripReviewDao tripReviewDao, UserDao userDao, TripDao tripDao) {
        this.tripReviewDao = tripReviewDao;
        this.userDao = userDao;
        this.tripDao = tripDao;
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
    public void create(TripReview tripReview, String short_name_trip) throws UnauthorizedException, NotAllowedException {
//        Objects.requireNonNull(tripReview);
//        if (SecurityUtils.isAuthenticatedAnonymously()) throw new UnauthorizedException();
//        if (short_name_trip == null) throw new NotAllowedException();
//
//        tripReview.setAuthor(userDao.find(SecurityUtils.getCurrentUser().getId()));
//        tripReview.setTripSession(tripDao.find(short_name_trip));
//        tripReviewDao.persist(tripReview);
    }

    @Transactional
    public void update(TripReview tripReview) {
        Objects.requireNonNull(tripReview);
        tripReviewDao.update(tripReview);
    }
}
