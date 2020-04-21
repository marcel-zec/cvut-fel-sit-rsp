package cz.cvut.fel.rsp.travelandwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "ACHIEVEMENT_CERTIFICATE")
public class AchievementCertificate extends Achievement{
    public AchievementCertificate() {
    }

    public AchievementCertificate(String name, String description, String icon) {
        super(name, description, icon);
    }
}
