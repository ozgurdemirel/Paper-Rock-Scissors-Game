package com.demirel.game.service;

import com.demirel.game.domain.Game;
import com.demirel.game.domain.HandType;
import com.demirel.game.domain.Player;
import com.demirel.game.util.RandomHandTypeSelector;
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

@RunWith(PowerMockRunner.class)
@PrepareForTest({RandomHandTypeSelector.class})
public class GameLogicServiceTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @InjectMocks
    private GameLogicService gameLogicService;
    @Mock
    private RandomHandTypeSelector randomHandTypeSelector;

    @Before
    public void before() {
        PowerMockito.mockStatic(RandomHandTypeSelector.class);
        PowerMockito.when(RandomHandTypeSelector.getInstance()).thenReturn(randomHandTypeSelector);
    }

    @Test
    public void shouldMoveRock() {
        systemInMock.provideLines("1"); // Rock
        PowerMockito.when(randomHandTypeSelector.getHandType()).thenReturn(HandType.ROCK);

        Game game = gameLogicService.move(game());

        assertThat(game.getPlayer().getHandType()).isEqualTo(HandType.find(1));
        assertThat(game.getComputer().getHandType()).isEqualTo(HandType.ROCK);
    }

    @Test
    public void shouldMovePaper() {
        systemInMock.provideLines("2"); // Paper
        PowerMockito.when(randomHandTypeSelector.getHandType()).thenReturn(HandType.PAPER);

        Game game = gameLogicService.move(game());

        assertThat(game.getPlayer().getHandType()).isEqualTo(HandType.find(2));
        assertThat(game.getComputer().getHandType()).isEqualTo(HandType.PAPER);
    }

    private Game game() {
        Player player = Player.Builder.aPlayer()
                .name(PLAYER_NAME)
                .build();

        Player computer = Player.Builder.aPlayer()
                .name(COMPUTER_NAME)
                .build();

        Game build = Game.Builder.aGame()
                .player(player)
                .computer(computer)
                .gameId(UUID.randomUUID().toString())
                .build();
        return build;
    }

}
