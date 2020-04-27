package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.AchievementCategorized;
import org.springframework.stereotype.Repository;

@Repository
public class AchievementCategorizedDao extends BaseDao<AchievementCategorized> {
    protected AchievementCategorizedDao() {
        super(AchievementCategorized.class);
    }
}
