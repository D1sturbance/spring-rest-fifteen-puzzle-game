package service;

import com.d1.PuzzleLauncher;
import com.d1.domain.TileSlide;
import com.d1.repository.UserRepository;
import com.d1.service.GameService;
import com.d1.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.Point;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PuzzleLauncher.class)
public class GameServiceTest {

    @Autowired
    GameService gameService;

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
    public void movePuzzleTileIsActuallyMovedUpTest() {
        Point pointBefore = getColumnAndRowOfZero(userRepository.findAll().get(0).getGame().getPuzzle());
        gameService.movePuzzleTile(TileSlide.UP, userRepository.findAll().get(0).getId());
        if (pointBefore.y != 0) {
            assertEquals(pointBefore.y - 1,
                    getColumnAndRowOfZero(userRepository.findAll().get(0).getGame().getPuzzle()).y);
        } else {
            assertEquals(pointBefore.y,
                    getColumnAndRowOfZero(userRepository.findAll().get(0).getGame().getPuzzle()).y);

        }

    }

    @Test
    public void movePuzzleTileIsActuallyMovedDownTest() {
        Point pointBefore = getColumnAndRowOfZero(userRepository.findAll().get(0).getGame().getPuzzle());
        gameService.movePuzzleTile(TileSlide.DOWN, userRepository.findAll().get(0).getId());
        if (pointBefore.y != 3) {
            assertEquals(pointBefore.y + 1,
                    getColumnAndRowOfZero(userRepository.findAll().get(0).getGame().getPuzzle()).y);
        } else {
            assertEquals(pointBefore.y,
                    getColumnAndRowOfZero(userRepository.findAll().get(0).getGame().getPuzzle()).y);

        }

    }

    private Point getColumnAndRowOfZero(int[][] puzzle) {
        Point point = new Point();
        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle[0].length; col++) {
                if (puzzle[row][col] == 0) {
                    point.setLocation(col, row);
                }
            }
        }
        return point;
    }
}
