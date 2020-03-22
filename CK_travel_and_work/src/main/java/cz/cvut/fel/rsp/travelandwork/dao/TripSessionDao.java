package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import org.springframework.stereotype.Repository;

@Repository
public class TripSessionDao extends BaseDao<TripSession> {
    public TripSessionDao() {
        super(TripSession.class);
    }
}