package com.d1.domain;

import lombok.Value;

import java.util.Arrays;

@Value
public class Game {
    String name;
    boolean isSolved;
    int[][] puzzle;

    public Game(String name, boolean isSolved, int[][] puzzle) {
        this.name = name;
        this.isSolved = isSolved;
        this.puzzle = Arrays.copyOf(puzzle, puzzle.length);
    }

    public int[][] getPuzzle() {
        return Arrays.copyOf(puzzle, puzzle.length);
    }
}
