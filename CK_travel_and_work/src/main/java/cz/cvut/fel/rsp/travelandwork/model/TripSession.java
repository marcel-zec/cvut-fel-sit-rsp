package cz.cvut.fel.rsp.travelandwork.model;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRIP_SESSION")
public class TripSession extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDateTime fromDate;

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDateTime toDate;

    @Basic(optional = false)
    @Column(nullable = false)
    @Min(value = 0, message = "Min 0")
    @Max(value = 3000, message = "Max 3000")
    private double price;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public TripSession() {}

    public TripSession(LocalDateTime fromDate, LocalDateTime toDate, double price) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
