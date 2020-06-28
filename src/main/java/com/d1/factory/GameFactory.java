package com.d1.factory;

import com.d1.domain.Game;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public final class GameFactory {

    public Game create(String name) {
        return new Game(
                name,
                false,
                shuffleTiles(createPuzzle()));
    }

    private int[][] createPuzzle() {
        int size = 4;
        int[][] puzzle = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                puzzle[j][i] = 1 + j * size + i;
            }
        }
        puzzle[size - 1][size - 1] = 0;
        return puzzle;
    }

    private int[][] shuffleTiles(int[][] puzzle) {
        Random random = new Random();
        for (int k = puzzle.length - 1; k > 0; k--) {
            for (int j = puzzle[k].length - 1; j > 0; j--) {
                int tileX = random.nextInt(k + 1);
                int tileY = random.nextInt(j + 1);

                int tmp = puzzle[k][j];
                puzzle[k][j] = puzzle[tileX][tileY];
                puzzle[tileX][tileY] = tmp;
            }
        }
        return puzzle;
    }
}
