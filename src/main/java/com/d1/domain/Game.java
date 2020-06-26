package com.d1.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {

    private String name;
    private String move;
    private int[][] puzzle;
    private int x;
    private int y;
    private boolean isGameSolved;
}
