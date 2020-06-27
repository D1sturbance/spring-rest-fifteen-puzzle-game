package com.d1.factory;

import com.d1.domain.Game;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GameFactory {

    public final int SIZE = 4;

    public Game create(String name) {
        return new Game(name, false, createPuzzle());
    }

    private int[][] createPuzzle() {
        int[][] puzzle = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                puzzle[j][i] = 1 + j * SIZE + i;
            }
        }
        puzzle[SIZE - 1][SIZE - 1] = 0;
        shufflePuzzle(puzzle);
        return puzzle;
    }

    private void shufflePuzzle(int[][] puzzle) {
        Random random = new Random();
        for (int k = puzzle.length - 1; k > 0; k--) {
            for (int j = puzzle[k].length - 1; j > 0; j--) {
                int m = random.nextInt(k + 1);
                int n = random.nextInt(j + 1);

                int tmp = puzzle[k][j];
                puzzle[k][j] = puzzle[m][n];
                puzzle[m][n] = tmp;
            }
        }
    }
}
