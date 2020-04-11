package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TripDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripReviewDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripSessionDao;
import cz.cvut.fel.rsp.travelandwork.dto.TripDto;
import cz.cvut.fel.rsp.travelandwork.exception.BadDateException;
import cz.cvut.fel.rsp.travelandwork.exception.MissingVariableException;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.model.TripReview;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TripService {

    private final TripDao tripDao;
    private final TripSessionDao tripSessionDao;
    private final TripReviewDao tripReviewDao;
    private final TranslateService translateService;

    @Autowired
    public TripService(TripDao tripDao, TripSessionDao tripSessionDao, TripReviewDao tripReviewDao, TranslateService translateService) {
        this.tripDao = tripDao;
        this.tripSessionDao = tripSessionDao;
        this.tripReviewDao = tripReviewDao;
        this.translateService = translateService;

    }

    @Transactional
    public List<Trip> findAll() {
        return tripDao.findAll();
    }

    @Transactional
    public List<TripDto> findAllDto() {
        List<TripDto> tripDtos = new ArrayList<>();

        for (Trip trip:tripDao.findAll()) {
            tripDtos.add(translateService.translateTrip(trip));
        }
        return tripDtos;
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
    public void create(Trip trip) throws BadDateException, MissingVariableException {

        Objects.requireNonNull(trip);
        if (trip.getSessions().size()<=0) throw new MissingVariableException();
        tripDao.persist(trip);
        for (TripSession session: trip.getSessions()) {
            if (session.getTo_date().isBefore(session.getFrom_date())) {
                tripDao.remove(trip);
                throw new BadDateException();
            }
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
    public void update(String stringId, Trip newTrip) throws BadDateException, NotFoundException, MissingVariableException {
        Trip trip = tripDao.find(stringId);

        if (trip == null) throw new NotFoundException();
        //todo pridat vynimku na rolu

        newTrip.setId(trip.getId());

        newTrip.setReviews(trip.getReviews());
        if (newTrip.getSessions().size()<=0) throw new MissingVariableException();

        //less new sessions
        if (newTrip.getSessions().size() < trip.getSessions().size()){
            for ( int i = newTrip.getSessions().size() ; i < trip.getSessions().size(); i++) {
                tripSessionDao.remove(trip.getSessions().get(i));
            }
        }

        for (int i = 0; i < newTrip.getSessions().size() ; i++) {
            TripSession newSession = newTrip.getSessions().get(i);
            if (newSession.getTo_date().isBefore(newSession.getFrom_date())) throw new BadDateException();

                if (i <= trip.getSessions().size()-1 ){
                TripSession oldSession = trip.getSessions().get(i);

                newTrip.getSessions().get(i).setId(oldSession.getId());
                oldSession = newSession;
                oldSession.setTrip(trip);
                tripSessionDao.update(oldSession);
            } else {
                    newSession.setTrip(trip);
                    tripSessionDao.persist(newSession);
                }
        }

        trip=newTrip;
        tripDao.update(trip);
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
