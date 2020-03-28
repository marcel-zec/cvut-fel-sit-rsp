package cz.cvut.fel.rsp.travelandwork.model;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    public LocalDateTime getFrom() {
        return fromDate;
    }

    public void setFrom(LocalDateTime from) {
        this.fromDate = from;
    }

    public LocalDateTime getTo() {
        return toDate;
    }

    public void setTo(LocalDateTime to) {
        this.toDate = to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
