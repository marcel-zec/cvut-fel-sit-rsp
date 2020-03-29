package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Objects;


@Repository
public class TripDao extends BaseDao<Trip> {
    public TripDao() {
        super(Trip.class);
    }

    public Trip find(String id){
        {
            try {
                return em.createNamedQuery("Trip.findByStringId", Trip.class).setParameter("id", id)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    public List<Trip> find(int requiered_level){
        {
            try {
                return em.createNamedQuery("Trip.findByLevel", Trip.class).setParameter("requiered_level", requiered_level)
                        .getResultList();
            } catch (NoResultException e) {
                return null;
            }
        }
    }
}