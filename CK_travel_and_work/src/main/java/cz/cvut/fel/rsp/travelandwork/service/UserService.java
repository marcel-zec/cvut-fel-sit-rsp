package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.AddressDao;
import cz.cvut.fel.rsp.travelandwork.dao.TravelJournalDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripReviewDao;
import cz.cvut.fel.rsp.travelandwork.dao.UserDao;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.TravelJournal;
import cz.cvut.fel.rsp.travelandwork.model.TripReview;
import cz.cvut.fel.rsp.travelandwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserDao dao;
    private final TripReviewDao tripReviewDao;
    private final TravelJournalDao travelJournalDao;
    private final PasswordEncoder passwordEncoder;
    private final AddressDao addressDao;


    @Autowired
    public UserService(UserDao dao, PasswordEncoder passwordEncoder, TripReviewDao tripReviewDao, TravelJournalDao travelJournalDao, AddressDao addressDao) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
        this.tripReviewDao = tripReviewDao;
        this.travelJournalDao = travelJournalDao;
        this.addressDao = addressDao;
    }

    @Transactional
    public void create(User user) {
        Objects.requireNonNull(user);
        user.encodePassword(passwordEncoder);
        dao.persist(user);
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
    public User find(Long id) {
        Objects.requireNonNull(id);
        return dao.find(id);
    }
    @Transactional
    public User findByUsername(String login) {
        Objects.requireNonNull(login);
        return dao.findByUsername(login);
    }

    @Transactional
    public List<User> findAll() {
        return dao.findAll();
    }

    @Transactional
    public boolean exists(Long id) {
        Objects.requireNonNull(id);
        return dao.exists(id);
    }


}
