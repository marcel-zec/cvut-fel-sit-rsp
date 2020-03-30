package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.Achievement;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class AchievementDao extends BaseDao<Achievement>{
    protected AchievementDao() {
        super(Achievement.class);
    }

    public Achievement find(Long id){
        {
            try {
                return em.createNamedQuery("Achievement.findById", Achievement.class).setParameter("id", id)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
    }


}
