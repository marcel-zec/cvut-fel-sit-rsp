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
        System.out.println(user.toString());
        Objects.requireNonNull(user);
        List<TripReviewDto> tripReviewDtos = new ArrayList<>();
        List<TripReview> tripReviews = user.getTripReviews();
        List<UserReviewDto> userReviewDtos = new ArrayList<>();
        List<UserReview> userReviews =  user.getUserReviews();

        if (tripReviews.size() > 0){
            tripReviews.forEach(review-> tripReviewDtos.add(translateTripReview(review)));
        }

        if (userReviews.size() > 0){
            userReviews.forEach(review-> userReviewDtos.add(translateUserReview(review)));
        }

        if (user.getTravel_journal() != null) {
            TravelJournalDto travelJournalDto = translateTravelJournal(user.getTravel_journal());
            return new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),
                    translateAddress(user.getAddress()),travelJournalDto,tripReviewDtos, user.getRole(),userReviewDtos);
        }

       return new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),
                translateAddress(user.getAddress()),null,tripReviewDtos, user.getRole(),userReviewDtos);
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
        List<AchievementCertificateDto> required_certificates = new ArrayList<>();
        List<AchievementCategorizedDto> required_achievements_categorized = new ArrayList<>();
        List<AchievementSpecialDto> required_achievements_special = new ArrayList<>();
        List<AchievementSpecialDto> gain_achievements = new ArrayList<>();

        trip.getRequired_certificates().forEach(achievementCertificate -> required_certificates.add(translateAchievementCertificate(achievementCertificate)));
        trip.getRequired_achievements_categorized().forEach(achievementCategorized -> required_achievements_categorized.add(translateAchievementCategorized(achievementCategorized)));
        trip.getRequired_achievements_special().forEach(achievementSpecial -> required_achievements_special.add(translateAchievementSpecial(achievementSpecial)));
        trip.getGain_achievements_special().forEach(achievementSpecial -> gain_achievements.add(translateAchievementSpecial(achievementSpecial)));
        trip.getSessions().forEach(session-> sessions.add(session.getId()));


        return new TripDto(trip.getId(),trip.getName(),trip.getShort_name(),trip.getPossible_xp_reward(),
                trip.getDescription(),trip.getRating(),trip.getDeposit(),trip.getLocation(), trip.getRequired_level(),
                trip.getCategory().getId(), required_certificates, required_achievements_categorized, required_achievements_special, gain_achievements,sessions);
    }

    @Transactional
    public TripSessionDto translateSession(TripSession tripSession) {
        Objects.requireNonNull(tripSession);
        TripDto tripDto = translateTrip(tripSession.getTrip());
        return new TripSessionDto(tripSession.getId(),tripSession.getFrom_date(),tripSession.getTo_date(),tripSession.getPrice(),tripDto);
    }

    @Transactional
    public AchievementCertificateDto translateAchievementCertificate(AchievementCertificate achievementCertificate){
        Objects.requireNonNull(achievementCertificate);
        List<Long> trips = new ArrayList<>();
        List<Long> owned_travel_journals = new ArrayList<>();

        achievementCertificate.getTrips().forEach(trip -> trips.add(trip.getId()));
        achievementCertificate.getOwned_travel_journals().forEach(travelJournal -> owned_travel_journals.add(travelJournal.getId()));

        return new AchievementCertificateDto(achievementCertificate.getId(),achievementCertificate.getName(),achievementCertificate.getDescription(),achievementCertificate.getIcon(),
                trips,owned_travel_journals);
    }

    @Transactional
    public AchievementSpecialDto translateAchievementSpecial(AchievementSpecial achievementSpecial){
        Objects.requireNonNull(achievementSpecial);
        List<Long> trips = new ArrayList<>();
        List<Long> owned_travel_journals = new ArrayList<>();

        achievementSpecial.getTrips().forEach(trip -> trips.add(trip.getId()));
        achievementSpecial.getOwned_travel_journals().forEach(travelJournal -> owned_travel_journals.add(travelJournal.getId()));

        return new AchievementSpecialDto(achievementSpecial.getId(),achievementSpecial.getName(),achievementSpecial.getDescription(),achievementSpecial.getIcon(),
                trips,owned_travel_journals);
    }

    @Transactional
    public AchievementCategorizedDto translateAchievementCategorized(AchievementCategorized achievementCategorized){
        Objects.requireNonNull(achievementCategorized);
        List<Long> trips = new ArrayList<>();
        List<Long> owned_travel_journals = new ArrayList<>();

        achievementCategorized.getTrips().forEach(trip -> trips.add(trip.getId()));
        achievementCategorized.getOwned_travel_journals().forEach(travelJournal -> owned_travel_journals.add(travelJournal.getId()));

        return new AchievementCategorizedDto(achievementCategorized.getId(),achievementCategorized.getName(),achievementCategorized.getDescription(),achievementCategorized.getIcon(),
                trips,owned_travel_journals, achievementCategorized.getLimit(), achievementCategorized.getCategory().getId());
    }

    @Transactional
    public TravelJournalDto translateTravelJournal(TravelJournal travelJournal){
        Objects.requireNonNull(travelJournal);
        List<EnrollmentDto> enrollmentDtos = new ArrayList<>();
        List<AchievementCertificateDto> certificateDtos = new ArrayList<>();
        List<AchievementCategorizedDto> categorizedDtos = new ArrayList<>();
        List<AchievementSpecialDto> specialDtos = new ArrayList<>();
        HashMap<CategoryDto, Integer> trip_counter= new HashMap<CategoryDto, Integer>();

        for (Category category : travelJournal.getTrip_counter().keySet()) {
            CategoryDto categoryDto= translateCategory(category);
            trip_counter.put(categoryDto,travelJournal.getTrip_counter().get(category));
        }


        travelJournal.getEnrollments().forEach(enrollment -> enrollmentDtos.add(translateEnrollment(enrollment)));
        travelJournal.getCertificates().forEach(certificate -> certificateDtos.add(translateAchievementCertificate(certificate)));
        travelJournal.getEarnedAchievementsCategorized().forEach(categorized -> categorizedDtos.add(translateAchievementCategorized(categorized)));
        travelJournal.getEarnedAchievementsSpecial().forEach(special -> specialDtos.add(translateAchievementSpecial(special)));

        return new TravelJournalDto(travelJournal.getId(), travelJournal.getXp_count(), trip_counter,travelJournal.getUser().getId(), certificateDtos, categorizedDtos, specialDtos,enrollmentDtos);
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
        List<AchievementSpecialDto> recieved_achievements_special = new ArrayList<>();

        enrollment.getRecieved_achievements().forEach(achievement_special -> recieved_achievements_special.add(translateAchievementSpecial(achievement_special)));

        return new EnrollmentDto(enrollment.getId(),enrollment.getEnrollDate(),enrollment.isDeposit_was_paid(),enrollment.getActual_xp_reward(),enrollment.getState(),
                recieved_achievements_special,enrollment.getTravelJournal().getId(),translateTrip(enrollment.getTrip()),translateSession(enrollment.getTripSession()));
    }

    @Transactional
    public TripReviewDto translateTripReview(TripReview tripReview){
        Objects.requireNonNull(tripReview);

        return new TripReviewDto(tripReview.getId(),tripReview.getNote(),tripReview.getDate(),
                tripReview.getRating(),tripReview.getAuthor().getId(),tripReview.getTrip().getId());
    }

    @Transactional
    public UserReviewDto translateUserReview(UserReview userReview){
        Objects.requireNonNull(userReview);

        return new UserReviewDto(userReview.getId(),userReview.getNote(),userReview.getDate(),
                userReview.getRating(),userReview.getUser().getId(),userReview.getAuthor().getId(),translateSession(userReview.getTripSession()));
    }
}
