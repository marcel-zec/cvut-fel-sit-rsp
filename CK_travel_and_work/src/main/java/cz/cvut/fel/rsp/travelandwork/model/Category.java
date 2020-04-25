package cz.cvut.fel.rsp.travelandwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category extends AbstractEntity{

    @Basic(optional = false)
    @Column(nullable = false, length = 30, unique = true)
    @NotBlank(message = "Name of category cannot be blank")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Trip> trips;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<AchievementCategorized> achievementsWithCategory;

    public Category() {
    }

    public Category(@NotBlank(message = "Name of category cannot be blank") String name) {
        this.name = name;
    }

    public boolean add(Trip trip){
        return trips.add(trip);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void addTrips(Trip trip) {
        if (this.trips == null) this.trips = new ArrayList<Trip>();
        if (!this.trips.contains(trip)){
            this.trips.add(trip);
        }
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<AchievementCategorized> getAchievementsWithCategory() {
        return achievementsWithCategory;
    }

    public void setAchievementsWithCategory(List<AchievementCategorized> achievementsWithCategory) {
        this.achievementsWithCategory = achievementsWithCategory;
    }

    public void addAchievementsWithCategory(AchievementCategorized achvm) {
        this.achievementsWithCategory.add(achvm);
    }
}

