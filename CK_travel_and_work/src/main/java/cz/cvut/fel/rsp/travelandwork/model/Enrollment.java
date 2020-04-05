package cz.cvut.fel.rsp.travelandwork.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "ENROLLMENT")
public class Enrollment extends AbstractEntity {
    @Basic(optional = false)
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enrollDate;

    @Basic(optional = false)
    @Column(nullable = false)
    private boolean deposit_was_paid;

    @Basic(optional = false)
    @Column(nullable = false)
    private int actual_xp_reward;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private String state;

    @ManyToMany
    @JoinTable(
            name = "recieved_achievement_trip",
            joinColumns = @JoinColumn(name = "enrollment_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private List<Achievement> recieved_achievements;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "travelJournal_id", nullable = false)
    private TravelJournal travelJournal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tripSession_id", nullable = false)
    private TripSession tripSession;


    public Date getEnrollDate() {
        return enrollDate;
    }

    public boolean isDeposit_was_paid() {
        return deposit_was_paid;
    }

    public int getActual_xp_reward() {
        return actual_xp_reward;
    }

    public TravelJournal getTravelJournal() {
        return travelJournal;
    }

    public Trip getTrip() {
        return trip;
    }

    public TripSession getTripSession() {
        return tripSession;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public void setDeposit_was_paid(boolean deposit_was_paid) {
        this.deposit_was_paid = deposit_was_paid;
    }

    public void setActual_xp_reward(int actual_xp_reward) {
        this.actual_xp_reward = actual_xp_reward;
    }

    public void setTravelJournal(TravelJournal travelJournal) {
        this.travelJournal = travelJournal;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setTripSession(TripSession tripSession) {
        this.tripSession = tripSession;
    }


    public String getState() {

        return state;
    }


    public void setState(String state) {

        this.state = state;
    }
}
