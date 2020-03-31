package cz.cvut.fel.rsp.travelandwork.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Trip.findByStringId", query = "SELECT t FROM Trip t WHERE t.short_name = :id AND t.deleted_at is null")
})
public class Trip extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @Size(max = 255, min = 3, message = "Name has to be from 3 to 255 characters.")
    @NotBlank(message = "Name has to be from 3 to 255 characters.")
    private String name;

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    @Size(max = 100, min = 3, message = "Short name has to be from 3 to 100 characters.")
    @NotBlank(message = "Short name has to be from 3 to 100 characters.")
    private String short_name;

    @Basic(optional = false)
    @Column(nullable = false)
    @Min(value = 0, message = "Min 0")
    @Max(value = 20, message = "Max 20")
    private int possible_xp_reward;

    @Basic(optional = false)
    @Column(nullable = false)
    @Size(max = 3000, min = 0, message = "Max 3000 characters.")
    private String description;

    @Basic(optional = false)
    @Column(nullable = false)
    @Min(value = 0, message = "Min 0")
    @Max(value = 5, message = "Max 5")
    private double rating;

    @Basic(optional = false)
    @Column(nullable = false)
    @Min(value = 0, message = "Min 0")
    @Max(value = 10000, message = "Max 10 000")
    private double deposit;

    @Basic(optional = false)
    @Column(nullable = false)
    @Size(max = 200, min = 0, message = "Max 200 characters.")
    private String location;

    @Basic(optional = false)
    @Column(nullable = false)
    private int requiered_level;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToMany
    @JoinTable(
            name = "required_achievement_trip",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private List<Achievement> required_achievements;

    @ManyToMany
    @JoinTable(
            name = "gain_achievement_trip",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private List<Achievement> gain_achievements;

    @OrderBy("from_date ASC")
    @OneToMany(mappedBy = "trip")
    private List<TripSession> sessions;

    @OrderBy("date ASC")
    @OneToMany(mappedBy = "trip")
    private List<TripReview> reviews;

    public Trip() {
    }

    public Trip(@Size(max = 255, min = 3, message = "Name has to be from 3 to 255 characters.")  @NotBlank(message = "Name has to be from 3 to 255 characters.") String name,
                @Min(value = 0, message = "Min 0") @Max(value = 20, message = "Max 20") int possible_xp_reward,
                @Size(max = 3000, min = 0, message = "Max 3000 characters.") String description,
                @Size(max = 100, min = 3, message = "Short name has to be from 3 to 100 characters.")  @NotBlank(message = "Short name has to be from 3 to 100 characters.") String short_name,
                @Min(value = 0, message = "Min 0") @Max(value = 10000, message = "Max 10 000") double deposit,
                @Size(max = 200, min = 0, message = "Max 200 characters.") String location,
                @Min(value = 0, message = "Min 0") @Max(value = 100, message = "Max 100") int required_level
                ) {
        this.name = name;
        this.deposit = deposit;
        this.possible_xp_reward = possible_xp_reward;
        this.description = description;
        this.location= location;
        this.requiered_level = required_level;
        this.short_name = short_name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPossible_xp_reward() {
        return possible_xp_reward;
    }

    public void setPossible_xp_reward(Integer possible_xp_reward) {
        this.possible_xp_reward = possible_xp_reward;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<TripSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<TripSession> sessions) {
        this.sessions = sessions;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {

        this.location = location;
    }


    public double getDeposit() {

        return deposit;
    }


    public void setDeposit(double deposit) {

        this.deposit = deposit;
    }


    public int getRequiered_level() {

        return requiered_level;
    }


    public void setRequiered_level(int requiered_level) {

        this.requiered_level = requiered_level;
    }


    public List<Achievement> getRequired_achievements() {

        return required_achievements;
    }


    public void setRequired_achievements(List<Achievement> required_achievements) {

        this.required_achievements = required_achievements;
    }


    public List<Achievement> getGain_achievements() {

        return gain_achievements;
    }


    public void setGain_achievements(List<Achievement> gain_achievements) {

        this.gain_achievements = gain_achievements;
    }


    public List<TripReview> getReviews() {

        return reviews;
    }


    public void setReviews(List<TripReview> reviews) {

        this.reviews = reviews;
    }
}
