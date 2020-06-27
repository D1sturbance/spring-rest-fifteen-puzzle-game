package com.d1.service;

import com.d1.domain.Game;
import com.d1.domain.GameMove;
import com.d1.domain.User;
import com.d1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class GameService {

    private final UserRepository userRepository;

    public GameService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void move(GameMove gameMove, String userId) {
        User user = userRepository.findById(userId);
        int[][] movedPuzzle = makeMove(user.getGame().getPuzzle(), gameMove);
        if (isSolved(movedPuzzle)) {
            userRepository.replace(copyUser(
                    userId,
                    user.getName(),
                    user.getGame().getName(),
                    user.getGame().getPuzzle(),
                    true
            ));
        } else {
            userRepository.replace(copyUser(
                    userId,
                    user.getName(),
                    user.getGame().getName(),
                    movedPuzzle,
                    false
            ));
        }
    }

    private User copyUser(String id, String userName, String gameName, int[][] puzzle, boolean isSolved) {
        return new User(id, userName, new Game(gameName, isSolved, puzzle));
    }

    private int[][] makeMove(int[][] puzzle, GameMove move) {
        Point point = getColumnAndRowOfZero(puzzle);
        int x = point.x;
        int y = point.y;
        int tempZero;
        switch (move) {
            case UP: {
                if (y < 1) break;
                tempZero = puzzle[y][x];
                puzzle[y][x] = puzzle[y - 1][x];
                puzzle[y - 1][x] = tempZero;
                break;
            }
            case LEFT: {
                if (x < 1) break;
                tempZero = puzzle[y][x];
                puzzle[y][x] = puzzle[y][x - 1];
                puzzle[y][x - 1] = tempZero;
                break;
            }
            case DOWN: {
                if (y > puzzle.length - 2) break;
                tempZero = puzzle[y][x];
                puzzle[y][x] = puzzle[y + 1][x];
                puzzle[y + 1][x] = tempZero;
                break;
            }
            case RIGHT: {
                if (x > puzzle.length - 2) break;
                tempZero = puzzle[y][x];
                puzzle[y][x] = puzzle[y][x + 1];
                puzzle[y][x + 1] = tempZero;
                break;
            }
        }
        return puzzle;
    }

    private boolean isSolved(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                if (puzzle[j][i] == 0) continue;
                if (puzzle[j][i] != 1 + j * puzzle.length + i) return false;
            }
        }
        return true;
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
