package cz.cvut.fel.rsp.travelandwork.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ACHIEVEMENT")
public class Achievement extends AbstractEntity{
    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(nullable = false)
    private String description;

    @Basic(optional = false)
    @Column(nullable = false)
    private int image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categrory_id", nullable = false)
    private Category category;

    @ManyToMany
    List<TravelJournal> ownedTravelJournals;
}
