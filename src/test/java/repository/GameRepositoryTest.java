package repository;

import com.d1.PuzzleLauncher;
import com.d1.domain.Game;
import com.d1.domain.User;
import com.d1.model.FifteenPuzzle;
import com.d1.repository.GameRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PuzzleLauncher.class)
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    FifteenPuzzle fifteenPuzzle;

    public List<User> expectedUserList = new ArrayList<>();

    @Before
    public void setUp() {
        int[][] puzzle = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        expectedUserList.add(new User(0
                , "Sam"
                , new Game("puzzle"
                , "UP"
                , puzzle
                , 3
                , 3
                , false)));
    }

    @After
    public void cleanUp(){
        gameRepository.userList.clear();
    }

    @Test
    public void createUserHasCreatedUser() {
        gameRepository.createUser("Sam");
        assertEquals(expectedUserList.size(), gameRepository.userList.size());
        assertEquals(expectedUserList.get(0).getId(), gameRepository.userList.get(0).getId());
        assertEquals(expectedUserList.get(0).getName(), gameRepository.userList.get(0).getName());
    }

    @Test
    public void createGameForUserHasGameCreatedForUser() {
        gameRepository.createUser("Sam");
        gameRepository.createGameForUser("puzzle", 0L);
        assertEquals(expectedUserList.size(), gameRepository.userList.size());
        assertEquals(expectedUserList.get(0).getId(), gameRepository.userList.get(0).getId());
        assertEquals(expectedUserList.get(0).getName(), gameRepository.userList.get(0).getName());
        printPuzzle(expectedUserList.get(0).getGame().getPuzzle());
        printPuzzle(gameRepository.userList.get(0).getGame().getPuzzle());
        assertEquals(expectedUserList.get(0).getGame().getName(),
                gameRepository.userList.get(0).getGame().getName());
        assertFalse(Arrays.deepEquals(expectedUserList.get(0).getGame().getPuzzle()
                , gameRepository.userList.get(0).getGame().getPuzzle()));
    }

    @Test
    public void makeGameMoveForUserHasMadeGameMoveForUser() {
        gameRepository.createUser("Sam");
        gameRepository.createGameForUser("puzzle", 0L);
        assertEquals(expectedUserList.size(), gameRepository.userList.size());
        assertEquals(expectedUserList.get(0).getId(), gameRepository.userList.get(0).getId());
        assertEquals(expectedUserList.get(0).getName(), gameRepository.userList.get(0).getName());
        assertEquals(expectedUserList.get(0).getGame().getName(),
                gameRepository.userList.get(0).getGame().getName());
        assertFalse(Arrays.deepEquals(expectedUserList.get(0).getGame().getPuzzle()
                , gameRepository.userList.get(0).getGame().getPuzzle()));

        int beforeXUp = gameRepository.userList.get(0).getGame().getX();
        int beforeYUp = gameRepository.userList.get(0).getGame().getY();
        gameRepository.makeGameMoveForUser("UP", 0L);
        int actualXUp = gameRepository.userList.get(0).getGame().getX();
        int actualYUp = gameRepository.userList.get(0).getGame().getY();
        assertEquals(beforeXUp, actualXUp);

        if (beforeYUp != 0) {
            assertEquals(beforeYUp - 1, actualYUp);
        } else {
            assertEquals(beforeYUp, actualYUp);
        }

        int beforeXDown = gameRepository.userList.get(0).getGame().getX();
        int beforeYDown = gameRepository.userList.get(0).getGame().getY();
        gameRepository.makeGameMoveForUser("DOWN", 0L);
        int actualXDown = gameRepository.userList.get(0).getGame().getX();
        int actualYDown = gameRepository.userList.get(0).getGame().getY();
        assertEquals(beforeXDown, actualXDown);
        if (beforeYDown != 3) {
            assertEquals(beforeYDown + 1, actualYDown);
        } else {
            assertEquals(beforeYDown, actualYDown);
        }

        int beforeXLeft = gameRepository.userList.get(0).getGame().getX();
        int beforeYLeft = gameRepository.userList.get(0).getGame().getY();
        gameRepository.makeGameMoveForUser("LEFT", 0L);
        int actualXLeft = gameRepository.userList.get(0).getGame().getX();
        int actualYLeft = gameRepository.userList.get(0).getGame().getY();

        if (beforeXLeft != 0) {
            assertEquals(beforeXLeft - 1, actualXLeft);
        } else {
            assertEquals(beforeXLeft, actualXLeft);
        }
        assertEquals(beforeYLeft, actualYLeft);

        int beforeXRight = gameRepository.userList.get(0).getGame().getX();
        int beforeYRight = gameRepository.userList.get(0).getGame().getY();
        gameRepository.makeGameMoveForUser("RIGHT", 0L);
        int actualXRight = gameRepository.userList.get(0).getGame().getX();
        int actualYRight = gameRepository.userList.get(0).getGame().getY();

        if (beforeXRight != 3) {
            assertEquals(beforeXRight + 1, actualXRight);
        } else {
            assertEquals(beforeXRight, actualXRight);
        }
        assertEquals(beforeYRight, actualYRight);

    }

    public static void printPuzzle(int[][] puzzle) {
        int size = 4;
        if (puzzle == null) {
            System.out.println("Puzzle:");
            return;
        }
        String str;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                str = puzzle[i][j] + " ";
                if (puzzle[i][j] < 10) str = " " + str;
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }
}
