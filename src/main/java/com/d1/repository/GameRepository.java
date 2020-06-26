package com.d1.repository;

import com.d1.domain.Game;
import com.d1.domain.User;
import com.d1.model.FifteenPuzzle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class GameRepository {

    public List<User> userList = new ArrayList<>();
    private FifteenPuzzle fifteenPuzzle;

    public void createUser(String userName) {
        long userId = 0;
        if (!userList.isEmpty()) {
            userId = userList.get(userList.size() - 1).getId();
            userId = ++userId;
        }
        User newUser = new User(userId, userName, new Game());
        userList.add(newUser);
    }

    public void createGameForUser(String gameName, Long id) {
        fifteenPuzzle = new FifteenPuzzle();
        Game game = getGameById(id);
        if (game.getPuzzle() == null && game.getName() == null) {
            game.setName(gameName);
            fifteenPuzzle.createPuzzle();
            fifteenPuzzle.mixPuzzle();
            game.setPuzzle(fifteenPuzzle.getPuzzle());
            game.setY(fifteenPuzzle.getY());
            game.setX(fifteenPuzzle.getX());
        }
    }

    public void makeGameMoveForUser(String gameMove, Long id) {
        Game game = getGameById(id);
        if (game.getPuzzle() != null) {
            fifteenPuzzle.setPuzzle(game.getPuzzle());
            fifteenPuzzle.setY(game.getY());
            fifteenPuzzle.setX(game.getX());
            if (!fifteenPuzzle.isSolved()) {
                fifteenPuzzle.makeMove(gameMove);
                game.setPuzzle(fifteenPuzzle.getPuzzle());
                game.setY(fifteenPuzzle.getY());
                game.setX(fifteenPuzzle.getX());
            } else {
                game.setGameSolved(true);
            }
        }
    }

    private Game getGameById(Long id) {
        return Objects.requireNonNull(userList.stream()
                .filter(user -> id.equals(user.getId()))
                .findAny()
                .orElse(null)).getGame();
    }
}
