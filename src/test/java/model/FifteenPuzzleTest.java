package model;

import com.d1.PuzzleLauncher;
import com.d1.model.FifteenPuzzle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertArrayEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PuzzleLauncher.class)
public class FifteenPuzzleTest {

    @Autowired
    FifteenPuzzle fifteenPuzzle;

    int[][] expectedPuzzle;
    int expectedX;
    int expectedY;

    @Before
    public void setUp() {
        expectedPuzzle = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        expectedX = 3;
        expectedY = 3;
    }

    @Test
    public void createPuzzleHasPuzzle() {
        fifteenPuzzle.createPuzzle();
        assertNotNull(fifteenPuzzle.getPuzzle());

        int[][] actualPuzzle = fifteenPuzzle.getPuzzle();
        printPuzzle(expectedPuzzle);
        printPuzzle(actualPuzzle);
        assertArrayEquals(expectedPuzzle, actualPuzzle);
    }

    @Test
    public void mixPuzzleHasMixedPuzzle() {
        fifteenPuzzle.createPuzzle();
        fifteenPuzzle.mixPuzzle();
        assertNotNull(fifteenPuzzle.getPuzzle());

        int[][] actualPuzzle = fifteenPuzzle.getPuzzle();
        printPuzzle(expectedPuzzle);
        printPuzzle(actualPuzzle);
        assertFalse(Arrays.deepEquals(expectedPuzzle, actualPuzzle));
    }

    @Test
    public void makeMoveHasMadeMove() {
        fifteenPuzzle.createPuzzle();
        assertNotNull(fifteenPuzzle.getPuzzle());

        fifteenPuzzle.makeMove("UP");
        int actualXUp = fifteenPuzzle.getX();
        int actualYUp = fifteenPuzzle.getY();
        assertEquals(3, actualXUp);
        assertEquals(2, actualYUp);

        fifteenPuzzle.makeMove("LEFT");
        int actualXLeft = fifteenPuzzle.getX();
        int actualYLeft = fifteenPuzzle.getY();
        assertEquals(2, actualXLeft);
        assertEquals(2, actualYLeft);

        fifteenPuzzle.makeMove("DOWN");
        int actualXDown = fifteenPuzzle.getX();
        int actualYDown = fifteenPuzzle.getY();
        assertEquals(2, actualXDown);
        assertEquals(3, actualYDown);

        fifteenPuzzle.makeMove("RIGHT");
        int actualXRight = fifteenPuzzle.getX();
        int actualYRight = fifteenPuzzle.getY();
        assertEquals(3, actualXRight);
        assertEquals(3, actualYRight);
    }

    @Test
    public void isSolvedHasSolvedPuzzle() {
        fifteenPuzzle.createPuzzle();
        assertTrue(fifteenPuzzle.isSolved());

        fifteenPuzzle.setPuzzle(expectedPuzzle);
        assertTrue(fifteenPuzzle.isSolved());

        fifteenPuzzle.createPuzzle();
        fifteenPuzzle.setMoves(60);
        fifteenPuzzle.mixPuzzle();
        assertFalse(fifteenPuzzle.isSolved());

        fifteenPuzzle.setPuzzle(expectedPuzzle);
        fifteenPuzzle.setMoves(60);
        fifteenPuzzle.mixPuzzle();
        assertFalse(fifteenPuzzle.isSolved());

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
