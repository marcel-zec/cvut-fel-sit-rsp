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
    private Integer possible_xp_reward;

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
    private String location;

    @Basic(optional = false)
    @Column(nullable = false)
    private int requiered_level;

    @OneToMany(mappedBy = "trip")
    private List<Achievement> required_achievements;

    @OneToMany(mappedBy = "trip")
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
                @Min(value = 0, message = "Min 0") @Max(value = 20, message = "Max 20") Integer possible_xp_reward,
                @Size(max = 3000, min = 0, message = "Max 3000 characters.") String description,
                @Size(max = 12, message = "Max length is 12.") String phone_number,
                @Size(max = 100, min = 3, message = "Short name has to be from 3 to 100 characters.")  @NotBlank(message = "Short name has to be from 3 to 100 characters.") String short_name) {
        this.name = name;
        this.possible_xp_reward = possible_xp_reward;
        this.description = description;
        this.short_name = short_name;
        this.phone_number = phone_number;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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
}
