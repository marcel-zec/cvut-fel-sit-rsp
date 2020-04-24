package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.AchievementCertificate;
import org.springframework.stereotype.Repository;

@Repository
public class AchievementCertificateDao extends BaseDao<AchievementCertificate> {
    protected AchievementCertificateDao() {
        super(AchievementCertificate.class);
    }
}
