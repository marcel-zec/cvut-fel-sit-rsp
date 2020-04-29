package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.AchievementCategorizedDao;
import cz.cvut.fel.rsp.travelandwork.dao.AchievementCertificateDao;
import cz.cvut.fel.rsp.travelandwork.dao.AchievementSpecialDao;
import cz.cvut.fel.rsp.travelandwork.model.Achievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementService {
    private final AchievementCertificateDao achievementCertificateDao;
    private final AchievementCategorizedDao achievementCategorizedDao;
    private final AchievementSpecialDao achievementSpecialDao;


    @Autowired
    public AchievementService(AchievementCertificateDao achievementCertificateDao, AchievementCategorizedDao achievementCategorizedDao, AchievementSpecialDao achievementSpecialDao) {

        this.achievementCertificateDao = achievementCertificateDao;
        this.achievementCategorizedDao = achievementCategorizedDao;
        this.achievementSpecialDao = achievementSpecialDao;
    }

    @Transactional
    public List<Achievement> findAll(){
        List<Achievement> achievements = new ArrayList<>();
        achievements.addAll(achievementCategorizedDao.findAll());
        achievements.addAll(achievementCertificateDao.findAll());
        achievements.addAll(achievementSpecialDao.findAll());
        return achievements;
    }
}
