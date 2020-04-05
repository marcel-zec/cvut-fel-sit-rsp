package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TripDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripSessionDao;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TripSessionService {

    private TripSessionDao tripSessionDao;
    private TripDao tripDao;

    @Autowired
    public TripSessionService(TripSessionDao tripSessionDao, TripDao tripDao) {
        this.tripSessionDao = tripSessionDao;
        this.tripDao = tripDao;
    }

    @Transactional
    public List<TripSession> findAllInTrip(String trip_short_name) {
        return tripSessionDao.find(trip_short_name);
    }

    @Transactional
    public void create(String tripId, TripSession tripSession) throws Exception {
        if (tripSession.getTo_date().isBefore(tripSession.getFrom_date())) throw new Exception();
        tripSession.setTrip(tripDao.find(tripId));
        tripSessionDao.persist(tripSession);
    }
}
