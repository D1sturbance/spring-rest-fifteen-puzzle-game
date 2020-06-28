package com.d1.controller;

import com.d1.domain.TileSlide;
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
    public void makeMove(@RequestParam TileSlide tileMove, @RequestParam String id) {
        gameService.movePuzzleTile(tileMove, id);
    }

}
