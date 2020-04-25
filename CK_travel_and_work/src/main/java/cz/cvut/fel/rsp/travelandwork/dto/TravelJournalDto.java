package cz.cvut.fel.rsp.travelandwork.dto;


import cz.cvut.fel.rsp.travelandwork.model.AchievementSpecial;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
    private List<AchievementCertificateDto> certificates;
    private List<AchievementCategorizedDto> categorized;
    private List<AchievementSpecialDto> special;
    private List<EnrollmentDto> enrollments;


    public TravelJournalDto(@NotNull(message = "Id cannot be blank") Long id, int xp_count, HashMap<CategoryDto, Integer> trip_counter, Long userId, List<AchievementCertificateDto> certificates, List<AchievementCategorizedDto> categorized, List<AchievementSpecialDto> special, List<EnrollmentDto> enrollments) {
        this.id = id;
        this.xp_count = xp_count;
        this.trip_counter = trip_counter;
        this.userId = userId;
        this.certificates = certificates;
        this.categorized = categorized;
        this.special = special;
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
        if (trip_counter==null) trip_counter = new HashMap<CategoryDto,Integer>();
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


    public List<AchievementCertificateDto> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<AchievementCertificateDto> certificates) {
        this.certificates = certificates;
    }

    public List<AchievementCategorizedDto> getCategorized() {
        return categorized;
    }

    public void setCategorized(List<AchievementCategorizedDto> categorized) {
        this.categorized = categorized;
    }

    public List<AchievementSpecialDto> getSpecial() {
        return special;
    }

    public void setSpecial(List<AchievementSpecialDto> special) {
        this.special = special;
    }

    public List<EnrollmentDto> getEnrollments() {
        if (enrollments==null) enrollments = new ArrayList<EnrollmentDto>();
        return enrollments;
    }


    public void setEnrollments(List<EnrollmentDto> enrollments) {

        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "TravelJournalDto{" +
                "id=" + id +
                ", xp_count=" + xp_count +
                ", trip_counter=" + trip_counter +
                ", userId=" + userId +
                ", earnedAchievements=" + earnedAchievements +
                ", enrollments=" + enrollments +
                '}';
    }
}
