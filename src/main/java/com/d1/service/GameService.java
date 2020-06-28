package com.d1.service;

import com.d1.domain.Game;
import com.d1.domain.TileSlide;
import com.d1.domain.User;
import com.d1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.awt.Point;


@Service
public class GameService {

    private final UserRepository userRepository;

    public GameService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void movePuzzleTile(TileSlide tileMove, String userId) {
        User user = userRepository.findById(userId);
        int[][] movedPuzzle = makeMove(user.getGame().getPuzzle(), tileMove);
        if (isSolved(movedPuzzle)) {
            userRepository.update(copyUser(
                    userId,
                    user.getName(),
                    user.getGame().getName(),
                    user.getGame().getPuzzle(),
                    true
            ));
        } else {
            userRepository.update(copyUser(
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

    private int[][] makeMove(int[][] puzzle, TileSlide tileMove) {
        Point point = findPositionOfZero(puzzle);
        int tempZero;
        switch (tileMove) {
            case UP: {
                if (point.y < 1) {
                    break;
                }
                tempZero = puzzle[point.y][point.x];
                puzzle[point.y][point.x] = puzzle[point.y - 1][point.x];
                puzzle[point.y - 1][point.x] = tempZero;
                break;
            }
            case LEFT: {
                if (point.x < 1) {
                    break;
                }
                tempZero = puzzle[point.y][point.x];
                puzzle[point.y][point.x] = puzzle[point.y][point.x - 1];
                puzzle[point.y][point.x - 1] = tempZero;
                break;
            }
            case DOWN: {
                if (point.y > puzzle.length - 2) {
                    break;
                }
                tempZero = puzzle[point.y][point.x];
                puzzle[point.y][point.x] = puzzle[point.y + 1][point.x];
                puzzle[point.y + 1][point.x] = tempZero;
                break;
            }
            case RIGHT: {
                if (point.x > puzzle.length - 2) {
                    break;
                }
                tempZero = puzzle[point.y][point.x];
                puzzle[point.y][point.x] = puzzle[point.y][point.x + 1];
                puzzle[point.y][point.x + 1] = tempZero;
                break;
            }
        }
        return puzzle;
    }

    private boolean isSolved(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                if (puzzle[j][i] == 0) {
                    continue;
                }
                if (puzzle[j][i] != 1 + j * puzzle.length + i) {
                    return false;
                }
            }
        }
        return true;
    }

    private Point findPositionOfZero(int[][] puzzle) {
        Point point = new Point();
        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle[0].length; col++) {
                if (puzzle[row][col] == 0) {
                    point.setLocation(col, row);
                    break;
                }
            }
        }
        return point;
    }
}
