package cz.cvut.fel.rsp.travelandwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "ACHIEVEMENT")
public class Achievement extends AbstractEntity{
    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(nullable = false)
    private String description;

    @Basic(optional = false)
    @Column(nullable = false)
    private String icon;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "category_id", nullable = false)
//    private Category category;

    @JsonIgnore
    @ManyToMany
    private List<Trip> trips;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "achievement_owned_travel_journals",
            joinColumns = @JoinColumn(name = "achievement_id"),
            inverseJoinColumns = @JoinColumn(name = "traveljournal_id"))
    private List<TravelJournal> owned_travel_journals;

    public Achievement() {
    }

    public Achievement(String name, String description, String icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


//    public Category getCategory() {
//        return category;
//    }

//    public void setCategory(Category category) {
//        this.category = category;
//    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<TravelJournal> getOwned_travel_journals() {
        return owned_travel_journals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwned_travel_journals(List<TravelJournal> owned_travel_journals) {
        this.owned_travel_journals = owned_travel_journals;
    }

    public List<Trip> getTrips() {
        return trips;
    }


    public void setTrips(List<Trip> trips) {

        this.trips = trips;
    }


}
