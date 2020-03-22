package cz.cvut.fel.rsp.travelandwork.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "TRIP")
public class Trip extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    @Size(max = 255, min = 3, message = "Name have to be min 3 and max 255 characters.")
    @NotBlank(message = "Name have to be min 3 and max 255 characters.")
    private String name;

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    @Min(value = 0, message = "Min 0")
    @Max(value = 20, message = "Max 20")
    private Integer possible_xp_reward;

    @Basic(optional = false)
    @Column(nullable = false)
    @Size(max = 255, min = 0, message = "Max 255 characters.")
    private String description;

    @Basic(optional = false)
    @Column(nullable = false)
    @Size(max = 12, message = "Max length is 12.")
    private Integer phone_number;

    @Basic(optional = false)
    @Column(nullable = false)
    @Min(value = 0, message = "Min 0")
    @Max(value = 5, message = "Max 5")
    private double rating;

    @OrderBy("from ASC")
    @OneToMany(mappedBy = "trip")
    private List<TripSession> sessions;

    @OrderBy("date ASC")
    @OneToMany(mappedBy = "trip")
    private List<TripReview> reviews;

    public Trip() {
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

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
