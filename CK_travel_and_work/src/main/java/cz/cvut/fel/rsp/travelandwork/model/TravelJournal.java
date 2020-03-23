package cz.cvut.fel.rsp.travelandwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "TRAVELJOURNAL")
public class TravelJournal extends AbstractEntity{
    @Basic(optional = false)
    @Column(nullable = false)
    private int xp_count;

    @Basic(optional = false)
    @Column(nullable = false)
    private HashMap<Category, Integer> trip_counter;

    @OneToOne(mappedBy = "travel_journal")
    private User user;

    @ManyToMany
    List<Achievement> earnedAchievements;

}
