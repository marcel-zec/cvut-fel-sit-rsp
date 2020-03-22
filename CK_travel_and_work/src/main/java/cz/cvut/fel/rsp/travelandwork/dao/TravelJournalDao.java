package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.TravelJournal;
import org.springframework.stereotype.Repository;

@Repository
public class TravelJournalDao extends BaseDao<TravelJournal> {
    protected TravelJournalDao() {
        super(TravelJournal.class);
    }
}
