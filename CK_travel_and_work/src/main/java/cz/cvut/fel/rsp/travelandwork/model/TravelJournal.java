package cz.cvut.fel.rsp.travelandwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "TRAVELJOURNAL")
public class TravelJournal extends AbstractEntity{
    @Basic(optional = false)
    @Column(nullable = false)
    private int xp_count;

    @Basic(optional = false)
    @Column(nullable = false)
    private HashMap<Category, Integer> trip_counter;

    @OneToOne(mappedBy = "travel_journal")
    private User user;

    @ManyToMany
    private List<Achievement> earnedAchievements;

    public int getXp_count() {
        return xp_count;
    }

    public HashMap<Category, Integer> getTrip_counter() {
        return trip_counter;
    }

    public User getUser() {
        return user;
    }

    public List<Achievement> getEarnedAchievements() {
        return earnedAchievements;
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

    public void setEarnedAchievements(List<Achievement> earnedAchievements) {
        this.earnedAchievements = earnedAchievements;
    }
}
