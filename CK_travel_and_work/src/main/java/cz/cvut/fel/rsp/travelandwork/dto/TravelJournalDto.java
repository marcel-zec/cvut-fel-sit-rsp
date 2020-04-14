package cz.cvut.fel.rsp.travelandwork.dto;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

public class TravelJournalDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    private int xp_count;

    @Basic(optional = false)
    private HashMap<CategoryDto, Integer> trip_counter;

    private Long userId;
    private List<AchievementDto> earnedAchievements;
    private List<EnrollmentDto> enrollments;


    public TravelJournalDto(@NotNull(message = "Id cannot be blank") Long id, int xp_count, HashMap<CategoryDto, Integer> trip_counter,
                            Long userId, List<AchievementDto> earnedAchievements, List<EnrollmentDto> enrollments) {

        this.id = id;
        this.xp_count = xp_count;
        this.trip_counter = trip_counter;
        this.userId = userId;
        this.earnedAchievements = earnedAchievements;
        this.enrollments = enrollments;
    }


    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public int getXp_count() {

        return xp_count;
    }


    public void setXp_count(int xp_count) {

        this.xp_count = xp_count;
    }


    public HashMap<CategoryDto, Integer> getTrip_counter() {

        return trip_counter;
    }


    public void setTrip_counter(HashMap<CategoryDto, Integer> trip_counter) {

        this.trip_counter = trip_counter;
    }


    public Long getUserId() {

        return userId;
    }


    public void setUserId(Long userId) {

        this.userId = userId;
    }


    public List<AchievementDto> getEarnedAchievements() {

        return earnedAchievements;
    }


    public void setEarnedAchievements(List<AchievementDto> earnedAchievements) {

        this.earnedAchievements = earnedAchievements;
    }


    public List<EnrollmentDto> getEnrollments() {

        return enrollments;
    }


    public void setEnrollments(List<EnrollmentDto> enrollments) {

        this.enrollments = enrollments;
    }
}