package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.*;
import cz.cvut.fel.rsp.travelandwork.dto.TripDto;
import cz.cvut.fel.rsp.travelandwork.dto.TripSessionDto;
import cz.cvut.fel.rsp.travelandwork.exception.BadDateException;
import cz.cvut.fel.rsp.travelandwork.exception.MissingVariableException;
import cz.cvut.fel.rsp.travelandwork.exception.NotAllowedException;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.*;
import cz.cvut.fel.rsp.travelandwork.service.security.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TripService {

    private final TripDao tripDao;
    private final TripSessionDao tripSessionDao;
    private final TripReviewDao tripReviewDao;
    private final TranslateService translateService;
    private final AccessService accessService;
    private final UserDao userDao;
    private final EnrollmentDao enrollmentDao;
    private final TravelJournalDao travelJournalDao;

    @Autowired
    public TripService(TripDao tripDao, TripSessionDao tripSessionDao, TripReviewDao tripReviewDao, TranslateService translateService, AccessService accessService, UserDao userDao, EnrollmentDao enrollmentDao, TravelJournalDao travelJournalDao) {
        this.tripDao = tripDao;
        this.tripSessionDao = tripSessionDao;
        this.tripReviewDao = tripReviewDao;
        this.translateService = translateService;
        this.accessService = accessService;
        this.userDao = userDao;
        this.enrollmentDao = enrollmentDao;
        this.travelJournalDao = travelJournalDao;
    }

    @Transactional
    public List<Trip> findAll() {
        return tripDao.findAll();
    }

    @Transactional
    public List<TripDto> findAllDto() {
        List<TripDto> tripDtos = new ArrayList<>();

        for (Trip trip:tripDao.findAll()) {
            tripDtos.add(translateService.translateTrip(trip));
        }
        return tripDtos;
    }

    @Transactional
    public TripDto find(Long id) {
        return translateService.translateTrip(tripDao.find(id));
    }

    @Transactional
    public TripDto findByString(String stringId) {
        return translateService.translateTrip(tripDao.find(stringId));
    }

    @Transactional
    public void create(Trip trip) throws BadDateException, MissingVariableException {

        Objects.requireNonNull(trip);
        if (trip.getSessions().size()<=0) throw new MissingVariableException();
        tripDao.persist(trip);
        for (TripSession session: trip.getSessions()) {
            if (session.getTo_date().isBefore(session.getFrom_date())) {
                tripDao.remove(trip);
                throw new BadDateException();
            }
            session.setTrip(trip);
            tripSessionDao.persist(session);
        }
       tripDao.update(trip);
    }

    @Transactional
    public void signUpToTrip(TripSessionDto tripSessionDto, User current_user) throws NotAllowedException {
        TripSession tripSession = tripSessionDao.find(tripSessionDto.getId());
//      TODO odkomentovat ked bude otestovane ukoncovanie tripov
//       if (tripSession.getFrom_date().isBefore(ChronoLocalDate.from(LocalDateTime.now()))) throw new NotAllowedException();
        User user = userDao.find(current_user.getId());
        Enrollment enrollment = new Enrollment();

        enrollment.setDeposit_was_paid(false);
        enrollment.setEnrollDate(LocalDateTime.now());
        enrollment.setActual_xp_reward(0);
        enrollment.setTrip(tripSession.getTrip());
        enrollment.setState(EnrollmentState.ACTIVE);
        enrollment.setTripSession(tripSession);
        enrollment.setTravelJournal(user.getTravel_journal());

        System.out.println(enrollment.toString());

        enrollmentDao.persist(enrollment);
        user.getTravel_journal().addEnrollment(enrollment);
        travelJournalDao.update(user.getTravel_journal());
    }

    @Transactional
    public List<Trip> findAfford(User current_user) throws NotAllowedException {
        if (current_user == null) throw new NotAllowedException();
        User user = accessService.getUser(current_user);
        int level = translateService.countLevel(translateService.translateTravelJournal(user.getTravel_journal()).getXp_count());
        return  tripDao.find(level);
    }

    @Transactional
    public List<Trip> findNotAfford(User current_user) throws NotAllowedException {
        List<Trip> trips = tripDao.findAll();
        trips.removeAll(findAfford(current_user));
        return trips;
    }

    @Transactional
    public void update(String stringId, Trip newTrip) throws BadDateException, NotFoundException, MissingVariableException {
        Trip trip = tripDao.find(stringId);

        if (trip == null) throw new NotFoundException();
        //todo pridat vynimku na rolu

        newTrip.setId(trip.getId());

        newTrip.setReviews(trip.getReviews());
        if (newTrip.getSessions().size()<=0) throw new MissingVariableException();

        //less new sessions
        if (newTrip.getSessions().size() < trip.getSessions().size()){
            for ( int i = newTrip.getSessions().size() ; i < trip.getSessions().size(); i++) {
                tripSessionDao.remove(trip.getSessions().get(i));
            }
        }

        for (int i = 0; i < newTrip.getSessions().size() ; i++) {
            TripSession newSession = newTrip.getSessions().get(i);
            if (newSession.getTo_date().isBefore(newSession.getFrom_date())) throw new BadDateException();

                if (i <= trip.getSessions().size()-1 ){
                TripSession oldSession = trip.getSessions().get(i);

                newTrip.getSessions().get(i).setId(oldSession.getId());
                oldSession = newSession;
                oldSession.setTrip(trip);
                tripSessionDao.update(oldSession);
            } else {
                    newSession.setTrip(trip);
                    tripSessionDao.persist(newSession);
                }
        }

        trip=newTrip;
        tripDao.update(trip);
    }

    @Transactional
    public void delete(String stringId) throws NotFoundException {

        Trip trip = tripDao.find(stringId);
        if (trip == null) throw new NotFoundException();

        for (TripSession session :trip.getSessions()) {
            session.softDelete();
            tripSessionDao.update(session);
        }

        for (TripReview review: trip.getReviews()) {
            review.softDelete();
            tripReviewDao.update(review);
        }

        trip.softDelete();
        tripDao.update(trip);
    }


    public List<Trip> getAllTripsByFilter(String location, String from_date, String to_date, double maxPrice) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate local_to_date = LocalDate.parse(to_date, formatter);
        LocalDate local_from_date = LocalDate.parse(from_date, formatter);

        return tripDao.findByFilter(location,  local_from_date, local_to_date, maxPrice);
    }
}
