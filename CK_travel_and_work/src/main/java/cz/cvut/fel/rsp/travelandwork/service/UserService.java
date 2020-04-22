package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.AddressDao;
import cz.cvut.fel.rsp.travelandwork.dao.TravelJournalDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripReviewDao;
import cz.cvut.fel.rsp.travelandwork.dao.UserDao;
import cz.cvut.fel.rsp.travelandwork.dto.UserDto;
import cz.cvut.fel.rsp.travelandwork.exception.BadPassword;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.exception.UnauthorizedException;
import cz.cvut.fel.rsp.travelandwork.model.*;
import cz.cvut.fel.rsp.travelandwork.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserDao dao;
    private final TripReviewDao tripReviewDao;
    private final TravelJournalDao travelJournalDao;
    private final AddressDao addressDao;
    private final TranslateService translateService;


    @Autowired
    public UserService(UserDao dao, TripReviewDao tripReviewDao, TravelJournalDao travelJournalDao, AddressDao addressDao, TranslateService translateService) {
        this.dao = dao;
        this.tripReviewDao = tripReviewDao;
        this.travelJournalDao = travelJournalDao;
        this.addressDao = addressDao;
        this.translateService = translateService;
    }

    @Transactional
    public void create(User user, String passwordAgain) throws BadPassword {
        Objects.requireNonNull(user);
        if (!user.getPassword().equals(passwordAgain)) throw new BadPassword();
        user.encodePassword();
        dao.persist(user);
        if (user.getAddress() != null){
            user.getAddress().setUser(user);
            addressDao.persist(user.getAddress());
        }
        if (user.getTravel_journal() == null) {
            TravelJournal tj = new TravelJournal(user);
            user.setTravel_journal(tj);
            travelJournalDao.persist(tj);
        }
        dao.update(user);
    }

    @Transactional(readOnly = true)
    public boolean exists(String login) {
        return dao.findByEmail(login) != null;
    }

    @Transactional
    public UserDto showCurrentUser() throws UnauthorizedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new UnauthorizedException();
            return translateService.translateUser(dao.find(SecurityUtils.getCurrentUser().getId()));
    }

    @Transactional
    public void delete(Long id) throws NotFoundException {

        User user = dao.find(id);
        if (user == null) throw new NotFoundException();

        user.getAddress().softDelete();
        addressDao.update(user.getAddress());

        for (Enrollment e :user.getTravel_journal().getEnrollments()) {
            e.softDelete();
        }
        user.getTravel_journal().softDelete();
        travelJournalDao.update(user.getTravel_journal());

        for (TripReview tr: user.getTripReviews()) {
            tr.softDelete();
            tripReviewDao.update(tr);
        }

        user.softDelete();
        dao.update(user);
        //TODO osetrenie na prihlaseneho usera {admin}
    }

    @Transactional
    public void update(User newUser, User current_user) throws NotFoundException {
        Objects.requireNonNull(newUser);
        current_user = dao.find(current_user.getId());
        User user = dao.findByEmail(newUser.getEmail());

        if (user == null) throw new NotFoundException();
        if (current_user.getRole() == Role.USER ) user = current_user;

        newUser.setId(user.getId());
        newUser.setTripReviews(user.getTripReviews());
        if (newUser.getAddress() != null ) {
            Address oldAddress = user.getAddress();
            newUser.getAddress().setId(oldAddress.getId());
            oldAddress = newUser.getAddress();
            addressDao.update(oldAddress);
        }
        if (newUser.getTravel_journal() != null){
            TravelJournal oldTravelJournal = user.getTravel_journal();
            newUser.getTravel_journal().setId(oldTravelJournal.getId());
            oldTravelJournal = newUser.getTravel_journal();
            travelJournalDao.update(oldTravelJournal);
        }

        user=newUser;
        dao.update(user);
    }

    @Transactional
    public UserDto find(Long id) {
        Objects.requireNonNull(id);
        return translateService.translateUser(dao.find(id));
    }
    @Transactional
    public UserDto findByEmail(String email) {
        Objects.requireNonNull(email);
        return translateService.translateUser(dao.findByEmail(email));
    }

    @Transactional
    public List<UserDto> findAll() {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user:dao.findAll()) {
            userDtos.add(translateService.translateUser(user));
        }
        return userDtos;
    }

    @Transactional
    public boolean exists(Long id) {
        Objects.requireNonNull(id);
        return dao.exists(id);
    }


}
