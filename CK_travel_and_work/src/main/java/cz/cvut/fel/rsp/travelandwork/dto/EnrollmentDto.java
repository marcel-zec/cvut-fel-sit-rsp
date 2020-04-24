package cz.cvut.fel.rsp.travelandwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.cvut.fel.rsp.travelandwork.model.EnrollmentState;

import javax.persistence.Basic;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

public class EnrollmentDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDateTime enrollDate;

    @Basic(optional = false)
    private boolean deposit_was_paid;

    @Basic(optional = false)
    private int actual_xp_reward;

    @Enumerated(EnumType.STRING)
    private EnrollmentState state;

    private List<AchievementDto> recieved_achievements;
    private Long travelJournalId;
    private TripDto trip;
    private TripSessionDto tripSession;


    public EnrollmentDto(@NotNull(message = "Id cannot be blank") Long id, @PastOrPresent LocalDateTime enrollDate, boolean deposit_was_paid, int actual_xp_reward, EnrollmentState state, List<AchievementDto> recieved_achievements,
                         Long travelJournalId, TripDto trip, TripSessionDto tripSession) {

        this.id = id;
        this.enrollDate = enrollDate;
        this.deposit_was_paid = deposit_was_paid;
        this.actual_xp_reward = actual_xp_reward;
        this.state = state;
        this.recieved_achievements = recieved_achievements;
        this.travelJournalId = travelJournalId;
        this.trip = trip;
        this.tripSession = tripSession;
    }


    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public LocalDateTime getEnrollDate() {

        return enrollDate;
    }


    public void setEnrollDate(LocalDateTime enrollDate) {

        this.enrollDate = enrollDate;
    }


    public boolean isDeposit_was_paid() {

        return deposit_was_paid;
    }


    public void setDeposit_was_paid(boolean deposit_was_paid) {

        this.deposit_was_paid = deposit_was_paid;
    }


    public int getActual_xp_reward() {

        return actual_xp_reward;
    }


    public void setActual_xp_reward(int actual_xp_reward) {

        this.actual_xp_reward = actual_xp_reward;
    }


    public EnrollmentState getState() {

        return state;
    }


    public void setState(EnrollmentState state) {

        this.state = state;
    }


    public List<AchievementDto> getRecieved_achievements() {

        return recieved_achievements;
    }


    public void setRecieved_achievements(List<AchievementDto> recieved_achievements) {

        this.recieved_achievements = recieved_achievements;
    }


    public Long getTravelJournalId() {

        return travelJournalId;
    }


    public void setTravelJournalId(Long travelJournalId) {

        this.travelJournalId = travelJournalId;
    }


    public TripDto getTrip() {

        return trip;
    }


    public void setTrip(TripDto trip) {

        this.trip = trip;
    }


    public TripSessionDto getTripSession() {

        return tripSession;
    }


    public void setTripSession(TripSessionDto tripSession) {

        this.tripSession = tripSession;
    }
}
