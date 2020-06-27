package controller;

import com.d1.PuzzleLauncher;
import com.d1.controller.GameController;
import com.d1.domain.User;
import com.d1.factory.UserFactory;
import com.d1.repository.UserRepository;
import com.d1.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PuzzleLauncher.class)
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GameController gameController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFactory userFactory;

    @Autowired
    GameService gameService;

    @Before
    public void setUp() {
        userRepository.save(userFactory.create("Thor", "fifteenPuzzle"));
    }

    @Test
    public void whenValidInput_MakeMoveUp() throws Exception {
        Point pointBefore = getColumnAndRowOfZero(userRepository.findAll().get(0).getGame().getPuzzle());
        int beforeRowZeroLocation = pointBefore.y;
        mockMvc.perform(post("/users/play-game")
                .param("move", "UP")
                .param("id", userRepository.findAll().get(0).getId())
                .contentType(MediaType.APPLICATION_JSON));
        Point pointAfter = getColumnAndRowOfZero(userRepository.findAll().get(0).getGame().getPuzzle());
        int afterRowZeroLocation = pointAfter.y;
        if (beforeRowZeroLocation != 0) {
            assertEquals(beforeRowZeroLocation - 1, afterRowZeroLocation);
        } else {
            assertEquals(beforeRowZeroLocation, afterRowZeroLocation);
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
