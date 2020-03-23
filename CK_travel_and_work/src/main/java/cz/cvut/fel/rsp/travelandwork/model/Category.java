package cz.cvut.fel.rsp.travelandwork.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CATEGORY")
public class Category extends AbstractEntity{

    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    @NotBlank(message = "Name of category cannot be blank")
    private String name;
}
