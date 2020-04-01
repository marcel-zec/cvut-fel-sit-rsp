package cz.cvut.fel.rsp.travelandwork.dto;

import cz.cvut.fel.rsp.travelandwork.model.Address;
import cz.cvut.fel.rsp.travelandwork.model.TravelJournal;
import cz.cvut.fel.rsp.travelandwork.model.TripReview;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UserDto  {

    @Size(max = 30, min = 1, message = "First name is in incorrect format.")
    @NotNull(message = "First name cannot be blank")
    private String firstName;

    @NotNull(message = "Last name cannot be blank")
    private String lastName;

    @Size(max = 255, min = 3, message = "Username is in incorrect format.")
    @NotNull(message = "Username cannot be blank")
    private String username;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be blank")
    private String email;

    private Address address;
    private TravelJournal travel_journal;
    private List<TripReview> tripReviews;


    public UserDto() {
    }

    public UserDto(String username, String firstName, String lastName, String email){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTravel_journal(TravelJournal travel_journal) {
        this.travel_journal = travel_journal;
    }

    public Address getAddress() {

        return address;
    }


    public TravelJournal getTravel_journal() {

        return travel_journal;
    }


    public List<TripReview> getTripReviews() {

        return tripReviews;
    }

    public void addReview(TripReview tripReview) {

        if (tripReviews == null) tripReviews = new ArrayList<TripReview>();
        tripReviews.add(tripReview);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", travel_journal=" + travel_journal +
                '}';
    }

    public void setTripReviews(List<TripReview> tripReviews) {
        this.tripReviews = tripReviews;
    }
}

