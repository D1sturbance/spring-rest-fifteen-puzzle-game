package com.d1.model;

import org.springframework.stereotype.Component;

@Component
public class FifteenPuzzle {

    private final int size = 4;
    private int moves = 60;
    private int[][] puzzle;
    private int x = size - 1;
    private int y = size - 1;

    public int[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void createPuzzle() {
        puzzle = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                puzzle[j][i] = 1 + j * size + i;
            }
        }
        puzzle[size - 1][size - 1] = 0;
    }

    public void mixPuzzle() {
        boolean success = true;
        while (moves > 0) {
            switch ((int) Math.floor(Math.random() * 4)) {
                case 0: {
                    success = makeMove("UP");
                    break;
                }
                case 1: {
                    success = makeMove("LEFT");
                    break;
                }
                case 2: {
                    success = makeMove("DOWN");
                    break;
                }
                case 3: {
                    success = makeMove("RIGHT");
                    break;
                }
            }
            if (success) moves--;
        }
    }

    public boolean makeMove(String key) {
        int tempZero;
        switch (key) {
            case "UP": {
                if (y < 1) return false;
                tempZero = puzzle[y][x];
                puzzle[y][x] = puzzle[y - 1][x];
                puzzle[y - 1][x] = tempZero;
                y--;
                break;
            }
            case "LEFT": {
                if (x < 1) return false;
                tempZero = puzzle[y][x];
                puzzle[y][x] = puzzle[y][x - 1];
                puzzle[y][x - 1] = tempZero;
                x--;
                break;
            }
            case "DOWN": {
                if (y > size - 2) return false;
                tempZero = puzzle[y][x];
                puzzle[y][x] = puzzle[y + 1][x];
                puzzle[y + 1][x] = tempZero;
                y++;
                break;
            }
            case "RIGHT": {
                if (x > size - 2) return false;
                tempZero = puzzle[y][x];
                puzzle[y][x] = puzzle[y][x + 1];
                puzzle[y][x + 1] = tempZero;
                x++;
                break;
            }
        }
        return true;
    }

    public boolean isSolved() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (puzzle[j][i] == 0) continue;
                if (puzzle[j][i] != 1 + j * size + i) return false;
            }
        }
        return true;
    }
}
