package cz.cvut.fel.rsp.travelandwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRIP_REVIEW")
@NamedQueries({
        @NamedQuery(name = "TripReview.findByTripId", query = "SELECT t FROM Trip t WHERE t.short_name = :id AND t.deleted_at is null")
        })
public class TripReview extends AbstractEntity {

    @Basic(optional = false)
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tripsession_id", nullable = false)
    private TripSession tripSession;

    public TripReview() {
        date = LocalDateTime.now();
    }

    public TripReview(@Size(max = 255, min = 0, message = "Max 255 characters.") String note, LocalDateTime date, @Min(value = 0, message = "Min 0") @Max(value = 5, message = "Max 5") double rating, User author, TripSession tripSession) {
        this.note = note;
        this.date = date;
        this.rating = rating;
        this.setAuthor(author);
        this.setTripSession(tripSession);
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

    public User getAuthor() {
        return author;
    }


    public TripSession getTripSession() {
        return tripSession;
    }

    public void setTripSession(TripSession tripSession) {
        this.tripSession = tripSession;
    }

    public void setAuthor(User author) {
        this.author = author;
        author.addTripReview(this);
    }

}
