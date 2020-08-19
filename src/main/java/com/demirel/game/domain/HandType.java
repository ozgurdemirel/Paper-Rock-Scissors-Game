package com.demirel.game.domain;

public enum HandType {

    ROCK("ROCK", 1) {
        @Override
        public boolean beats(HandType handType) {
            return handType.equals(SCISSOR);
        }
    },
    PAPER("PAPER", 2) {
        @Override
        public boolean beats(HandType handType) {
            return handType.equals(ROCK);
        }
    },
    SCISSOR("SCISSOR", 3) {
        @Override
        public boolean beats(HandType handType) {
            return handType.equals(PAPER);
        }
    };

    private final String value;
    private final int id;

    HandType(String value, int id) {
        this.value = value;
        this.id = id;
    }

    public static HandType find(int id) {
        for (HandType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new RuntimeException("Internal Error");
    }

    public abstract boolean beats(HandType handType);

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

}
