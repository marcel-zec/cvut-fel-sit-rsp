package cz.cvut.fel.rsp.travelandwork.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ADDRESS")
public class Address extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank(message = "City cannot be blank")
    private String city;

    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank(message = "Street cannot be blank")
    private String street;

    @Basic(optional = false)
    @Column(nullable = false)
    private int houseNumber;

    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank(message = "ZIP code cannot be blank")
    private String zipCode;

    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank(message = "Country cannot be blank")
    private String country;

    @OneToOne(mappedBy = "address")
    private User user;

}
