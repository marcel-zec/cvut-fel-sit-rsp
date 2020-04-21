package cz.cvut.fel.rsp.travelandwork.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ACHIEVEMENT_CATEGORIZED")
public class AchievementCategorized extends Achievement {

    private Category category;
    private int limit;


}
