package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TripDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripReviewDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripSessionDao;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.model.TripReview;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TripService {

    private TripDao tripDao;
    private TripSessionDao tripSessionDao;
    private TripReviewDao tripReviewDao;

    @Autowired
    public TripService(TripDao tripDao, TripSessionDao tripSessionDao, TripReviewDao tripReviewDao) {
        this.tripDao = tripDao;
        this.tripSessionDao = tripSessionDao;
        this.tripReviewDao = tripReviewDao;
    }

    @Transactional
    public List<Trip> findAll() {
        return tripDao.findAll();
    }

    @Transactional
    public Trip find(Long id) {
        return tripDao.find(id);
    }

    @Transactional
    public Trip findByString(String stringId) {
        return tripDao.find(stringId);
    }

    @Transactional
    public void create(Trip trip) {

        for (TripSession session: trip.getSessions()) {
            tripSessionDao.persist(session);
        }
        tripDao.persist(trip);
    }

    @Transactional
    public void signUpToTrip(String stringId) {
        Trip trip = tripDao.find(stringId);
    }

    @Transactional
    public List<Trip> findAfford() {
        List<Trip> trips;
        return null;
    }

    @Transactional
    public List<Trip> findNotAfford() {
        List<Trip> trips;
        return null;
    }

    @Transactional
    public void update(String stringId, Trip newTrip) throws NotFoundException {
        Trip trip = tripDao.find(stringId);

        if (trip == null) throw new NotFoundException();
        //pridat vynimku na rolu

        newTrip.setId(trip.getId());
        tripDao.update(newTrip);


//        trip.setDescription(newTrip.getDescription());
//        trip.setName(newTrip.getName());
//        trip.setPossible_xp_reward(newTrip.getPossible_xp_reward());
//        trip.setRating(newTrip.getRating());
//        trip.setLocation(newTrip.getLocation());
//        trip.setCategory(newTrip.getCategory());
//        trip.setDeposit(newTrip.getDeposit());
//        trip.setGain_achievements(newTrip.getGain_achievements());
//        trip.setRequired_achievements(newTrip.getRequired_achievements());
//        trip.setRequiered_level(newTrip.getRequiered_level());
//        trip.setReviews(newTrip.getReviews());
//        trip.setSessions(newTrip.getSessions());
//        trip.setShort_name(newTrip.getShort_name());

//        tripDao.update(trip);
    }

    @Transactional
    public void delete(String stringId) throws NotFoundException {

        Trip trip = tripDao.find(stringId);
        if (trip == null) throw new NotFoundException();

        for (TripSession session :trip.getSessions()) {
            session.softDelete();
            tripSessionDao.update(session);
        }

        for (TripReview review: trip.getReviews()) {
            review.softDelete();
            tripReviewDao.update(review);
        }

        trip.softDelete();
        tripDao.update(trip);
    }



}
