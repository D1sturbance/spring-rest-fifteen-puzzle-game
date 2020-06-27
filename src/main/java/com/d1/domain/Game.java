package com.d1.domain;

import lombok.Value;

@Value
public class Game {
    String name;
    boolean isSolved;
    int[][] puzzle;
}
