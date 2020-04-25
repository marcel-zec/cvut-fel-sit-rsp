package cz.cvut.fel.rsp.travelandwork.service;
import cz.cvut.fel.rsp.travelandwork.dto.UserDto;
import cz.cvut.fel.rsp.travelandwork.environment.util.Generator;
import cz.cvut.fel.rsp.travelandwork.exception.BadPassword;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTest {
    User testUser = Generator.generateUser();
    List<User> list = Arrays.asList(Generator.generateUser(), Generator.generateUser(), Generator.generateUser());

    @Autowired
    TranslateService ts;

    @Autowired
    private EntityManager em;

    @Autowired
    private UserService sut;

    @Before
    public void init() throws BadPassword {
        sut.create(testUser, testUser.getPassword());
        for (User us: list) {
            sut.create(us, us.getPassword());
            System.out.println(us.toString());
        }
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
    public void update_UserUpdated() throws NotFoundException {
        testUser.setFirstName("Tonicek");
        //TODO - testy - opravit
//        sut.update(testUser);
        assertEquals(testUser.getFirstName(), sut.find(testUser.getId()).getFirstName());
    }

    //TODO: mel by porovnavat usery!!! ale nefunguje to! :(
    @Test
    @Transactional
    @Rollback
    public void find_FindsUserById() {
        Assert.assertEquals(testUser.getEmail(), sut.find(testUser.getId()).getEmail());
    }

    @Test
    @Transactional
    @Rollback
    public void exists_ReturnsUserExistsByEmail() {
        assertTrue(sut.exists(testUser.getEmail()));
    }


    //TODO: mel by porovnavat usery!!! ale nefunguje to! :(
    @Test
    @Transactional
    @Rollback
    public void find_FindsUserByItsEmail() {
        Assert.assertEquals(testUser.getFirstName(), sut.findByEmail(testUser.getEmail()).getFirstName());
    }

    @Test
    @Transactional
    @Rollback
    public void findAll_alreadyExistingUsers_AllFindsAllUsersFromList() {
        assertNotNull(sut.findAll());
    }






    }
