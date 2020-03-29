package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.AchievementDao;
import cz.cvut.fel.rsp.travelandwork.model.Achievement;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
public class AchievementService {
    private final AchievementDao achievementDao;

    public AchievementService(AchievementDao achievementDao) {
        this.achievementDao = achievementDao;
    }

    @Transactional
    public List<Achievement> findAll() {
        return achievementDao.findAll();
    }

    @Transactional
    public Achievement find(Long id) {
        return achievementDao.find(id);
    }

    @Transactional
    public void persist(Achievement achievement) {
        achievementDao.persist(achievement);
    }

    @Transactional
    public void update(Achievement achievement) {
        Objects.requireNonNull(achievement);
        achievementDao.update(achievement);
    }


}
