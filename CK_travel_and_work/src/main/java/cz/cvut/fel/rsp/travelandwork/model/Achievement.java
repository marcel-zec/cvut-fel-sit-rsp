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
    private List<TravelJournal> ownedTravelJournals;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public Category getCategory() {
        return category;
    }

    public List<TravelJournal> getOwnedTravelJournals() {
        return ownedTravelJournals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setOwnedTravelJournals(List<TravelJournal> ownedTravelJournals) {
        this.ownedTravelJournals = ownedTravelJournals;
    }
}
