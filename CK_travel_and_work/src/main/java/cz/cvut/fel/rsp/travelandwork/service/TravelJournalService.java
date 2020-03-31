package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.TravelJournalDao;
import cz.cvut.fel.rsp.travelandwork.model.TravelJournal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TravelJournalService {
    private final TravelJournalDao dao;

    @Autowired
    public TravelJournalService(TravelJournalDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void addTrip(TravelJournal travelJournal, Trip trip) {
        Objects.requireNonNull(trip);
        Objects.requireNonNull(travelJournal);
        travelJournal.addTrip(trip);
        dao.update(travelJournal);
    }
}
