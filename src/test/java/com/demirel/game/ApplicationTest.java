package com.demirel.game;

import com.demirel.game.controller.GameController;
import com.demirel.game.domain.Game;
import com.demirel.game.domain.HandType;
import com.demirel.game.domain.Player;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
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
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GameController.class})
public class ApplicationTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    @Mock
    private GameController gameController;
    @InjectMocks
    private Application application;

    @Before
    public void before() {
        PowerMockito.mockStatic(GameController.class);
        PowerMockito.when(GameController.getInstance()).thenReturn(gameController);
    }

    @Test
    public void shouldFiveTimesGame() {
        systemInMock.provideLines("4", "4", "4", "4", "5"); // exit option=5, continue = 4
        Game game = game(HandType.ROCK, HandType.SCISSOR);
        PowerMockito.when(gameController.play()).thenReturn(game);

        application.gameLoop();

        verify(gameController, times(5)).play();
    }

    @Test
    public void shouldGamePlayedAndEndWin() {
        systemInMock.provideLines("5"); // exit option
        Game game = game(HandType.ROCK, HandType.SCISSOR);
        PowerMockito.when(gameController.play()).thenReturn(game);

        application.gameLoop();

        assertThat(systemOutRule.getLog()).contains("Congratulations you won!");

    }

    @Test
    public void shouldGamePlayedAndEndWithLost() {
        systemInMock.provideLines("5"); // exit
        Game game = game(HandType.PAPER, HandType.SCISSOR);
        PowerMockito.when(gameController.play()).thenReturn(game);

        application.gameLoop();

        assertThat(systemOutRule.getLog()).contains("You  lost! :(");
    }

    @Test
    public void shouldGamePlayedAndEndWithDraw() {
        systemInMock.provideLines("5"); // exit
        Game game = game(HandType.SCISSOR, HandType.SCISSOR);
        PowerMockito.when(gameController.play()).thenReturn(game);

        application.gameLoop();

        assertThat(systemOutRule.getLog()).contains("DRAW! :-|");
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
