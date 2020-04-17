package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dto.*;
import cz.cvut.fel.rsp.travelandwork.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class TranslateService {

    @Transactional
    public UserDto translateUser(User user) {
        Objects.requireNonNull(user);
        List<TripReviewDto> tripReviewDtos = new ArrayList<>();
        user.getTripReviews().forEach(review-> tripReviewDtos.add(translateTripReview(review)));

        if (user.getTravel_journal() != null) {
            TravelJournalDto travelJournalDto = translateTravelJournal(user.getTravel_journal());
            return new UserDto(user.getId(),user.getFirstName(),user.getLastName(),/*user.getUsername(),*/user.getEmail(),
                    translateAddress(user.getAddress()),travelJournalDto,tripReviewDtos);
        }

       return new UserDto(user.getId(),user.getFirstName(),user.getLastName(),/*user.getUsername(),*/user.getEmail(),
                translateAddress(user.getAddress()),null,tripReviewDtos);
    }


    @Transactional
    public AddressDto translateAddress(Address address) {
        Objects.requireNonNull(address);

        return new AddressDto(address.getId(),address.getCity(),address.getStreet(),address.getHouseNumber(),address.getZipCode(),
                address.getCountry(),address.getUser().getId());
    }

    @Transactional
    public TripDto translateTrip(Trip trip) {
        Objects.requireNonNull(trip);
        List<Long> sessions = new ArrayList<>();
        List<AchievementDto> required_achievements = new ArrayList<>();
        List<AchievementDto> gain_achievements = new ArrayList<>();

        trip.getRequired_achievements().forEach(achievement -> required_achievements.add(translateAchievement(achievement)));
        trip.getGain_achievements().forEach(achievement -> gain_achievements.add(translateAchievement(achievement)));
        trip.getSessions().forEach(session-> sessions.add(session.getId()));


        return new TripDto(trip.getId(),trip.getName(),trip.getShort_name(),trip.getPossible_xp_reward(),
                trip.getDescription(),trip.getRating(),trip.getDeposit(),trip.getLocation(), trip.getRequired_level(),
                trip.getCategory().getId(),required_achievements,gain_achievements,sessions);
    }

    @Transactional
    public TripSessionDto translateSession(TripSession tripSession) {
        Objects.requireNonNull(tripSession);
        TripDto tripDto = translateTrip(tripSession.getTrip());
        return new TripSessionDto(tripSession.getId(),tripSession.getFrom_date(),tripSession.getTo_date(),tripSession.getPrice(),tripDto);
    }

    @Transactional
    public AchievementDto translateAchievement(Achievement achievement){
        Objects.requireNonNull(achievement);
        List<Long> trips = new ArrayList<>();
        List<Long> owned_travel_journals = new ArrayList<>();

        achievement.getTrips().forEach(trip -> trips.add(trip.getId()));
        achievement.getOwned_travel_journals().forEach(travelJournal -> owned_travel_journals.add(travelJournal.getId()));

        return new AchievementDto(achievement.getId(),achievement.getName(),achievement.getDescription(),achievement.getIcon(),
                trips,owned_travel_journals);
    }

    @Transactional
    public TravelJournalDto translateTravelJournal(TravelJournal travelJournal){
        Objects.requireNonNull(travelJournal);
        List<EnrollmentDto> enrollmentDtos = new ArrayList<>();
        List<AchievementDto> achievementDtos = new ArrayList<>();
        HashMap<CategoryDto, Integer> trip_counter= new HashMap<CategoryDto, Integer>();

        for (Category category : travelJournal.getTrip_counter().keySet()) {
            CategoryDto categoryDto= translateCategory(category);
            trip_counter.put(categoryDto,travelJournal.getTrip_counter().get(category));
        }


        travelJournal.getEnrollments().forEach(enrollment -> enrollmentDtos.add(translateEnrollment(enrollment)));
        travelJournal.getEarnedAchievements().forEach(achievement -> achievementDtos.add(translateAchievement(achievement)));

        return new TravelJournalDto(travelJournal.getId(), travelJournal.getXp_count(), trip_counter,travelJournal.getUser().getId(),achievementDtos,enrollmentDtos);
    }

    @Transactional
    public CategoryDto translateCategory(Category category){
        Objects.requireNonNull(category);
        List<TripDto> trips = new ArrayList<>();

        for (Trip trip : category.getTrips()) {
            trips.add(translateTrip(trip));
        }
        return new CategoryDto(category.getId(),category.getName(),trips);
    }

    @Transactional
    public EnrollmentDto translateEnrollment(Enrollment enrollment){
        Objects.requireNonNull(enrollment);
        List<AchievementDto> recieved_achievements = new ArrayList<>();

        enrollment.getRecieved_achievements().forEach(achievement -> recieved_achievements.add(translateAchievement(achievement)));

        return new EnrollmentDto(enrollment.getId(),enrollment.getEnrollDate(),enrollment.isDeposit_was_paid(),enrollment.getActual_xp_reward(),enrollment.getState(),
                recieved_achievements,enrollment.getTravelJournal().getId(),translateTrip(enrollment.getTrip()),translateSession(enrollment.getTripSession()));
    }

    @Transactional
    public TripReviewDto translateTripReview(TripReview tripReview){
        Objects.requireNonNull(tripReview);

        return new TripReviewDto(tripReview.getId(),tripReview.getNote(),tripReview.getDate(),
                tripReview.getRating(),tripReview.getAuthor().getId(),tripReview.getTrip().getId());
    }
}
