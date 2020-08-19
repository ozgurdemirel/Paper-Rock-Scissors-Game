package com.demirel.game.domain;

public class Player {

    private String name; // HUMAN , COMPUTER , might be an enum
    private HandType handType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HandType getHandType() {
        return handType;
    }

    public void setHandType(HandType handType) {
        this.handType = handType;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", handType=" + handType +
                '}';
    }

    public static final class Builder {
        private String name; // HUMAN , COMPUTER
        private HandType handType;

        private Builder() {
        }

        public static Builder aPlayer() {
            return new Builder();
        }

        public Builder name(String type) {
            this.name = type;
            return this;
        }

        public Builder handType(HandType handType) {
            this.handType = handType;
            return this;
        }

        public Player build() {
            Player player = new Player();
            player.name = this.name;
            player.handType = this.handType;
            return player;
        }
    }
}
