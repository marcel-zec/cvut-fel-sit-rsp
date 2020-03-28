package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TripDao;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TripService {

    private TripDao tripDao;

    @Autowired
    public TripService(TripDao tripDao) {
        this.tripDao = tripDao;
    }


    @Transactional
    public void signUpToTrip(Long id) {
        Trip trip = tripDao.find(id);
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
    public Trip findAfford(Long id) {
        return tripDao.find(id);
    }

    @Transactional
    public Trip findNotAfford(Long id) {
        return tripDao.find(id);
    }

    @Transactional
    public void update(String stringId, Trip newTrip) throws NotFoundException {
        Trip trip = tripDao.find(stringId);

        if (trip == null) throw new NotFoundException();
        //pridat vynimku na rolu

        trip.setDescription(newTrip.getDescription());
        trip.setName(newTrip.getName());
        trip.setPhone_number(newTrip.getPhone_number());
        trip.setPossible_xp_reward(newTrip.getPossible_xp_reward());
        trip.setRating(newTrip.getRating());
        tripDao.update(trip);
    }

    @Transactional
    public void delete(String stringId) throws NotFoundException {

        Trip trip = tripDao.find(stringId);
        if (trip == null) throw new NotFoundException();
        // urobit priznak
    }



}
