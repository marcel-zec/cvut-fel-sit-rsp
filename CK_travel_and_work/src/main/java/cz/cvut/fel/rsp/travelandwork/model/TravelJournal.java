package cz.cvut.fel.rsp.travelandwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "TRAVELJOURNAL")
public class TravelJournal extends AbstractEntity{
    @Basic(optional = false)
    @Column(nullable = false)
    private int xp_count = 0;


    //@Basic(optional = false)
    @Column(nullable = false)
    private HashMap<Category, Integer> trip_counter;

    @JsonIgnore
    @OneToOne(mappedBy = "travel_journal")
    private User user;

    @ManyToMany
    private List<AchievementCertificate> certificates;

    @ManyToMany
    private List<AchievementCategorized> earnedAchievementsCategorized;

    @ManyToMany
    private List<AchievementSpecial> earnedAchievementsSpecial;

    @OneToMany(mappedBy = "travelJournal")
    private List<Enrollment> enrollments;

    public TravelJournal() {
        this.user = new User();
        this.trip_counter = new HashMap<Category,Integer>();
        this.enrollments = new ArrayList<Enrollment>();
        this.earnedAchievementsCategorized = new ArrayList<AchievementCategorized>();
        this.earnedAchievementsSpecial = new ArrayList<AchievementSpecial>();
        this.certificates = new ArrayList<AchievementCertificate>();
    }

    public TravelJournal(User user) {
        this.user = user;
        this.trip_counter = new HashMap<Category,Integer>();
        this.enrollments = new ArrayList<Enrollment>();
        this.earnedAchievementsCategorized = new ArrayList<AchievementCategorized>();
        this.earnedAchievementsSpecial = new ArrayList<AchievementSpecial>();
        this.certificates = new ArrayList<AchievementCertificate>();
    }


    public TravelJournal() {
    }

    public int getXp_count() {
        return xp_count;
    }

    public HashMap<Category, Integer> getTrip_counter() {
        return trip_counter;
    }

    public User getUser() {
        return user;
    }

    public void setXp_count(int xp_count) {
        this.xp_count = xp_count;
    }

    public void setTrip_counter(HashMap<Category, Integer> trip_counter) {
        this.trip_counter = trip_counter;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AchievementCertificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<AchievementCertificate> earnedAchievements) {
        this.certificates = earnedAchievements;
    }

    public void addCertificate(AchievementCertificate achievementCertificate) {
        this.certificates.add(achievementCertificate);
    }

    public boolean hasCertificate(AchievementCertificate achievementCertificate) {
        return this.certificates.contains(achievementCertificate);
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }


    public void setEnrollments(List<Enrollment> enrollments) {

        this.enrollments = enrollments;
    }

    public void addEnrollment(Enrollment enrollment){
        if (enrollments == null) enrollments = new ArrayList<Enrollment>();
        enrollments.add(enrollment);
    }

    public List<AchievementCategorized> getEarnedAchievementsCategorized() {
        return earnedAchievementsCategorized;
    }

    public void setEarnedAchievementsCategorized(List<AchievementCategorized> earnedAchievementsCategorized) {
        this.earnedAchievementsCategorized = earnedAchievementsCategorized;
    }

    public void addEarnedAchievementCategorized(AchievementCategorized achievementCategorized) {
        this.earnedAchievementsCategorized.add(achievementCategorized);
    }

    public List<AchievementSpecial> getEarnedAchievementsSpecial() {
        return earnedAchievementsSpecial;
    }

    public void setEarnedAchievementsSpecial(List<AchievementSpecial> earnedAchievementsSpecial) {
        this.earnedAchievementsSpecial = earnedAchievementsSpecial;
    }

    public void addEarnedAchievementSpecial(AchievementSpecial achievementSpecial) {
        this.earnedAchievementsSpecial.add(achievementSpecial);
    }
    /**
     * Adds trip to travel journal
     * If travel journal already contains the category, adds one more.
     * If doesn't, adds a new category counted with one trip in there.
     */
    public void addTrip(Trip trip){
        int actualValue = findAndGetCategoryValueIfExists(trip.getCategory());
        if(actualValue != -1) {
            actualValue++;
            this.trip_counter.put(trip.getCategory(), actualValue);
        }
        else{
            this.trip_counter.put(trip.getCategory(), 1);
        }
        addsXp(trip.getPossible_xp_reward());
    }

    private int findAndGetCategoryValueIfExists(Category category){
        for (Category key: this.trip_counter.keySet()) {
            if(key.equals(category)){
                return this.trip_counter.get(key);
            }
        }
        return -1;
    }

    private void addsXp(int xp){
        this.xp_count += xp;
    }
}
