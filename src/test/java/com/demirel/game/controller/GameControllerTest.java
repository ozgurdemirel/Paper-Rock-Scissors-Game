package com.demirel.game.controller;

import com.demirel.game.domain.Game;
import com.demirel.game.domain.HandType;
import com.demirel.game.domain.Player;
import com.demirel.game.service.GameLogicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static com.demirel.game.util.GameConstants.COMPUTER_NAME;
import static com.demirel.game.util.GameConstants.PLAYER_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GameLogicService.class, GameLogicService.class})
public class GameControllerTest {

    @InjectMocks
    private GameController gameController;

    @Mock
    private GameLogicService gameLogicService;

    @Before
    public void before() {
        PowerMockito.mockStatic(GameLogicService.class);
        PowerMockito.when(GameLogicService.getInstance()).thenReturn(gameLogicService);
    }

    @Test
    public void shouldPlayWithExpectedGameState() {
        Game game = game(HandType.SCISSOR, HandType.PAPER);
        PowerMockito.when(gameLogicService.move(any())).thenReturn(game);

        Game gameReturn = gameController.play();

        assertThat(gameReturn.getPlayer().getHandType()).isEqualTo(HandType.SCISSOR);
        assertThat(gameReturn.getComputer().getHandType()).isEqualTo(HandType.PAPER);
    }

    private Game game(HandType playerHand, HandType computerHand) {
        Player player = Player.Builder.aPlayer()
                .name(PLAYER_NAME)
                .handType(playerHand)
                .build();

        Player computer = Player.Builder.aPlayer()
                .name(COMPUTER_NAME)
                .handType(computerHand)
                .build();

        Game build = Game.Builder.aGame()
                .player(player)
                .computer(computer)
                .gameId(UUID.randomUUID().toString())
                .build();


        build.determineWinner();
        return build;
    }

}
