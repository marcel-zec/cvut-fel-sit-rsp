package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.AddressDao;
import cz.cvut.fel.rsp.travelandwork.dao.TravelJournalDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripReviewDao;
import cz.cvut.fel.rsp.travelandwork.dao.UserDao;
import cz.cvut.fel.rsp.travelandwork.dto.UserDto;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.Enrollment;
import cz.cvut.fel.rsp.travelandwork.model.TravelJournal;
import cz.cvut.fel.rsp.travelandwork.model.TripReview;
import cz.cvut.fel.rsp.travelandwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserDao dao;
    private final TripReviewDao tripReviewDao;
    private final TravelJournalDao travelJournalDao;
    private final AddressDao addressDao;


    @Autowired
    public UserService(UserDao dao, TripReviewDao tripReviewDao, TravelJournalDao travelJournalDao, AddressDao addressDao) {
        this.dao = dao;
        this.tripReviewDao = tripReviewDao;
        this.travelJournalDao = travelJournalDao;
        this.addressDao = addressDao;
    }

    @Transactional
    public void create(User user) {
        Objects.requireNonNull(user);
        user.encodePassword();
        dao.persist(user);
        user = dao.findByUsername(user.getUsername());
        if (user.getAddress() != null){
            user.getAddress().setUser(user);
            addressDao.persist(user.getAddress());
        }
        if (user.getTravel_journal() != null) {
            user.getTravel_journal().setUser(user);
            travelJournalDao.persist(user.getTravel_journal());
        }
        dao.update(user);
    }

    @Transactional(readOnly = true)
    public boolean exists(String login) {
        return dao.findByUsername(login) != null;
    }

    @Transactional
    public void delete(String username) throws NotFoundException {

        User user = dao.findByUsername(username);
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
    public void update(User user) {
        Objects.requireNonNull(user);
        dao.update(user);
    }

    @Transactional
    public UserDto find(Long id) {
        Objects.requireNonNull(id);
        return translate(dao.find(id));
    }
    @Transactional
    public UserDto findByUsername(String username) {
        Objects.requireNonNull(username);
        return translate(dao.findByUsername(username));
    }

    @Transactional
    public List<UserDto> findAll() {

        List<UserDto> userDtos = new ArrayList<>();
        for (User user:dao.findAll()) {
            userDtos.add(translate(user));
        }
        return userDtos;
    }

    @Transactional
    public boolean exists(Long id) {
        Objects.requireNonNull(id);
        return dao.exists(id);
    }

    private UserDto translate(User user) {
        Objects.requireNonNull(user);
        UserDto userDto = new UserDto();

        user.setUsername(user.getUsername());
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setTravel_journal(user.getTravel_journal());
        userDto.setTripReviews(user.getTripReviews());

        return userDto;
    }

}
