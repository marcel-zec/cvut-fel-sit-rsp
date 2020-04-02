package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TripDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripReviewDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripSessionDao;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.Achievement;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.model.TripReview;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TripService {

    private final TripDao tripDao;
    private final TripSessionDao tripSessionDao;
    private final TripReviewDao tripReviewDao;

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

        tripDao.persist(trip);
        trip = tripDao.find(trip.getShort_name());
        for (TripSession session: trip.getSessions()) {
            session.setTrip(trip);
            tripSessionDao.persist(session);
        }
       tripDao.update(trip);
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
        //todo pridat vynimku na rolu

        newTrip.setId(trip.getId());

        //less new sessions
        if (newTrip.getSessions().size() < trip.getSessions().size()){
            for ( int i = newTrip.getSessions().size() ; i < trip.getSessions().size(); i++) {
                trip.getSessions().get(i).softDelete();
                tripSessionDao.update( trip.getSessions().get(i));
            }
        }

        for (int i = 0; i < newTrip.getSessions().size() ; i++) {
                if (i <= trip.getSessions().size()-1 ){
                TripSession oldSession = trip.getSessions().get(i);
                TripSession newSession = newTrip.getSessions().get(i);

                newTrip.getSessions().get(i).setId(oldSession.getId());
                oldSession = newSession;
                oldSession.setTrip(trip);
                tripSessionDao.update(oldSession);
            } else {
                    TripSession newSession = newTrip.getSessions().get(i);
                    newSession.setTrip(trip);
                    tripSessionDao.persist(newSession);
                }
        }

        trip=newTrip;
        tripDao.update(trip);

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
//
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
