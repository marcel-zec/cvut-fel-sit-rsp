package cz.cvut.fel.rsp.travelandwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_REVIEW")
public class UserReview extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @Size(max = 255, min = 0, message = "Max 255 characters.")
    private String note;

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDateTime date;

    @Basic(optional = false)
    @Column(nullable = false)
    @Min(value = 0, message = "Min 0")
    @Max(value = 5, message = "Max 5")
    private double rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "trip_session_id", nullable = false)
    private TripSession tripSession;


    public UserReview() {
        this.date = LocalDateTime.now();
    }


    public String getNote() {

        return note;
    }


    public void setNote(String note) {

        this.note = note;
    }


    public LocalDateTime getDate() {

        return date;
    }


    public void setDate(LocalDateTime date) {

        this.date = date;
    }


    public double getRating() {

        return rating;
    }


    public void setRating(double rating) {

        this.rating = rating;
    }


    public User getUser() {

        return user;
    }


    public void setUser(User user) {

        this.user = user;
    }


    public User getAuthor() {

        return author;
    }


    public void setAuthor(User author) {

        this.author = author;
    }


    public TripSession getTripSession() {

        return tripSession;
    }


    public void setTripSession(TripSession tripSession) {

        this.tripSession = tripSession;
    }
}
