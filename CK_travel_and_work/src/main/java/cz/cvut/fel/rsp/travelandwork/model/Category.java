package cz.cvut.fel.rsp.travelandwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category extends AbstractEntity{

    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    @NotBlank(message = "Name of category cannot be blank")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(@NotBlank(message = "Name of category cannot be blank") String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category")
    private List<Trip> trips;

    public boolean add(Trip trip){
        return trips.add(trip);
    }
}

