package factory;


import com.d1.PuzzleLauncher;
import com.d1.domain.Game;
import com.d1.factory.GameFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PuzzleLauncher.class)
public class GameFactoryTest {

    @Autowired
    GameFactory gameFactory;

    Game game;

    @Before
    public void setUp() {
        game = gameFactory.create("puzzleGame");
    }

    @Test
    public void createGameIsNotNullTest() {
        assertNotNull(game);
    }

    @Test
    public void createGameNameIsAsExpectedTest() {
        assertEquals("puzzleGame", game.getName());
    }

    @Test
    public void createGamePuzzleIsSolvedFalseTest() {
        assertFalse(game.isSolved());
    }

    @Test
    public void createGamePuzzleIsNotNullTest() {
        assertNotNull(game.getPuzzle());
    }

}
