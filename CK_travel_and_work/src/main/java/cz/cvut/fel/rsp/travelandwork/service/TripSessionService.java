package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TripSessionDao;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TripSessionService {

    private TripSessionDao tripSessionDao;

    @Autowired
    public TripSessionService(TripSessionDao tripSessionDao) {
        this.tripSessionDao = tripSessionDao;
    }

    @Transactional
    public List<TripSession> findAllInTrip(String trip_short_name) {
        return tripSessionDao.find(trip_short_name);
    }
}
