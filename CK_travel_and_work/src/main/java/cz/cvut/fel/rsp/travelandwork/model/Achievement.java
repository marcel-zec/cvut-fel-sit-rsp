package cz.cvut.fel.rsp.travelandwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

public abstract class Achievement extends AbstractEntity{

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(nullable = false)
    private String description;

    @Basic(optional = false)
    @Column(nullable = false)
    private String icon;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void addTrips(Trip trip) {
        this.trips.add(trip);
    }

    public void removeTrips(Trip trip) {
        this.trips.remove(trip);
    }

    public List<TravelJournal> getOwned_travel_journals() {
        return owned_travel_journals;
    }

    public void setOwned_travel_journals(List<TravelJournal> owned_travel_journals) {
        this.owned_travel_journals = owned_travel_journals;
    }

    public void addOwned_travel_journals(TravelJournal journal) {
        this.owned_travel_journals.add(journal);
    }

    public void removeOwned_travel_journal(TravelJournal journal) {
        this.owned_travel_journals.remove(journal);
    }
}
