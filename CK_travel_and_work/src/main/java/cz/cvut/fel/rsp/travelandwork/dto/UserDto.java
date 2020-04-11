package cz.cvut.fel.rsp.travelandwork.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDto  {

    @NotNull(message = "Id cannot be blank")
    private Long id;

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

    private AddressDto address;
    private TravelJournalDto travel_journal;
    private List<TripReviewDto> tripReviews;


    public UserDto() {
    }


    public UserDto(@NotNull(message = "Id cannot be blank") Long id, @Size(max = 30, min = 1, message = "First name is in incorrect format.") @NotNull(message = "First name cannot be blank") String firstName, @NotNull(message = "Last name cannot be blank") String lastName, @Size(max = 255, min = 3, message = "Username is in incorrect format.") @NotNull(message = "Username cannot be blank") String username, @Email(message = "Email should be valid") @NotNull(message = "Email cannot be blank") String email,
                   AddressDto address, TravelJournalDto travel_journal, List<TripReviewDto> tripReviews) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.address = address;
        this.travel_journal = travel_journal;
        this.tripReviews = tripReviews;
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


    public AddressDto getAddress() {

        return address;
    }


    public void setAddress(AddressDto address) {

        this.address = address;
    }


    public TravelJournalDto getTravel_journal() {

        return travel_journal;
    }


    public void setTravel_journal(TravelJournalDto travel_journal) {

        this.travel_journal = travel_journal;
    }


    public List<TripReviewDto> getTripReviewsDto() {

        return tripReviews;
    }


    public void setTripReviewsDto(List<TripReviewDto> tripReviews) {

        this.tripReviews = tripReviews;
    }


    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public List<TripReviewDto> getTripReviews() {

        return tripReviews;
    }


    public void setTripReviews(List<TripReviewDto> tripReviews) {

        this.tripReviews = tripReviews;
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
}

