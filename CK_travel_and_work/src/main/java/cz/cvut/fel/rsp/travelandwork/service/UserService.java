package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.UserDao;
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
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserDao dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void persist(User user) {
        Objects.requireNonNull(user);
        user.encodePassword(passwordEncoder);
        dao.persist(user);
    }

    @Transactional(readOnly = true)
    public boolean exists(String login) {
        return dao.findByUsername(login) != null;
    }
    @Transactional
    public void remove(User user) {
        Objects.requireNonNull(user);
        dao.remove(user);
    }

    @Transactional
    public void update(User user) {
        Objects.requireNonNull(user);
        dao.update(user);
    }

    @Transactional
    public User find(Integer id) {
        Objects.requireNonNull(id);
        return dao.find(id);
    }
    @Transactional
    public User findByUsername(String login) {
        Objects.requireNonNull(login);
        return dao.findByUsername(login);
    }

    @Transactional
    public void findAll(List<User> user) {
        Objects.requireNonNull(user);
        dao.findAll();
    }

    @Transactional
    public void exists(Long id) {
        Objects.requireNonNull(id);
        dao.exists(id);
    }


}
