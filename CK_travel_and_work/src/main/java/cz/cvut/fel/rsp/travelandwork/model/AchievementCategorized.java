package cz.cvut.fel.rsp.travelandwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="ACHIEVEMENT_CATEGORIZED")
public class AchievementCategorized extends Achievement {

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Basic(optional = false)
    @Column(nullable = false)
    private int limit;

    public AchievementCategorized() {
    }

    public AchievementCategorized(String name, String description, String icon) {
        super(name, description, icon);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
