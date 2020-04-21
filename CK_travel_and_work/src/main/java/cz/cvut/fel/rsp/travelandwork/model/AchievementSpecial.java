package cz.cvut.fel.rsp.travelandwork.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ACHIEVEMENT_SPECIAL")
public class AchievementSpecial extends Achievement {
    public AchievementSpecial() {
    }

    public AchievementSpecial(String name, String description, String icon) {
        super(name, description, icon);
    }
}
