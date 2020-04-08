package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dto.AddressDto;
import cz.cvut.fel.rsp.travelandwork.dto.TripDto;
import cz.cvut.fel.rsp.travelandwork.dto.UserDto;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TranslateService {

    @Transactional
    public UserDto translateUser(User user) {
        Objects.requireNonNull(user);
        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        userDto.setEmail(user.getEmail());
        userDto.setTravel_journal(user.getTravel_journal());
        userDto.setTripReviews(user.getTripReviews());
        userDto.setAddress(user.getAddress());

        return userDto;
    }


//    @Transactional
//    public AddressDto translateAddress(Address address, UserDto userdto) {
//        Objects.requireNonNull(address);
//        Objects.requireNonNull(userdto);
//        return new AddressDto(address.getCity(),address.getStreet(),address.getHouseNumber(),address.getZipCode(),
//                address.getCountry(),userdto);
//    }

    @Transactional
    public TripDto translateTrip(Trip trip) {
        Objects.requireNonNull(trip);
        return new TripDto(trip.getName(),trip.getShort_name(),trip.getPossible_xp_reward(),
                trip.getDescription(),trip.getRating(),trip.getDeposit(),trip.getLocation(), trip.getRequired_level(),
                trip.getCategory(),trip.getRequired_achievements(),trip.getGain_achievements());
    }
}
