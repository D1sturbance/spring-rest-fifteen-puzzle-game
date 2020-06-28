package service;

import com.d1.PuzzleLauncher;
import com.d1.repository.UserRepository;
import com.d1.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PuzzleLauncher.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
        userService.create("IronMan", "puzzle");
    }

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void createUserIsCreatedTest() {
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void createUserNameIsAsExpectedTest() {
        assertEquals("IronMan", userRepository.findAll().get(0).getName());
    }

    @Test
    public void createUserGameNameIsAsExpectedTest() {
        assertEquals("puzzle", userRepository.findAll().get(0).getGame().getName());
    }
}
