package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.AchievementCategorizedDao;
import cz.cvut.fel.rsp.travelandwork.dao.TravelJournalDao;
import cz.cvut.fel.rsp.travelandwork.model.AchievementCategorized;
import cz.cvut.fel.rsp.travelandwork.model.Category;
import cz.cvut.fel.rsp.travelandwork.model.TravelJournal;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TravelJournalService {
    private final TravelJournalDao dao;
    private final AchievementCategorizedService achievementCategorizedService;

    @Autowired
    public TravelJournalService(TravelJournalDao dao, AchievementCategorizedService achievementCategorizedService) {
        this.dao = dao;
        this.achievementCategorizedService = achievementCategorizedService;
    }

    @Transactional
    public void addTrip(TravelJournal travelJournal, Trip trip) {
        Objects.requireNonNull(trip);
        Objects.requireNonNull(travelJournal);
        travelJournal.addTrip(trip);
        dao.update(travelJournal);
        checkCategorizedAchievements(trip.getCategory(), travelJournal);
    }


    //this should be used after finalizing/closing the enrollment and adding new trip to hashmap in travel journal
    @Transactional
    public void checkCategorizedAchievements(Category category, TravelJournal currentTravelJournal) {
        int numberOfTripsInCat = currentTravelJournal.findAndGetCategoryValueIfExists(category);
        List<AchievementCategorized> categorizedAchievements = achievementCategorizedService.findAllInCategory(category);
        List<AchievementCategorized> ownedAchievements = currentTravelJournal.getEarnedAchievementsCategorized();

        if((numberOfTripsInCat == -1) || (categorizedAchievements == null)) return;

        for(AchievementCategorized cA : categorizedAchievements) {
            if((cA.getLimit() <= numberOfTripsInCat) && (!ownedAchievements.contains(cA))) {
                currentTravelJournal.addEarnedAchievementCategorized(cA);
                cA.addTravelJournal(currentTravelJournal);
                achievementCategorizedService.update(cA);
            }
        }

        dao.update(currentTravelJournal);
    }
}
