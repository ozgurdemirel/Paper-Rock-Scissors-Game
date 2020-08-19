package com.demirel.game.domain;

import com.demirel.game.util.GameConstants;

import java.time.LocalDateTime;

public class Game {

    private final LocalDateTime startTime = LocalDateTime.now();
    private String gameId;
    private Player player;
    private Player computer;
    private Player winner;

    public String getGameId() {
        return gameId;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getComputer() {
        return computer;
    }

    public Player getWinner() {
        return winner;
    }

    public Player determineWinner() {
        // it might be written again without any 'if' with strategy design pattern but i would prefer to keep like this since 'if case' not too much...
        if (this.getPlayer().getHandType().beats(this.getComputer().getHandType())) {
            this.winner = this.player;
        } else if (this.getComputer().getHandType().beats(this.getPlayer().getHandType())) {
            this.winner = this.computer;
        } else {
            this.winner = Player.Builder.aPlayer().name(GameConstants.EMPTY_PLAYER).build(); // draw...
        }
        return this.winner;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", player=" + player +
                ", computer=" + computer +
                ", winner=" + winner +
                ", startTime=" + startTime +
                '}';
    }

    public static final class Builder {
        private String gameId;
        private Player player;
        private Player computer;
        private Player winner;

        private Builder() {
        }

        public static Builder aGame() {
            return new Builder();
        }

        public Builder gameId(String gameId) {
            this.gameId = gameId;
            return this;
        }

        public Builder player(Player player) {
            this.player = player;
            return this;
        }

        public Builder computer(Player computer) {
            this.computer = computer;
            return this;
        }

        public Builder winner(Player winner) {
            this.winner = winner;
            return this;
        }


        public Game build() {
            Game game = new Game();
            game.player = this.player;
            game.computer = this.computer;
            game.winner = this.winner;
            game.gameId = this.gameId;
            return game;
        }
    }
}
