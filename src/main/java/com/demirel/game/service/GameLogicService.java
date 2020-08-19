package com.demirel.game.service;

import com.demirel.game.domain.Game;
import com.demirel.game.util.GameConstants;
import com.demirel.game.util.MessageResource;
import com.demirel.game.util.RandomHandTypeSelector;
import com.demirel.game.util.UserInteraction;
import com.demirel.game.domain.HandType;

public class GameLogicService {

    private static GameLogicService gameLogicService;
    private final RandomHandTypeSelector randomHandTypeSelector;

    private GameLogicService() {
        randomHandTypeSelector = RandomHandTypeSelector.getInstance();
    }

    private GameLogicService(RandomHandTypeSelector randomHandTypeSelector) {
        this.randomHandTypeSelector = randomHandTypeSelector;
    }

    public static synchronized GameLogicService getInstance() {
        if (gameLogicService != null) {
            return gameLogicService;
        }
        return gameLogicService = new GameLogicService();
    }

    /***
     * Game state executed here user chose a hand then computer and  determination of winner
     * @param game game init state
     * @return
     */
    public Game move(Game game) {
        UserInteraction askForHandTypeToTheUser = UserInteraction.Builder.aChoiceScanner()
                .prompt(MessageResource.WHICH_TYPE_OF_HAND_QUESTION)
                .pattern(GameConstants.WHICH_TYPE_OF_HAND_REGEX)
                .warning(MessageResource.HAS_TO_BE_NUMERIC_MUST_BE_1_2_3)
                .build();

        Integer userHandChoice = askForHandTypeToTheUser.askUserForNumberInput();

        game.getPlayer().setHandType(HandType.find(userHandChoice));
        game.getComputer().setHandType(randomHandTypeSelector.getHandType());
        game.determineWinner();

        return game;
    }

}
