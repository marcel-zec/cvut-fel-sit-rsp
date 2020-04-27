package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.AchievementCertificate;
import cz.cvut.fel.rsp.travelandwork.model.AchievementSpecial;
import org.springframework.stereotype.Repository;

@Repository
public class AchievementSpecialDao extends BaseDao<AchievementSpecial>{
    protected AchievementSpecialDao() {
        super(AchievementSpecial.class);
    }
}
