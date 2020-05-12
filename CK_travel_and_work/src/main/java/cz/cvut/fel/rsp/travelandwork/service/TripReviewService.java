package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TripDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripReviewDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripSessionDao;
import cz.cvut.fel.rsp.travelandwork.dao.UserDao;
import cz.cvut.fel.rsp.travelandwork.dto.TripSessionDto;
import cz.cvut.fel.rsp.travelandwork.exception.AlreadyExistsException;
import cz.cvut.fel.rsp.travelandwork.exception.NotAllowedException;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.exception.UnauthorizedException;
import cz.cvut.fel.rsp.travelandwork.model.Enrollment;
import cz.cvut.fel.rsp.travelandwork.model.TripReview;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import cz.cvut.fel.rsp.travelandwork.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TripReviewService {

    private final TripReviewDao tripReviewDao;
    private final UserDao userDao;
    private final TripDao tripDao;
    private final TripSessionDao tripSessionDao;

    public TripReviewService(TripReviewDao tripReviewDao, UserDao userDao, TripDao tripDao, TripSessionDao tripSessionDao) {
        this.tripReviewDao = tripReviewDao;
        this.userDao = userDao;
        this.tripDao = tripDao;
        this.tripSessionDao = tripSessionDao;
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
    public void create(TripReview tripReview, Long sessionId) throws AlreadyExistsException, UnauthorizedException, NotFoundException {
        Objects.requireNonNull(tripReview);
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new UnauthorizedException();

        TripSession tripSession = tripSessionDao.find(sessionId);
        if (tripSession == null) throw new NotFoundException();
        for(TripReview review : tripSession.getTripReviews()){
            if (review.getAuthor().getId().equals(SecurityUtils.getCurrentUser().getId())) throw new AlreadyExistsException();
        }
        Optional<Enrollment> found = tripSession.getEnrollments().stream().filter(enrollment -> enrollment.getTravelJournal().getUser().getId().equals(SecurityUtils.getCurrentUser().getId())).findFirst();
        if(found.isPresent()){
            tripReview.setAuthor(userDao.find(SecurityUtils.getCurrentUser().getId()));
            tripReview.setTripSession(tripSession);
            tripSession.addTripReview(tripReview);
            tripReviewDao.persist(tripReview);
            tripSessionDao.update(tripSession);
            System.out.println("Review should be added");
        } else {
            throw new NotFoundException();
        }
    }

    @Transactional
    public void update(TripReview tripReview) {
        Objects.requireNonNull(tripReview);
        tripReviewDao.update(tripReview);
    }
}
