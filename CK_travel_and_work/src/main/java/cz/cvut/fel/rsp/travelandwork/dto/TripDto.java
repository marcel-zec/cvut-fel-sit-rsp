package cz.cvut.fel.rsp.travelandwork.dto;

import cz.cvut.fel.rsp.travelandwork.model.Achievement;
import cz.cvut.fel.rsp.travelandwork.model.Category;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

public class TripDto {

    @Basic(optional = false)
    @Size(max = 255, min = 3, message = "Name has to be from 3 to 255 characters.")
    @NotNull(message = "Name has to be from 3 to 255 characters.")
    private String name;

    @Basic(optional = false)
    @Size(max = 100, min = 3, message = "Short name has to be from 3 to 100 characters.")
    @NotNull(message = "Short name has to be from 3 to 100 characters.")
    private String short_name;

    @Basic(optional = false)
    @Min(value = 0, message = "Min 0")
    @Max(value = 20, message = "Max 20")
    private int possible_xp_reward;

    @Basic(optional = false)
    @Size(max = 3000, min = 0, message = "Max 3000 characters.")
    private String description;

    @Basic(optional = false)
    @Min(value = 0, message = "Min 0")
    @Max(value = 5, message = "Max 5")
    private double rating;

    @Basic(optional = false)
    @Min(value = 0, message = "Min 0")
    @Max(value = 10000, message = "Max 10 000")
    private double deposit;

    @Basic(optional = false)
    @Column(nullable = false)
    @Size(max = 200, min = 0, message = "Max 200 characters.")
    private String location;

    @Basic(optional = false)
    @Min(value = 0, message = "Min 0")
    private int required_level;

    private Category category;
    private List<Achievement> required_achievements;
    private List<Achievement> gain_achievements;


    public TripDto() {
    }


    public TripDto(@Size(max = 255, min = 3, message = "Name has to be from 3 to 255 characters.") @NotBlank(message = "Name has to be from 3 to 255 characters.") String name,
                   @Size(max = 100, min = 3, message = "Short name has to be from 3 to 100 characters.") @NotBlank(message = "Short name has to be from 3 to 100 characters.") String short_name,
                   @Min(value = 0, message = "Min 0") @Max(value = 20, message = "Max 20") int possible_xp_reward,
                   @Size(max = 3000, min = 0, message = "Max 3000 characters.") String description,
                   @Min(value = 0, message = "Min 0") @Max(value = 5, message = "Max 5") double rating,
                   @Min(value = 0, message = "Min 0") @Max(value = 10000, message = "Max 10 000") double deposit,
                   @Size(max = 200, min = 0, message = "Max 200 characters.") String location,
                   @Min(value = 0, message = "Min 0") int required_level, Category category,
                   List<Achievement> required_achievements, List<Achievement> gain_achievements) {
        this.name = name;
        this.short_name = short_name;
        this.possible_xp_reward = possible_xp_reward;
        this.description = description;
        this.rating = rating;
        this.deposit = deposit;
        this.location = location;
        this.required_level = required_level;
        this.category = category;
        this.required_achievements = required_achievements;
        this.gain_achievements = gain_achievements;
    }


    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
    }


    public String getShort_name() {

        return short_name;
    }


    public void setShort_name(String short_name) {

        this.short_name = short_name;
    }


    public int getPossible_xp_reward() {

        return possible_xp_reward;
    }


    public void setPossible_xp_reward(int possible_xp_reward) {

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


    public double getDeposit() {

        return deposit;
    }


    public void setDeposit(double deposit) {

        this.deposit = deposit;
    }


    public String getLocation() {

        return location;
    }


    public void setLocation(String location) {

        this.location = location;
    }


    public int getRequired_level() {

        return required_level;
    }


    public void setRequired_level(int required_level) {

        this.required_level = required_level;
    }


    public Category getCategory() {

        return category;
    }


    public void setCategory(Category category) {

        this.category = category;
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
}
