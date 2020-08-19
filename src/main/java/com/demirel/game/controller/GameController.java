package com.demirel.game.controller;

import com.demirel.game.domain.Game;
import com.demirel.game.domain.Player;
import com.demirel.game.service.GameLogicService;

import java.util.UUID;

import static com.demirel.game.util.GameConstants.COMPUTER_NAME;
import static com.demirel.game.util.GameConstants.PLAYER_NAME;

public final class GameController {

    private static GameController gameController;
    private final GameLogicService gameLogicService;

    private GameController() {
        gameLogicService = GameLogicService.getInstance();
    }

    private GameController(GameLogicService gameLogicService) {
        this.gameLogicService = gameLogicService;
    }

    public static synchronized GameController getInstance() {
        if (gameController != null) {
            return gameController;
        }
        return gameController = new GameController();
    }

    /***
     * Game initialization, hand choice operation executed here
     * @return game with winner
     */
    public synchronized Game play() {
        Player player = Player.Builder.aPlayer()
                .name(PLAYER_NAME)
                .build();

        Player computer = Player.Builder.aPlayer()
                .name(COMPUTER_NAME)
                .build();

        Game game = Game.Builder.aGame()
                .player(player)
                .computer(computer)
                .gameId(UUID.randomUUID().toString())
                .build();
        return gameLogicService.move(game);
    }

}
