package cz.cvut.fel.rsp.travelandwork.service;
import cz.cvut.fel.rsp.travelandwork.dao.UserDao;
import cz.cvut.fel.rsp.travelandwork.environment.Generator;
import cz.cvut.fel.rsp.travelandwork.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTest {
    User testUser = Generator.generateUser();
    List<User> list = Arrays.asList(Generator.generateUser(), Generator.generateUser(), Generator.generateUser());

    @Autowired
    private EntityManager em;

    @Autowired
    private UserService sut;

    @Before
    public void init() {
        sut.persist(testUser);
        for (User us: list) {
            sut.persist(us);
            System.out.println(us.toString());
        }
    }

    @Test
    @Transactional
    @Rollback
    public void persist_PersistedUser() {
        User u = Generator.generateUser();
        sut.persist(u);
        Assert.assertEquals(u, sut.findByUsername(u.getUsername()));
    }
    @Test
    @Transactional
    @Rollback
    public void exists_ReturnsUserExistsById() {
        assertTrue(sut.exists(testUser.getId()));
    }
    @Test
    @Transactional
    @Rollback
    public void update_UserUpdated() {
        testUser.setFirstName("Tonicek");
        sut.update(testUser);
        assertEquals(testUser.getFirstName(), sut.find(testUser.getId()).getFirstName());
    }
    @Test
    @Transactional
    @Rollback
    public void find_FindsUserById() {
        Assert.assertEquals(testUser, sut.find(testUser.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void exists_ReturnsUserExistsByName() {
        assertTrue(sut.exists(testUser.getUsername()));
    }
    @Test
    @Transactional
    @Rollback
    public void find_ByUsernameFindsUserByItsName() {
        Assert.assertEquals(testUser, sut.findByUsername(testUser.getUsername()));
    }

    @Test
    @Transactional
    @Rollback
    public void findAll_alreadyExistingUsers_AllFindsAllUsersFromList() {
        assertNotNull(sut.findAll());
    }






    }
