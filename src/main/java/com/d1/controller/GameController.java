package com.d1.controller;

import com.d1.domain.GameMove;
import com.d1.service.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/users/play-game")
    public void makeMove(@RequestParam GameMove move, @RequestParam String id) {
        gameService.move(move, id);
    }

}
