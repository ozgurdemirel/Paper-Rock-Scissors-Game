package com.demirel.game;

import com.demirel.game.controller.GameController;
import com.demirel.game.domain.Game;
import com.demirel.game.domain.Player;
import com.demirel.game.util.GameConstants;
import com.demirel.game.util.MessageResource;
import com.demirel.game.util.UserInteraction;


public class Application {

    private final GameController gameController;

    private Application() {
        this.gameController = GameController.getInstance();
    }

    private Application(GameController gameController) {
        this.gameController = gameController;
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.gameLoop();
    }

    /**
     * Game loop starting point
     */
    void gameLoop() {

        while (Boolean.TRUE) {
            Game game = gameController.play();

            printGameState(game);

            UserInteraction.print("- - - - - - - - - - - - - - - - - - - - - - -");
            UserInteraction askForContinue = UserInteraction.Builder.aChoiceScanner()
                    .prompt(MessageResource.GAME_LOOP_EXIT_OR_CONTINUE_MESSAGE)
                    .pattern(GameConstants.GAME_LOOP_EXIT_OR_CONTINUE_REGEX)
                    .warning(MessageResource.GAME_LOOP_EXIT_OR_CONTINUE_MESSAGE_WARNING)
                    .build();
            if (Integer.valueOf(askForContinue.askUserForInput()) == GameConstants.GAME_EXIT) {
                break;
            }
        }

    }

    private void printGameState(Game game) {
        Player player = game.getPlayer();
        Player winner = game.getWinner();
        Player computer = game.getComputer();
        switch (winner.getName()) {
            case GameConstants.PLAYER_NAME:
                UserInteraction.print(MessageResource.CONGRATULATION_MESSAGE);
                UserInteraction.print(
                        String.format(MessageResource.X_BEATS_X, winner.getHandType().getValue(), computer.getHandType().getValue())
                );
                break;
            case GameConstants.COMPUTER_NAME:
                UserInteraction.print(MessageResource.LOST_MESSAGE);
                UserInteraction.print(
                        String.format(MessageResource.X_BEATS_X, winner.getHandType().getValue(), player.getHandType().getValue())
                );
                break;
            default:
                UserInteraction.print(MessageResource.DRAW_MESSAGE);
                break;
        }

        UserInteraction.print(
                String.format(MessageResource.HAND_STATE_MESSAGE, player.getHandType().getValue(), computer.getHandType().getValue())
        );
    }

}
