package factory;

import com.d1.PuzzleLauncher;
import com.d1.domain.User;
import com.d1.factory.UserFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PuzzleLauncher.class)
public class UserFactoryTest {

    @Autowired
    UserFactory userFactory;

    User user;

    @Before
    public void setUp() {
        user = userFactory.create("Hulk", "puzzleGame");
    }

    @Test
    public void createUserIsNotNullTest() {
        assertNotNull(user);
    }

    @Test
    public void createUserNameIsAsExpectedTest() {
        assertEquals("Hulk", user.getName());
    }

    @Test
    public void createUserGameIsNotNull() {
        assertNotNull(user.getGame());
    }
}
