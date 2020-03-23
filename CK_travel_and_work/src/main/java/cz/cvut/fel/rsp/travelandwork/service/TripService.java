package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TripDao;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
