package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.Achievement;
import org.springframework.stereotype.Repository;

@Repository
public class AchievementDao extends BaseDao<Achievement>{
    protected AchievementDao() {
        super(Achievement.class);
    }
}
