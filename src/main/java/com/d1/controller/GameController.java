package com.d1.controller;

import com.d1.domain.User;
import com.d1.repository.GameRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return gameRepository.userList;
    }

    @PostMapping("/create-user")
    public User createUser(@RequestParam String userName) {
        gameRepository.createUser(userName);
        return gameRepository.userList.get(gameRepository.userList.size() - 1);
    }

    @PutMapping("/users/{id}/create-game")
    public User createGameForUser(@RequestParam String gameName, @PathVariable Long id) {
        gameRepository.createGameForUser(gameName, id);
        return gameRepository.userList.stream()
                .filter(user -> id.equals(user.getId()))
                .findAny()
                .orElse(null);
    }

    @PutMapping("/users/{id}/play-game")
    public User makeGameMoveForUser(@RequestParam String gameMove, @PathVariable Long id) {
        gameRepository.makeGameMoveForUser(gameMove, id);
        return gameRepository.userList.stream()
                .filter(user -> id.equals(user.getId()))
                .findAny()
                .orElse(null);
    }


}
