package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.Trip;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;


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

    public List<Trip> find(int required_level){

        {
            try {
                return em.createNamedQuery("Trip.findByLevel", Trip.class).setParameter("required_level", required_level)
                        .getResultList();
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    public List<Trip> findByFilter(String location, LocalDate from_date, LocalDate to_date, double price){
        try {
                return em.createNamedQuery("Trip.findByFilter", Trip.class)
                        .setParameter("location", location)
                        .setParameter("from_date", from_date)
                        .setParameter("to_date", to_date)
                        .setParameter("price", price)
                        .getResultList();
        } catch (NoResultException e) {
                return null;
        }
    }
}